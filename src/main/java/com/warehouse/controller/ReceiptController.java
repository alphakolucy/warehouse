package com.warehouse.controller;

import com.warehouse.model.*;
import com.warehouse.service.CostSettinsService;
import com.warehouse.service.EntrustOrderService;
import com.warehouse.service.ReceiptService;
import com.warehouse.service.WarehouseStockService;
import com.warehouse.service.repository.*;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.*;
import com.warehouse.util.tools.ClassAttrValCompare;
import com.warehouse.util.tools.DateUtil;
import com.warehouse.util.tools.EmptyUtil;
import com.warehouse.util.tools.ObjectCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 收发货单
 */
@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private EntrustOrderDetailRepository entrustOrderDetailRepository;
    @Autowired
    private MeteringReceiptOrderRepository meteringReceiptOrderRepository;
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private WarehouseStockService warehouseStockService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EntrustOrderRepository entrustOrderRepository;
    @Autowired
    private CostSettinsService costSettinsService;

    /**
     * @api {GET} /receipt 收发货单列表
     * @apiGroup receipt
     * @apiVersion 0.0.1
     * @apiDescription 收发货单列表
     * @apiParam {String} [receiptSettlementId] 收发货结算单Id
     * @apiParam {String} [entrustOrderDetailId] 委托单明细Id
     * @apiParam {String} [orderNo] 单号(支持模糊查询)
     * @apiParam {Integer} [orderType] 订单类型
     * - IN_STOCK(14010, "入库"),
     * - OUT_STOCK(14020, "出库");
     * @apiParam {Integer} [orderState] 订单状态
     * - NEW_CREATE(90010, "新建"),
     * - IN_METERING(90020, "计量中"),
     * - HAD_METERING(90030, "已计量"),
     * - CONFIRM(90040, "已确认"),
     * - PASS(90060, "已放行"),
     * - INVALID(-90000, "作废");
     * @apiParam {Boolean=true,false} hasMeteringConfirm 计量单是否有一个确认过
     * @apiParam {String} [entrustOrderId] 委托单Id
     * @apiParam {Boolean} [handWarehouse] 是否分配库区/库层
     * - true:已分配
     * - false:未分配
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParamExample 请求明文：
     * /receipt?contract_num=1111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {Integer} orderState 订单状态
     * @apiSuccess (200) {String} orderStateTxt 订单状态Txt
     * @apiSuccess (200) {Integer} orderType 订单类型
     * @apiSuccess (200) {String} orderTypeTxt 订单类型Txt
     * @apiSuccess (200) {BigDecimal} fee 费用
     * @apiSuccess (200) {BigDecimal} num 件数
     * @apiSuccess (200) {BigDecimal} tonNum 吨数
     *
     * @apiSuccess (200) {Object} warehouseSiteData 仓库对象
     * @apiSuccess (200) {String} warehouseSiteData.id id
     * @apiSuccess (200) {String} warehouseSiteData.name 名称

     * @apiSuccess (200) {Object} warehouseAreaData 库区对象
     * @apiSuccess (200) {String} warehouseAreaData.id id
     * @apiSuccess (200) {String} warehouseAreaData.name 名称
     *
     * @apiSuccess (200) {Object} warehouseData 库位对象
     * @apiSuccess (200) {String} warehouseData.id id
     * @apiSuccess (200) {String} warehouseData.name 名称
     *
     * @apiSuccess (200) {Object} customerData 客户对象
     * @apiSuccess (200) {String} customerData.id id
     * @apiSuccess (200) {String} customerData.name 名称
     *
     * @apiSuccess (200) {Object} productData 物资对象
     * @apiSuccess (200) {String} productData.id id
     * @apiSuccess (200) {String} productData.productName 名称
     *
     * @apiSuccess (200) {Object} entrustOrderDetailData 委托单明细对象
     * @apiSuccess (200) {String} entrustOrderDetailData.id id
     * @apiSuccess (200) {String} entrustOrderDetailData.vehicleNo 车号
     * @apiSuccess (200) {BigDecimal} entrustOrderDetailData.num 件数
     * @apiSuccess (200) {BigDecimal} entrustOrderDetailData.tonNum 吨数
     *
     * @apiSuccess (200) {Boolean=true,false} hasMeteringConfirm 计量单是否有一个确认过
     *
     * @apiSuccess (200) {Object} planData 计划对象
     * @apiSuccess (200) {BigDecimal} planData.planNum 数量
     * @apiSuccess (200) {BigDecimal} planData.planNum 吨数

     * @apiSuccess (200) {Object} noMeterData 未计量对象
     * @apiSuccess (200) {BigDecimal} noMeterData.meterNum 数量
     * @apiSuccess (200) {BigDecimal} noMeterData.meterTonNum 吨数
     *
     * @apiSuccess (200) {Object} meterData 已计量
     * @apiSuccess (200) {BigDecimal} meterData.meterNum 数量
     * @apiSuccess (200) {BigDecimal} meterData.meterTonNum 吨数
     *
     * @apiSuccess (200) {String} yardman 调度员(收货单新增)
     * @apiSuccess (200) {String} tallyMan 理货员(收货单新增)
     * @apiSuccess (200) {String} loadMan 装卸工(收、发货单确认)
     * @apiSuccess (200) {String} auditMan 稽核员(收货单确认)
     * @apiSuccess (200) {Boolean} handWarehouse 是否分配库区/库层
     * - true:已分配
     * - false:未分配
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Receipt>> findAll(Receipt entity,
    												String customerId,
    												String productId,
                                                 String receiptSettlementId,
                                                 String entrustOrderDetailId,
                                                 String entrustOrderId,
                                                 @PageableDefault(value = 10,
                                                         sort = {"createTime"},
                                                         direction = Sort.Direction.DESC)
                                                         Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //单号模糊方式查
                .withMatcher("orderNo", ExampleMatcher.GenericPropertyMatchers.contains());

        //收发货结算单Id
        if(EmptyUtil.isNotEmpty(receiptSettlementId)){
            ReceiptSettlement receiptSettlement = new ReceiptSettlement();
            receiptSettlement.setId(receiptSettlementId);
            entity.setReceiptSettlement(receiptSettlement);
        }
        //委托单明细Id
        if(EmptyUtil.isNotEmpty(entrustOrderDetailId)){
            EntrustOrderDetail entrustOrderDetail = new EntrustOrderDetail();
            entrustOrderDetail.setId(entrustOrderDetailId);
            entity.setEntrustOrderDetail(entrustOrderDetail);
        }
        
        //客户id
        if(EmptyUtil.isNotEmpty(customerId)){
        		Customer customer = new Customer();
        		customer.setId(customerId);
        		entity.setCustomer(customer);
        }
        
        //产品id
        if(EmptyUtil.isNotEmpty(productId)){
        		Product product = new Product();
        		product.setId(productId);
        		entity.setProduct(product);
        }

        //委托单Id
        if(EmptyUtil.isNotEmpty(entrustOrderId)){
            EntrustOrder entrustOrder = new EntrustOrder();
            entrustOrder.setId(entrustOrderId);
            entity.setEntrustOrder(entrustOrder);
        }

        Example<Receipt> example = Example.of(entity, matcher);
        Page<Receipt> apply = this.receiptRepository.findAll(example, pageable);
        return new ResponseEntity<Page<Receipt>>(apply, HttpStatus.OK);
    }
    
    //通知
    @GetMapping(produces = "application/json", value="/all/notice")
    public ResponseEntity<Page<Receipt>> notice(Receipt entity,
    												String transState,
    												String warehouseId,
                                                 @PageableDefault(value = 100,
                                                         sort = {"createTime"},
                                                         direction = Sort.Direction.DESC)
                                                         Pageable pageable) {
    	
    	
    	 		Specification<Receipt> specification = new Specification<Receipt>() {
             @Override
             public Predicate toPredicate(Root<Receipt> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                 List<Predicate> list = new ArrayList<Predicate>();
                 //订单状态列表逗号分隔
                 String[] strs = transState.split(",");
                 list.add(root.get("orderState").in(strs)); 
                 
                 Join<Receipt,EntrustOrder> rectiptJoin = root.join(root.getModel().getSingularAttribute("entrustOrder",EntrustOrder.class),JoinType.LEFT);
                 list.add(cb.like(rectiptJoin.get("arrivalMode").as(String.class), "火车"));
                 list.add(cb.equal(rectiptJoin.get("orderType").as(Integer.class), EntrustOrderType.RECEIVE_GOODS.getValue()));
//                 list.add(cb.equal(rectiptJoin.get("orderState").as(Integer.class), EntrustOrderState.CONFIRM.getValue())); 
                 
                 //仓库Id查询
                 if (EmptyUtil.isNotEmpty(warehouseId)) {
                     Warehouse warehouse = new Warehouse();
                     warehouse.setId(warehouseId);
                     list.add(cb.equal(root.get("warehouse"), warehouse));
                 }  
	     	   

                 Predicate[] p = new Predicate[list.size()];
                 return cb.and(list.toArray(p));
             }
         };
 
 
         Page<Receipt> apply = this.receiptRepository.findAll(specification, pageable);
        return new ResponseEntity<Page<Receipt>>(apply, HttpStatus.OK);
    } 

    /**
     * @api {GET} /receipt/{id} 收发货单详细
     * @apiGroup receipt
     * @apiVersion 0.0.1
     * @apiDescription 收发货单详细
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /receipt/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {Integer} orderState 订单状态
     * @apiSuccess (200) {String} orderStateTxt 订单状态Txt
     * @apiSuccess (200) {Integer} orderType 订单类型
     * @apiSuccess (200) {String} orderTypeTxt 订单类型Txt
     * @apiSuccess (200) {BigDecimal} fee 费用
     * @apiSuccess (200) {BigDecimal} num 件数
     * @apiSuccess (200) {BigDecimal} tonNum 吨数

     * @apiSuccess (200) {Object} warehouseSiteData 仓库对象
     * @apiSuccess (200) {String} warehouseSiteData.id id
     * @apiSuccess (200) {String} warehouseSiteData.name 名称
     *
     * @apiSuccess (200) {Object} warehouseAreaData 库区对象
     * @apiSuccess (200) {String} warehouseAreaData.id id
     * @apiSuccess (200) {String} warehouseAreaData.name 名称
     *
     * @apiSuccess (200) {Object} warehouseData 库位对象
     * @apiSuccess (200) {String} warehouseData.id id
     * @apiSuccess (200) {String} warehouseData.name 名称

     * @apiSuccess (200) {Object} customerData 客户对象
     * @apiSuccess (200) {String} customerData.id id
     * @apiSuccess (200) {String} customerData.name 名称
     *
     * @apiSuccess (200) {Object} productData 物资对象
     * @apiSuccess (200) {String} productData.id id
     * @apiSuccess (200) {String} productData.productName 名称
     *
     * @apiSuccess (200) {Object} entrustOrderDetailData 委托单明细对象
     * @apiSuccess (200) {String} entrustOrderDetailData.id id
     * @apiSuccess (200) {String} entrustOrderDetailData.vehicleNo 车号
     * @apiSuccess (200) {BigDecimal} entrustOrderDetailData.num 件数
     * @apiSuccess (200) {BigDecimal} entrustOrderDetailData.tonNum 吨数
     *
     * @apiSuccess (200) {Boolean=true,false} hasMeteringConfirm 计量单是否有一个确认过
     *
     * @apiSuccess (200) {Object} planData 计划对象
     * @apiSuccess (200) {BigDecimal} planData.planNum 数量
     * @apiSuccess (200) {BigDecimal} planData.planNum 吨数

     * @apiSuccess (200) {Object} noMeterData 未计量对象
     * @apiSuccess (200) {BigDecimal} noMeterData.meterNum 数量
     * @apiSuccess (200) {BigDecimal} noMeterData.meterTonNum 吨数
     *
     * @apiSuccess (200) {Object} meterData 已计量
     * @apiSuccess (200) {BigDecimal} meterData.meterNum 数量
     * @apiSuccess (200) {BigDecimal} meterData.meterTonNum 吨数
     *
     * @apiSuccess (200) {String} yardman 调度员(收货单新增)
     * @apiSuccess (200) {String} tallyMan 理货员(收货单新增)
     * @apiSuccess (200) {String} loadMan 装卸工(收、发货单确认)
     * @apiSuccess (200) {String} auditMan 稽核员(收货单确认)
     * @apiSuccess (200) {Boolean} handWarehouse 是否分配库区/库层
     * - true:已分配
     * - false:未分配
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        Receipt entity = this.receiptRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    /**
     * @api {POST} /receipt 收发货单添加
     * @apiGroup receipt
     * @apiVersion 0.0.1
     * @apiDescription 收发货单添加
     * @apiParam {String} entrustOrderDetailId 委托单明细Id
     * @apiParam {Integer} orderType 订单类型（入库 出库）
     * - IN_STOCK(14010, "入库"),
     * - OUT_STOCK(14020, "出库");
     * @apiParam {String} yardman 调度员(收货单新增)
     * @apiParam {String} tallyMan 理货员(收货单新增)
     *
     * @apiParam {String} remark 备注
     * @apiParam {String} createMan 操作人
     * @apiParam {String} createManId 操作人ID
     * @apiParamExample 请求明文：
     * /customer
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(Receipt entity,
                                  String entrustOrderDetailId) {
        HttpStatusContent status = null;

        entity.setHasMeteringConfirm(false);
        entity.setHandWarehouse(false);
        if(!ReceiptOrderType.isExist(entity.getOrderType())){
            status = new HttpStatusContent(OutputState.FAIL, "订单类型参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String orderNo = "";
        String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format((new Date()).getTime());
        if(entity.getOrderType().equals(ReceiptOrderType.IN_STOCK.getValue())){
            orderNo = "RK";//入库
        }else if(entity.getOrderType().equals(ReceiptOrderType.OUT_STOCK.getValue())){
            orderNo = "CK";//出库
        }
        orderNo = orderNo + yyyyMMdd;
        String newStrSN = String.format("%05d", 1);//每天默认00001
        //获取不同类型最大序号数据
        Receipt maxOrderNoEntity = this.receiptRepository.findEveryDayMaxOrderNo(yyyyMMdd,entity.getOrderType());
        if(maxOrderNoEntity != null && EmptyUtil.isNotEmpty(maxOrderNoEntity.getOrderNo())){
            String maxOrderNo = maxOrderNoEntity.getOrderNo();
            String serialNumber = maxOrderNo.substring(maxOrderNo.length() - 5, maxOrderNo.length());

            newStrSN = Integer.toString(Integer.valueOf(serialNumber) + 1);
            if(newStrSN.length()<5){
                //不足5位前面补0
                newStrSN = String.format("%05d", Integer.valueOf(newStrSN));//25为int型
            }
        }
        orderNo = orderNo+newStrSN;
        entity.setOrderNo(orderNo);

        //本年第几周
        Integer weekthOfYear = DateUtil.getWeekthOfYear(new Date());
        String strWeek = weekthOfYear.toString();
        if(strWeek.length()==1){
            strWeek = "0"+strWeek;//不足2位前面补0
        }
        entity.setYearWeek(strWeek);
        //本周单子序号
        String weekNo = "1000";//默认从1000开始
        Receipt everyYearWeekMaxWeekNo = this.receiptRepository.findEveryYearWeekMaxWeekNo(DateUtil.getYear(new Date()).toString(),
                strWeek, entity.getOrderType());
        if(everyYearWeekMaxWeekNo != null
            && EmptyUtil.isNotEmpty(everyYearWeekMaxWeekNo.getWeekNo())){
            int i = Integer.valueOf(everyYearWeekMaxWeekNo.getWeekNo()) + 1;
            weekNo = Integer.valueOf(i).toString();
        }
        entity.setWeekNo(weekNo);

        entity.setOrderState(ReceiptOrderState.NEW_CREATE.getValue());
        entity.setIsSettlement(false);

        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(EmptyUtil.isEmpty(entrustOrderDetailId)){
            status = new HttpStatusContent(OutputState.FAIL, "缺少委托单明细参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        EntrustOrderDetail entrustOrderDetail = this.entrustOrderDetailRepository.findOne(entrustOrderDetailId);
        if(entrustOrderDetail == null) {
            status = new HttpStatusContent(OutputState.FAIL, "委托单明细不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(entrustOrderDetail.getIsReceipt() != null
                && entrustOrderDetail.getIsReceipt()) {
            status = new HttpStatusContent(OutputState.FAIL, "委托单明细已经生成过了！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(!entrustOrderDetail.getEntrustOrder().getOrderState()
                .equals(EntrustOrderState.CONFIRM.getValue())
                && !entrustOrderDetail.getEntrustOrder().getOrderState()
                .equals(EntrustOrderState.SENDING.getValue())
                && !entrustOrderDetail.getEntrustOrder().getOrderState()
                        .equals(EntrustOrderState.RECEIVING.getValue())) {
            status = new HttpStatusContent(OutputState.FAIL, "确认中或者收发货中的委托单才能操作！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        entrustOrderDetail.setIsReceipt(true);//修改明细(可以通过关联修改)
        entity.setEntrustOrderDetail(entrustOrderDetail);
        entity.setEntrustOrder(entrustOrderDetail.getEntrustOrder());

        if(entrustOrderDetail.getEntrustOrder() == null
                || entrustOrderDetail.getEntrustOrder().getCustomer() ==null
                || !SettlementMethod.isExist(entrustOrderDetail.getEntrustOrder().getCustomer().getSettlementMethod())) {
            status = new HttpStatusContent(OutputState.FAIL, "委托单客户缺少结算方式！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Integer settlementMethod = entrustOrderDetail.getEntrustOrder().getCustomer().getSettlementMethod();
        if(settlementMethod.equals(SettlementMethod.MONTH_SETTLEMENT.getValue())){
            entity.setIsSettlement(false);//月结需要生成结算单
        }else {
            entity.setIsSettlement(true);
        }
        entity.setSettlementMethod(settlementMethod);
        entity.setEntrustOrderType(entrustOrderDetail.getEntrustOrder().getOrderType());
        entity.setWarehouseSite(entrustOrderDetail.getEntrustOrder().getWarehouse());
        entity.setCustomer(entrustOrderDetail.getEntrustOrder().getCustomer());
        entity.setProduct(entrustOrderDetail.getProduct());

        //计算费用
        //如果是未知客户且入库就不判断(正常客户或者非入库都要判断),入库未知客户确认分配时再改单价和费用用途
        if(entity.getCustomer().getIsUnknown() == null
                || !entity.getCustomer().getIsUnknown()
                || !entity.getOrderType().equals(ReceiptOrderType.IN_STOCK.getValue())){
            CostSettinsOpType queryOpType = null;
            if(entity.getOrderType().equals(ReceiptOrderType.IN_STOCK.getValue())){
                queryOpType = CostSettinsOpType.IN_STOCK;//入库
            }else if(entity.getOrderType().equals(ReceiptOrderType.OUT_STOCK.getValue())){
                queryOpType = CostSettinsOpType.OUT_STOCK;//出库
            } else {
                status = new HttpStatusContent(OutputState.FAIL, "单据类型参数错误！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Map<String, Object> unitPriceMap = this.costSettinsService.getUnitPrice(entity.getCustomer().getId(),
                    entity.getProduct().getCate().getId(), queryOpType);
            if(unitPriceMap.get("status").equals("no")){
                status = new HttpStatusContent(OutputState.FAIL,unitPriceMap.get("message").toString());
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            BigDecimal unitPrice = BigDecimal.valueOf(Double.valueOf(unitPriceMap.get("unitPrice").toString()));
            Integer feePurpose = Integer.valueOf(unitPriceMap.get("feePurpose").toString());

            entity.setUnitFee(unitPrice);
            entity.setFeePurpose(feePurpose);
        } 

        //记录新建状态日志
        ReceiptLog log = new ReceiptLog();
        log.setCreateMan(entity.getCreateMan());
        log.setCreateManId(entity.getCreateManId());
        log.setOrderState(entity.getOrderState());
        log.setReceipt(entity);
        log.setRemark(ReceiptOrderState.getTxtByValue(entity.getOrderState()));

        Set<ReceiptLog> logSet = new HashSet<>();
        logSet.add(log);
        entity.setReceiptLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        entity = this.receiptService.add(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    /**
     * @api {POST} /receipt 收发货单修改
     * @apiGroup receipt
     * @apiVersion 0.0.1
     * @apiDescription 收发货单修改
     * @apiParam {String} id 主键id
     * @apiParam {String} entrustOrderDetailId 委托单明细Id
     * @apiParam {String} remark 备注
     * @apiParamExample 请求明文：
     * /customer
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 Receipt entity,
                                 String entrustOrderDetailId,
                                 String createMan,
                                 String createManId) {

        HttpStatusContent status = null;
        entity.setOrderState(null);//订单状态不能通过此接口修改
        entity.setFee(null);
        entity.setOrderNo(null);
        entity.setHasMeteringConfirm(null);

        Receipt receipt = this.receiptRepository.getOne(id);
        if (receipt == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }

        if(!ReceiptOrderType.isExist(entity.getOrderType())){
            status = new HttpStatusContent(OutputState.FAIL, "订单类型参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //修改委托单id,且跟自身不相等
        if(EmptyUtil.isNotEmpty(entrustOrderDetailId)
                && !entrustOrderDetailId.equals(receipt.getEntrustOrderDetail().getId())){
            EntrustOrderDetail entrustOrderDetail = this.entrustOrderDetailRepository.findOne(entrustOrderDetailId);
            if(entrustOrderDetail == null) {
                status = new HttpStatusContent(OutputState.FAIL, "委托单明细不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(entrustOrderDetail.getIsReceipt() != null
                    && entrustOrderDetail.getIsReceipt()) {
                status = new HttpStatusContent(OutputState.FAIL, "委托单明细已经生成过了！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            entrustOrderDetail.setIsReceipt(true);//修改明细(可以通过关联修改)
            entity.setEntrustOrderDetail(entrustOrderDetail);
            entity.setEntrustOrder(entrustOrderDetail.getEntrustOrder());

            if(entrustOrderDetail.getEntrustOrder() == null
                    || entrustOrderDetail.getEntrustOrder().getCustomer() ==null
                    || !SettlementMethod.isExist(entrustOrderDetail.getEntrustOrder().getCustomer().getSettlementMethod())) {
                status = new HttpStatusContent(OutputState.FAIL, "委托单客户缺少结算方式！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Integer settlementMethod = entrustOrderDetail.getEntrustOrder().getCustomer().getSettlementMethod();
            if(settlementMethod.equals(SettlementMethod.MONTH_SETTLEMENT.getValue())){
                entity.setIsSettlement(false);//月结需要生成结算单
            }else {
                entity.setIsSettlement(true);
            }
            entity.setSettlementMethod(settlementMethod);
            entity.setEntrustOrderType(entrustOrderDetail.getEntrustOrder().getOrderType());
            entity.setWarehouseSite(entrustOrderDetail.getEntrustOrder().getWarehouse());
            entity.setCustomer(entrustOrderDetail.getEntrustOrder().getCustomer());
            entity.setProduct(entrustOrderDetail.getProduct());
        }

        //已分配库位的收发货单据不能修改
        if (receipt.getWarehouse() != null) {
            status = new HttpStatusContent(OutputState.FAIL, "已分配库位不能修改！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }

        ReceiptLog log = new ReceiptLog();
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(null);
        log.setReceipt(receipt);

        List<Map<String, Object>> maps = ClassAttrValCompare.newCompareToOld(receipt, entity);
        //log.setRemark(maps.toString());
        log.setRemark("修改");
        Set<ReceiptLog> logSet = new HashSet<>();
        logSet.add(log);
        receipt.setReceiptLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        ObjectCopyUtil<Receipt> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, receipt);
        entity = this.receiptRepository.save(receipt);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    /**
     * @api {POST} /receipt/orderState/change 收发货单状态变更
     * @apiGroup receipt
     * @apiVersion 0.0.1
     * @apiDescription 收发货单状态变更
     * @apiParam {String} id 主键id
     * @apiParam {Integer} orderState 订单状态
     * - HAD_METERING(90030, "已计量"),
     * - PASS(90060, "已放行"),(针对已确认的发货单)
     * - INVALID(-90000, "作废");
     * @apiParam {String} [warehouseLevelId] 库层Id(第4级)
     * @apiParam {String} [warehouseAreaId] 库区Id(第2级)
     * @apiParam {Boolean=true} [handWarehouse] 是否分配库区\库层
     *
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /receipt/orderState/change
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json", value = "/orderState/change")
    public ResponseEntity<?> orderStateChange(String id,
                                              Integer orderState,
                                              String createMan,
                                              String createManId,
                                              String warehouseLevelId,
                                              String warehouseAreaId,
                                              Boolean handWarehouse) {
        HttpStatusContent status = null;

        if (EmptyUtil.isEmpty(id)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少id参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)) {
            status = new HttpStatusContent(OutputState.FAIL, "操作人和操作人Id必传！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        Receipt originEntity = this.receiptRepository.findOne(id);
        if (originEntity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        //变更前状态
        Integer preState = originEntity.getOrderState();

        if(orderState !=null && orderState.equals(preState)){
            status = new HttpStatusContent(OutputState.FAIL,
                    "当前状态已经是："+ ReceiptOrderState.getTxtByValue(preState));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if(ReceiptOrderState.CONFIRM.getValue().equals(orderState)){
            status = new HttpStatusContent(OutputState.FAIL, "不能做确认操作！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(orderState !=null && !ReceiptOrderState.isExist(orderState)){
            status = new HttpStatusContent(OutputState.FAIL, "状态参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(ReceiptOrderState.PASS.getValue().equals(orderState)){
            if(!originEntity.getOrderType().equals(ReceiptOrderType.OUT_STOCK.getValue())) {
                status = new HttpStatusContent(OutputState.FAIL, "发货单才能做放行操作！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(!originEntity.getOrderState().equals(ReceiptOrderState.CONFIRM.getValue())) {
                status = new HttpStatusContent(OutputState.FAIL, "已确认才能做放行操作！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        String logRemark = "";

        //收、发货单如果是，状态改为 计量中，不修改，生成计量单时修改。
        if(ReceiptOrderState.IN_METERING.getValue().equals(orderState)) {
            status = new HttpStatusContent(OutputState.FAIL, "不能修改状态为计量中！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(orderState!=null && ReceiptOrderState.isExist(orderState)){
            logRemark = "状态由"
                    +ReceiptOrderState.getTxtByValue(preState)
                    +"变为"
                    + ReceiptOrderState.getTxtByValue(orderState);
            originEntity.setOrderState(orderState);//1.修改状态
        }

        //分库区或分库层(分配库位/库区)
        if(handWarehouse != null && handWarehouse) {
            if (originEntity.getHandWarehouse() != null
                    && originEntity.getHandWarehouse()) {
                status = new HttpStatusContent(OutputState.FAIL, "已经分配过了！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            //收货单 分配库区
            if(originEntity.getOrderType().equals(ReceiptOrderType.IN_STOCK.getValue())){
                if (EmptyUtil.isEmpty(warehouseAreaId)) {
                    status = new HttpStatusContent(OutputState.FAIL, "缺少库区参数！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                Warehouse warehouse = this.warehouseRepository.findOne(warehouseAreaId);
                if (warehouse == null || warehouse.getLevelOrder() != 2) {
                    status = new HttpStatusContent(OutputState.FAIL, "库区不存在！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if(warehouse.getIsUse() != null && !warehouse.getIsUse()){
                    status = new HttpStatusContent(OutputState.FAIL,
                            "库区："+warehouse.getName()
                                    +",未启用");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }

                originEntity.setOrderState(ReceiptOrderState.ALLOCATION.getValue());
                originEntity.setWarehouseArea(warehouse);//库区
                originEntity.setHandWarehouse(true);
                orderState = ReceiptOrderState.ALLOCATION.getValue();
                logRemark = logRemark + ".分配库区:" + warehouse.getName();
            } else if(originEntity.getOrderType().equals(ReceiptOrderType.OUT_STOCK.getValue())){
                //发货单 分配库层
                if (EmptyUtil.isEmpty(warehouseLevelId)) {
                    status = new HttpStatusContent(OutputState.FAIL, "缺少库层参数！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                Warehouse warehouse = this.warehouseRepository.findOne(warehouseLevelId);
                if (warehouse == null || warehouse.getLevelOrder() != 4) {
                    status = new HttpStatusContent(OutputState.FAIL, "库层不存在！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if(warehouse.getIsUse() != null && !warehouse.getIsUse()){
                    status = new HttpStatusContent(OutputState.FAIL,
                            "库层："+warehouse.getName()
                                    +",未启用");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                originEntity.setWarehouseLevel(warehouse);//库层
                originEntity.setWarehouse(warehouse.getParent());//库位
                originEntity.setWarehouseArea(warehouse.getParent().getParent());//库区
                originEntity.setHandWarehouse(true);
                originEntity.setOrderState(ReceiptOrderState.ALLOCATION.getValue());
                orderState = ReceiptOrderState.ALLOCATION.getValue();
                logRemark = logRemark + ".分配库层:" + warehouse.getName(); 
            }
        }

        ReceiptLog log = new ReceiptLog();
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(orderState);
        log.setReceipt(originEntity);
        log.setRemark(logRemark);
        Set<ReceiptLog> logSet = new HashSet<>();
        logSet.add(log);
        originEntity.setReceiptLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        if(this.receiptRepository.save(originEntity) != null){
            status = new HttpStatusContent(OutputState.SUCCESS);
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
        }
        status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @api {POST} /receipt/orderState/feeConfirm 收发货单收费确认
     * @apiGroup receipt
     * @apiVersion 0.0.1
     * @apiDescription 收发货单收费确认
     * @apiParam {String} id 主键id
     *
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParam {String} [warehouseLevelId] 库层id(收货单 分配库层)
     *
     * @apiParam {String} loadMan 装卸工(收、发货单确认)
     * @apiParam {String} auditMan 稽核员(收货单确认)
     * @apiParam {String} [customerId] 客户id(未知客户:收货单确认)
     *
     * @apiParamExample 请求明文：
     * /receipt/orderState/feeConfirm
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json", value = "/orderState/feeConfirm")
    public ResponseEntity<?> feeConfirm(String id,
                                        String createMan,
                                        String createManId,
                                        String warehouseLevelId,
                                        String loadMan,
                                        String auditMan,
                                        String customerId) {
        HttpStatusContent status = null;

        if (EmptyUtil.isEmpty(id)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少id参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)) {
            status = new HttpStatusContent(OutputState.FAIL, "操作人和操作人Id必传！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        Receipt originEntity = this.receiptRepository.findOne(id);
        if (originEntity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        //发货单才判断之前数据是否有库层,是否库位锁定了
        if (originEntity.getOrderType().equals(ReceiptOrderType.OUT_STOCK.getValue())) {
            if (originEntity.getWarehouseLevel() == null) {
                status = new HttpStatusContent(OutputState.FAIL, "没有库层！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            if (originEntity.getWarehouseLevel().getLockState()!=null
                    && originEntity.getWarehouseLevel().getLockState()) {
                status = new HttpStatusContent(OutputState.FAIL, "库层已锁定,无法操作！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(originEntity.getWarehouseLevel().getIsUse() == null || !originEntity.getWarehouseLevel().getIsUse()){
                status = new HttpStatusContent(OutputState.FAIL,
                        "库层："+originEntity.getWarehouseLevel().getName()
                                +",未启用");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if (EmptyUtil.isNotEmpty(loadMan)) {
            originEntity.setLoadMan(loadMan);
        }
        if (EmptyUtil.isNotEmpty(auditMan)) {
            originEntity.setAuditMan(auditMan);
        }

        Customer newCustomer = null;//分配的新客户,有就修改
        //收货单 分配库层
        if(originEntity.getOrderType().equals(ReceiptOrderType.IN_STOCK.getValue())){
            if (EmptyUtil.isEmpty(warehouseLevelId)) {
                status = new HttpStatusContent(OutputState.FAIL, "缺少库层参数！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Warehouse warehouse = this.warehouseRepository.findOne(warehouseLevelId);
            if (warehouse == null || warehouse.getLevelOrder()!=4) {
                status = new HttpStatusContent(OutputState.FAIL, "库层不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (warehouse.getLockState()!=null
                    && warehouse.getLockState()) {
                status = new HttpStatusContent(OutputState.FAIL, "库层已锁定,无法操作！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            originEntity.setWarehouseLevel(warehouse);//库层
            originEntity.setWarehouse(warehouse.getParent());//库位

            if(originEntity.getCustomer().getIsUnknown() != null
                    && originEntity.getCustomer().getIsUnknown()){
                if (EmptyUtil.isEmpty(customerId)) {
                    status = new HttpStatusContent(OutputState.FAIL, "未知客户,收货确认时要分配客户！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                Customer customer = this.customerRepository.getOne(customerId);
                if (EmptyUtil.isEmpty(customer)) {
                    status = new HttpStatusContent(OutputState.FAIL, "客户不存在！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (customer.getIsUnknown()!=null
                        && customer.getIsUnknown()) {
                    status = new HttpStatusContent(OutputState.FAIL, "分配的客户不能是未知客户！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                originEntity.setCustomer(customer);//重新设置客户
                newCustomer = customer;

                //计算费用
                //如果是未知客户确认分配时再改单价和费用用途(且收货)
                CostSettinsOpType queryOpType = null;
                if(originEntity.getOrderType().equals(ReceiptOrderType.IN_STOCK.getValue())){
                    queryOpType = CostSettinsOpType.IN_STOCK;//入库
                }else if(originEntity.getOrderType().equals(ReceiptOrderType.OUT_STOCK.getValue())){
                    queryOpType = CostSettinsOpType.OUT_STOCK;//出库
                } else {
                    status = new HttpStatusContent(OutputState.FAIL, "单据类型参数错误！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }

                Map<String, Object> unitPriceMap = this.costSettinsService.getUnitPrice(newCustomer.getId(),
                        originEntity.getProduct().getCate().getId(), queryOpType);
                if(unitPriceMap.get("status").equals("no")){
                    status = new HttpStatusContent(OutputState.FAIL,unitPriceMap.get("message").toString());
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                BigDecimal unitPrice = BigDecimal.valueOf(Double.valueOf(unitPriceMap.get("unitPrice").toString()));
                Integer feePurpose = Integer.valueOf(unitPriceMap.get("feePurpose").toString());

                originEntity.setUnitFee(unitPrice);
                originEntity.setFeePurpose(feePurpose);
            }
        }

        //变更前状态
        Integer preState = originEntity.getOrderState();

        if(!ReceiptOrderState.HAD_METERING.getValue().equals(preState)){
            status = new HttpStatusContent(OutputState.FAIL, "已计量才能确认！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        originEntity.setOrderState(ReceiptOrderState.CONFIRM.getValue());//1.修改委托单状态

        String logRemark = "状态由"
                +ReceiptOrderState.getTxtByValue(preState)
                +"变为"
                + ReceiptOrderState.getTxtByValue(ReceiptOrderState.CONFIRM.getValue());

        //状态改为确认(加或减库存),收费确认
        //统计计量单，件数和吨数
        MeteringReceiptOrder order = new MeteringReceiptOrder();
        Receipt queryReceipt = new Receipt();
        queryReceipt.setId(originEntity.getId());
        order.setReceipt(queryReceipt);
        Example<MeteringReceiptOrder> example = Example.of(order);
        List<MeteringReceiptOrder> orders = this.meteringReceiptOrderRepository.findAll(example);
        if(orders == null || orders.size() <= 0){
            status = new HttpStatusContent(OutputState.FAIL, "没有计量信息！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //确认过的计量单列表
        List<MeteringReceiptOrder> confirmOrders = new ArrayList<>();
        //新建的计量单列表
        List<MeteringReceiptOrder> newCreateOrders = new ArrayList<>();
        //修改的计量单列表
        List<MeteringReceiptOrder> updateOrders = new ArrayList<>();
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).getOrderState().equals(MeteringReceiptOrderState.CONFIRM.getValue())){
                confirmOrders.add(orders.get(i));
            }
            else if(orders.get(i).getOrderState().equals(MeteringReceiptOrderState.NEW_CREATE.getValue())){
                newCreateOrders.add(orders.get(i));
            }
        }

        if(confirmOrders.size() <= 0){
            status = new HttpStatusContent(OutputState.FAIL, "计量单至少有一个确认过！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(newCreateOrders.size() > 0){
            status = new HttpStatusContent(OutputState.FAIL,
                    "还有"+newCreateOrders.size()+"条计量单未处理");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BigDecimal totalNum = BigDecimal.ZERO;//合计件数
        BigDecimal totalTonNum = BigDecimal.ZERO;//合计吨数
        BigDecimal meterTotalFee = BigDecimal.ZERO;//合计费用

        for(int i=0;i<confirmOrders.size();i++){
            totalNum = totalNum.add(confirmOrders.get(i).getNum());
            totalTonNum = totalTonNum.add(confirmOrders.get(i).getTonNum());
            if(confirmOrders.get(i).getFee()!=null){
                meterTotalFee = meterTotalFee.add(confirmOrders.get(i).getFee());
            }
        }

        //1.如果：是理重,费用 = 委托单明细吨数 * 委托单明细单价,计量单费用不管
        if(CostSettinsFeePurpose.THEORY.getValue().equals(originEntity.getFeePurpose())) {
            BigDecimal totalFee = originEntity.getEntrustOrderDetail().getTonNum().multiply(originEntity.getUnitFee());
            originEntity.setFee(totalFee);
        }//2.如果：是实重
        else if(CostSettinsFeePurpose.FACT.getValue().equals(originEntity.getFeePurpose())) {
            //实重：1.正常客户情况，合计计量单费用
            if(newCustomer == null){
                originEntity.setFee(meterTotalFee);
            } else{
                //实重：2.未知客户分客户的情况,修改每个计量的费用,再合计费用
                BigDecimal totalFee = BigDecimal.ZERO;
                for(int i=0;i<confirmOrders.size();i++){
                    MeteringReceiptOrder order1 = confirmOrders.get(i);
                    BigDecimal multiplyFee = order1.getTonNum().multiply(originEntity.getUnitFee());
                    order1.setFee(multiplyFee);
                    updateOrders.add(order1);
                    totalFee = totalFee.add(multiplyFee);
                }
                originEntity.setFee(totalFee);
            }
        }

        //入库单确认时，库位容量判断
        if(ReceiptOrderType.IN_STOCK.getValue().equals(originEntity.getOrderType())
            && originEntity.getWarehouse()!=null){
            BigDecimal numMaxCapacity = originEntity.getWarehouse().getNumMaxCapacity();
            BigDecimal tonNumMaxCapacity = originEntity.getWarehouse().getTonNumMaxCapacity();
            //统计库存表，此库位已存数量和吨数
            Map<String, BigDecimal> stockNumAndTon = this.warehouseStockService.getStockNumAndTon(
                    originEntity.getWarehouse().getId(),
                    null, null, null,
                    null,
                    null);
            BigDecimal stockSumNum = stockNumAndTon.get("stockSumNum");//件数
            BigDecimal stockSumTonNum = stockNumAndTon.get("stockSumTonNum");//吨数
            //库位最大容量件数为Null不判断了
            if(numMaxCapacity != null
                    && stockSumNum.add(totalNum).compareTo(numMaxCapacity)>0){
                status = new HttpStatusContent(OutputState.FAIL,
                        "件数:库存"+stockSumNum+
                                "+本次确认"+totalNum
                                +"已超过库位件数最大容量"+numMaxCapacity
                                +"！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //库位最大容量吨数为Null不判断了
            if(tonNumMaxCapacity != null
                    && stockSumTonNum.add(totalTonNum).compareTo(tonNumMaxCapacity)>0){
                status = new HttpStatusContent(OutputState.FAIL,
                        "吨数数:库存"+stockSumTonNum+
                                "+本次确认"+totalTonNum
                                +"已超过库位吨数最大容量"+tonNumMaxCapacity
                                +"！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        originEntity.setNum(totalNum);//确认才计算件数
        originEntity.setTonNum(totalTonNum);//确认才计算吨数

        ReceiptLog log = new ReceiptLog();
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(ReceiptOrderState.CONFIRM.getValue());
        log.setReceipt(originEntity);
        log.setRemark(logRemark);
        Set<ReceiptLog> logSet = new HashSet<>();
        logSet.add(log);
        originEntity.setReceiptLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        if(this.receiptService.feeConfirm(originEntity,totalNum,
                totalTonNum,createMan,
                createManId,newCustomer,updateOrders) != null){
            status = new HttpStatusContent(OutputState.SUCCESS);
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
        }
        status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

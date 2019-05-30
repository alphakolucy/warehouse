package com.warehouse.controller;

import com.warehouse.model.*;
import com.warehouse.service.MeteringReceiptOrderService;
import com.warehouse.service.repository.CraneRepository;
import com.warehouse.service.repository.MeteringReceiptOrderRepository;
import com.warehouse.service.repository.ReceiptRepository;
import com.warehouse.service.repository.SteelyardRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.*;
import com.warehouse.util.tools.ClassAttrValCompare;
import com.warehouse.util.tools.EmptyUtil;
import com.warehouse.util.tools.ObjectCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 计量单
 */
@RestController
@RequestMapping("/meteringReceiptOrder")
public class MeteringReceiptOrderController {
    @Autowired
    private MeteringReceiptOrderRepository meteringReceiptOrderRepository;
    @Autowired
    private CraneRepository craneRepository;
    @Autowired
    private SteelyardRepository steelyardRepository;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private MeteringReceiptOrderService meteringReceiptOrderService;

    /**
     * @api {GET} /meteringReceiptOrder 计量单列表
     * @apiGroup meteringReceiptOrder
     * @apiVersion 0.0.1
     * @apiDescription 计量单列表
     * @apiParam {String} [orderNo] 单号(支持模糊查询)
     * @apiParam {String} [craneId] 吊车Id
     * @apiParam {String} [steelyardId] 秤Id
     * @apiParam {String} [receiptId] 收发货单Id
     * @apiParam {String} [orderState] 订单状态
     * - NEW_CREATE(11010, "新建"),
     * - CONFIRM(11020, "确认"),
     * - INVALID(-11000, "作废");
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /meteringReceiptOrder?opType=40010&sort=id,asc
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     *
     * @apiSuccess (200) {Integer} orderState 订单状态
     * @apiSuccess (200) {String} orderStateTxt 订单状态Txt
     *
     * @apiSuccess (200) {BigDecimal} num 实收件数
     * @apiSuccess (200) {BigDecimal} tonNum 实收吨数
     *
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     * @apiSuccess (200) {BigDecimal} fee 实收费用
     *
     * @apiSuccess (200) {Object} receiptData 收发货单对象
     * @apiSuccess (200) {String} receiptData.id id
     * @apiSuccess (200) {String} receiptData.orderNo 单号
     * @apiSuccess (200) {String} receiptData.productMaker 厂家
     * @apiSuccess (200) {String} receiptData.productName 物资名称
     * @apiSuccess (200) {String} receiptData.productMaterial 材料
     * @apiSuccess (200) {String} receiptData.productModel 型号
     * @apiSuccess (200) {String} receiptData.productSpec 规格
     * @apiSuccess (200) {String} receiptData.productRationale 理重
     * @apiSuccess (200) {String} receiptData.productUnit 单位
     *
     * @apiSuccess (200) {Object} craneData 吊车对象
     * @apiSuccess (200) {String} craneData.id id
     * @apiSuccess (200) {String} craneData.craneNo 吊车编号
     *
     * @apiSuccess (200) {Object} steelyardData 秤对象
     * @apiSuccess (200) {String} steelyardData.id id
     * @apiSuccess (200) {String} steelyardData.steelyardNo 秤编号
     * @apiSuccess (200) {String} vehicleNo 车号(发货单计量才有)
     * @apiSuccess (200) {String} tallyMan 理货员(新增)
     * @apiSuccess (200) {String} meteringMan 计量员(新增)
     * @apiSuccess (200) {String} trafficMan 行车工(新增)
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<MeteringReceiptOrder>> findAll(MeteringReceiptOrder entity,
                                                    @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable,
                                                     String receiptId,
                                                     String craneId,
                                                     String steelyardId) {
        //收发货单Id查询
        if (EmptyUtil.isNotEmpty(receiptId)) {
            Receipt receipt = new Receipt();
            receipt.setId(receiptId);
            entity.setReceipt(receipt);
        }
        //吊车Id查询
        if (EmptyUtil.isNotEmpty(craneId)) {
            Crane crane = new Crane();
            crane.setId(craneId);
            entity.setCrane(crane);
        }
        //秤Id查询
        if (EmptyUtil.isNotEmpty(steelyardId)) {
            Steelyard steelyard = new Steelyard();
            steelyard.setId(steelyardId);
            entity.setSteelyard(steelyard);
        }

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //模糊方式查
                .withMatcher("orderNo", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<MeteringReceiptOrder> example = Example.of(entity,matcher);

        Page<MeteringReceiptOrder> apply = this.meteringReceiptOrderRepository.findAll(example, pageable);
        return new ResponseEntity<Page<MeteringReceiptOrder>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /meteringReceiptOrder/{id} 单个计量单
     * @apiGroup meteringReceiptOrder
     * @apiVersion 0.0.1
     * @apiDescription 单个计量单
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /meteringReceiptOrder/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     *
     * @apiSuccess (200) {Integer} orderState 订单状态
     * @apiSuccess (200) {String} orderStateTxt 订单状态Txt
     *
     * @apiSuccess (200) {BigDecimal} num 实收件数
     * @apiSuccess (200) {BigDecimal} tonNum 实收吨数
     *
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     * @apiSuccess (200) {BigDecimal} fee 实收费用
     *
     * @apiSuccess (200) {Object} receiptData 收发货单对象
     * @apiSuccess (200) {String} receiptData.id id
     * @apiSuccess (200) {String} receiptData.orderNo 单号
     * @apiSuccess (200) {String} receiptData.productMaker 厂家
     * @apiSuccess (200) {String} receiptData.productName 物资名称
     * @apiSuccess (200) {String} receiptData.productMaterial 材料
     * @apiSuccess (200) {String} receiptData.productModel 型号
     * @apiSuccess (200) {String} receiptData.productSpec 规格
     * @apiSuccess (200) {String} receiptData.productRationale 理重
     * @apiSuccess (200) {String} receiptData.productUnit 单位
     *
     * @apiSuccess (200) {Object} craneData 吊车对象
     * @apiSuccess (200) {String} craneData.id id
     * @apiSuccess (200) {String} craneData.craneNo 吊车编号
     *
     * @apiSuccess (200) {Object} steelyardData 秤对象
     * @apiSuccess (200) {String} steelyardData.id id
     * @apiSuccess (200) {String} steelyardData.steelyardNo 秤编号
     *
     * @apiSuccess (200) {String} vehicleNo 车号(发货单计量才有)
     * @apiSuccess (200) {String} tallyMan 理货员(新增)
     * @apiSuccess (200) {String} meteringMan 计量员(新增)
     * @apiSuccess (200) {String} trafficMan 行车工(新增)
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        MeteringReceiptOrder entity = this.meteringReceiptOrderRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MeteringReceiptOrder>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /meteringReceiptOrder 计量单添加
     * @apiGroup meteringReceiptOrder
     * @apiVersion 0.0.1
     * @apiDescription 计量单添加
     * @apiParam {String} craneId 吊车Id
     * @apiParam {String} steelyardId 秤Id
     * @apiParam {String} receiptId 收发货单Id
     *
     * @apiParam {BigDecimal} num 实收件数
     * @apiParam {BigDecimal} tonNum 实收吨数

     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParam {String} vehicleNo 车号(发货单计量才有)
     *
     * @apiParam {String} tallyMan 理货员(新增)
     * @apiParam {String} meteringMan 计量员(新增)
     * @apiParam {String} trafficMan 行车工(新增)

     * @apiParamExample 请求明文：
     * /meteringReceiptOrder
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(MeteringReceiptOrder entity,
                                  String receiptId,
                                  String craneId,
                                  String steelyardId) {

        HttpStatusContent status = null;

        String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format((new Date()).getTime());
        String orderNo = "JL" + yyyyMMdd;
        String newStrSN = String.format("%05d", 1);//每天默认00001
        
        //获取最大序号
        MeteringReceiptOrder maxOrderNoEntity = this.meteringReceiptOrderRepository.findEveryDayMaxOrderNo(yyyyMMdd);
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
        entity.setOrderState(MeteringReceiptOrderState.NEW_CREATE.getValue());//默认状态新建

        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(EmptyUtil.isEmpty(receiptId)){
            status = new HttpStatusContent(OutputState.FAIL, "缺少收发货单参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(EmptyUtil.isEmpty(craneId)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少吊车参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(EmptyUtil.isEmpty(steelyardId)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少秤参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Receipt receipt = this.receiptRepository.findOne(receiptId);
        if(receipt == null) {
            status = new HttpStatusContent(OutputState.FAIL, "收发货单不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(!receipt.getOrderState().equals(ReceiptOrderState.ALLOCATION.getValue())
                && !receipt.getOrderState().equals(ReceiptOrderState.IN_METERING.getValue())
                && !receipt.getOrderState().equals(ReceiptOrderState.HAD_METERING.getValue())) {
            status = new HttpStatusContent(OutputState.FAIL, "收发货单状态必须为：已分配、计量中、已计量");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        //只要有计量单，就把已分配的状态改成已计量
        if (receipt.getOrderState().equals(ReceiptOrderState.ALLOCATION.getValue())) {
        		receipt.setOrderState(ReceiptOrderState.IN_METERING.getValue());
        }
        
        Crane crane = this.craneRepository.findOne(craneId);
        if(crane == null) {
            status = new HttpStatusContent(OutputState.FAIL, "吊车不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(crane.getIsUse() == null || !crane.getIsUse()){
            status = new HttpStatusContent(OutputState.FAIL,
                    "龙门吊："+crane.getCraneNo()
                            +",未启用");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity.setCrane(crane);

        Steelyard steelyard = this.steelyardRepository.findOne(steelyardId);
        if(steelyard == null) {
            status = new HttpStatusContent(OutputState.FAIL, "秤不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity.setSteelyard(steelyard);

        //计算费用
       if(CostSettinsFeePurpose.FACT.getValue().equals(receipt.getFeePurpose())
               && receipt.getUnitFee()!=null){
           //1.理重不管费用,收发货确认的时候通过委托单计算一次
           //2.如果：entrustOrderDetail是实重,费用 = 计量单实收吨数 * 委托单明细单价
            BigDecimal thisFee = entity.getTonNum().multiply(receipt.getUnitFee());
            entity.setFee(thisFee);
        }
       
        entity.setReceiptType(receipt.getOrderType());
        
        if (entity.getReceiptType().equals(ReceiptOrderType.IN_STOCK.getValue())) {
	    		orderNo = "RK" + orderNo;
	    }else {
	    		orderNo = "CK" + orderNo;
	    } 
        
        entity.setOrderNo(orderNo);

        //记录新建状态日志
        MeteringReceiptOrderLog log = new MeteringReceiptOrderLog();
        log.setCreateMan(entity.getCreateMan());
        log.setCreateManId(entity.getCreateManId());
        log.setOrderState(entity.getOrderState());
        log.setMeteringReceiptOrder(entity);
        log.setRemark(MeteringReceiptOrderState.getTxtByValue(entity.getOrderState()));

        Set<MeteringReceiptOrderLog> logSet = new HashSet<>();
        logSet.add(log);

        entity.setMeteringReceiptOrderLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        entity.setReceipt(receipt);
        entity.setEntrustOrder(receipt.getEntrustOrderDetail().getEntrustOrder());
        if(this.meteringReceiptOrderService.add(entity) != null){
            status = new HttpStatusContent(OutputState.SUCCESS);
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
        }
        status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * @api {PUT} /meteringReceiptOrder/{id} 计量单修改
     * @apiGroup meteringReceiptOrder
     * @apiVersion 0.0.1
     * @apiDescription 计量单修改
     * @apiParam {String} id 主键id
     *
     * @apiParam {String} craneId 吊车Id
     * @apiParam {String} steelyardId 秤Id
     * @apiParam {String} receiptId 收发货单Id
     * @apiParam {BigDecimal} num 实收件数
     * @apiParam {BigDecimal} tonNum 实收吨数
     * @apiParam {String} vehicleNo 车号(发货单计量才有)
     * @apiParamExample 请求明文：
     * /meteringReceiptOrder/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 MeteringReceiptOrder entity,
                                 String receiptId,
                                 String craneId,
                                 String steelyardId,
                                 String createMan,
                                 String createManId) {
        HttpStatusContent status = null;
        entity.setFee(null);
        entity.setOrderState(null);

        if(EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)){
            status = new HttpStatusContent(OutputState.FAIL, "缺少操作人信息！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        MeteringReceiptOrder meteringReceiptOrder = this.meteringReceiptOrderRepository.findOne(id);
        if (meteringReceiptOrder == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (meteringReceiptOrder.getOrderState().equals(MeteringReceiptOrderState.CONFIRM.getValue())) {
            status = new HttpStatusContent(OutputState.FAIL, "已确认不能修改！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if(EmptyUtil.isNotEmpty(craneId)){
            Crane crane = this.craneRepository.findOne(craneId);
            if(crane == null) {
                status = new HttpStatusContent(OutputState.FAIL, "吊车不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            entity.setCrane(crane);
        }

        if(EmptyUtil.isNotEmpty(steelyardId)){
            Steelyard steelyard = this.steelyardRepository.findOne(steelyardId);
            if(steelyard == null) {
                status = new HttpStatusContent(OutputState.FAIL, "秤不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            entity.setSteelyard(steelyard);
        }

        if(EmptyUtil.isNotEmpty(receiptId)){
            Receipt receipt = this.receiptRepository.findOne(receiptId);
            if(receipt == null) {
                status = new HttpStatusContent(OutputState.FAIL, "收发货单不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            entity.setReceipt(receipt);

            //计算费用
            if(CostSettinsFeePurpose.FACT.getValue().equals(receipt.getFeePurpose())
                    && receipt.getUnitFee() != null){
                //1.理重：收发货确认的是计算，这里不管
                //2.如果：entrustOrderDetail是实重,费用 = 计量单实收吨数 * 委托单明细单价
                BigDecimal thisFee = entity.getTonNum().multiply(receipt.getUnitFee());
                entity.setFee(thisFee);
            }
        }

        //如果修改吨数并且是实重
        //重新计算费用
        if(EmptyUtil.isNotEmpty(entity.getTonNum())
            && CostSettinsFeePurpose.FACT.getValue().equals(meteringReceiptOrder.getReceipt().getFeePurpose())
                && meteringReceiptOrder.getReceipt().getUnitFee()!=null){
            //2.如果：entrustOrderDetail是实重,费用 = 计量单实收吨数 * 委托单明细单价
            BigDecimal thisFee = entity.getTonNum().multiply(meteringReceiptOrder.getReceipt().getUnitFee());
            entity.setFee(thisFee);
        }

        MeteringReceiptOrderLog log = new MeteringReceiptOrderLog();
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(null);
        log.setMeteringReceiptOrder(meteringReceiptOrder);

        List<Map<String, Object>> maps = ClassAttrValCompare.newCompareToOld(meteringReceiptOrder, entity);
        //log.setRemark(maps.toString());
        log.setRemark("修改");
        Set<MeteringReceiptOrderLog> logSet = new HashSet<>();
        logSet.add(log);
        meteringReceiptOrder.setMeteringReceiptOrderLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        ObjectCopyUtil<MeteringReceiptOrder> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, meteringReceiptOrder);
        entity = this.meteringReceiptOrderRepository.save(meteringReceiptOrder);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {POST} /meteringReceiptOrder/transferState/change 计量单状态变更
     * @apiGroup meteringReceiptOrder
     * @apiVersion 0.0.1
     * @apiDescription 计量单状态变更
     * @apiParam {String} id 主键id
     * @apiParam {Integer} orderState 订单状态
     * - CONFIRM(11020, "确认"),
     * - INVALID(-11000, "作废");
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /meteringReceiptOrder/transferState/change
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json", value = "/transferState/change")
    public ResponseEntity<?> orderStateChange(String id,
                                              Integer orderState,
                                              String createMan,
                                              String createManId) {
        HttpStatusContent status = null;

        if (EmptyUtil.isEmpty(id)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少id参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)) {
            status = new HttpStatusContent(OutputState.FAIL, "操作人和操作人Id必传！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        MeteringReceiptOrder originEntity = this.meteringReceiptOrderRepository.findOne(id);
        if (originEntity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        //变更前状态
        Integer preState = originEntity.getOrderState();

        if(orderState.equals(preState)){
            status = new HttpStatusContent(OutputState.FAIL,
                    "当前状态已经是："+MeteringReceiptOrderState.getTxtByValue(preState));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if(!MeteringReceiptOrderState.isExist(orderState)){
            status = new HttpStatusContent(OutputState.FAIL, "状态参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        originEntity.setOrderState(orderState);//1.修改委托单状态

        //确认时
        if(MeteringReceiptOrderState.CONFIRM.getValue().equals(orderState)){
            if(!preState.equals(MeteringReceiptOrderState.NEW_CREATE.getValue())){
                status = new HttpStatusContent(OutputState.FAIL, "新建状态才能做确认！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Receipt receipt = originEntity.getReceipt();

            //第一个计量单，确认时才修改
            MeteringReceiptOrder order = new MeteringReceiptOrder();
            Receipt queryReceipt = new Receipt();
            queryReceipt.setId(originEntity.getId());
            order.setReceipt(queryReceipt);
            Example<MeteringReceiptOrder> example = Example.of(order);
            List<MeteringReceiptOrder> orders = this.meteringReceiptOrderRepository.findAll(example);
            if(orders == null || orders.size() <= 0){
            	    receipt.setOrderState(ReceiptOrderState.IN_METERING.getValue());
                receipt.setHasMeteringConfirm(true);//计量单已确认
            }
            originEntity.setReceipt(receipt);//通过关联修改（待测试）
        }

        MeteringReceiptOrderLog log = new MeteringReceiptOrderLog();
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(orderState);
        log.setMeteringReceiptOrder(originEntity);
        log.setRemark("状态由"
                +MeteringReceiptOrderState.getTxtByValue(preState)
                +"变为"+MeteringReceiptOrderState.getTxtByValue(orderState));
        Set<MeteringReceiptOrderLog> logSet = new HashSet<>();
        logSet.add(log);
        originEntity.setMeteringReceiptOrderLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        if(this.meteringReceiptOrderService.confirm(originEntity) != null){
            status = new HttpStatusContent(OutputState.SUCCESS);
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
        }
        status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @api {POST} /meteringReceiptOrder/transferState/multi 计量单状态确认(批量)
     * @apiGroup meteringReceiptOrder
     * @apiVersion 0.0.1
     * @apiDescription 计量单状态确认(批量)
     * @apiParam {String} ids 主键id列表(逗号分隔)
     * @apiParam {Integer} orderState 订单状态
     * - CONFIRM(11020, "确认"),
     * - INVALID(-11000, "作废");
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /meteringReceiptOrder/transferState/multi
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json", value = "/transferState/confirm")
    public ResponseEntity<?> multi(String ids,
                                     Integer orderState,
                                     String createMan,
                                     String createManId) {
        HttpStatusContent status = null;

        if (EmptyUtil.isEmpty(ids)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少id参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        String[] idArr = ids.split(",");
        if (idArr.length <=0) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少id参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if(!MeteringReceiptOrderState.CONFIRM.getValue().equals(orderState)
                && MeteringReceiptOrderState.INVALID.getValue().equals(orderState)){
            status = new HttpStatusContent(OutputState.FAIL, "状态参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)) {
            status = new HttpStatusContent(OutputState.FAIL, "操作人和操作人Id必传！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        List<MeteringReceiptOrder> meteringReceiptOrderList = new ArrayList<>();
        for(int i=0;i<idArr.length;i++){
            MeteringReceiptOrder originEntity = this.meteringReceiptOrderRepository.findOne(idArr[i]);
            if (originEntity == null) {
                status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            //变更前状态
            Integer preState = originEntity.getOrderState();

            if(orderState.equals(preState)){
                status = new HttpStatusContent(OutputState.FAIL,
                        "当前状态已经是："+MeteringReceiptOrderState.getTxtByValue(preState));
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            originEntity.setOrderState(orderState);//1.修改状态

            if(!preState.equals(MeteringReceiptOrderState.NEW_CREATE.getValue())){
                status = new HttpStatusContent(OutputState.FAIL, "新建状态才能操作！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            //确认时
            if(MeteringReceiptOrderState.CONFIRM.getValue().equals(orderState)){
                Receipt receipt = originEntity.getReceipt();
                //第一个计量单，确认时才修改
                MeteringReceiptOrder order = new MeteringReceiptOrder();
                Receipt queryReceipt = new Receipt();
                queryReceipt.setId(originEntity.getId());
                order.setReceipt(queryReceipt);
                Example<MeteringReceiptOrder> example = Example.of(order);
                List<MeteringReceiptOrder> orders = this.meteringReceiptOrderRepository.findAll(example);
                if(orders == null || orders.size() <= 0){
                    receipt.setHasMeteringConfirm(true);//计量单已确认
                }
                originEntity.setReceipt(receipt);//通过关联修改（待测试）
            }

            MeteringReceiptOrderLog log = new MeteringReceiptOrderLog();
            log.setCreateMan(createMan);
            log.setCreateManId(createManId);
            log.setOrderState(orderState);
            log.setMeteringReceiptOrder(originEntity);
            log.setRemark("状态由"
                    +MeteringReceiptOrderState.getTxtByValue(preState)
                    +"变为"+MeteringReceiptOrderState.getTxtByValue(orderState));
            Set<MeteringReceiptOrderLog> logSet = new HashSet<>();
            logSet.add(log);
            originEntity.setMeteringReceiptOrderLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

            meteringReceiptOrderList.add(originEntity);
        }
        //批量修改
        if(this.meteringReceiptOrderService.confirmList(meteringReceiptOrderList) != null){
            status = new HttpStatusContent(OutputState.SUCCESS);
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
        }
        status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

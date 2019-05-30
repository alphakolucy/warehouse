package com.warehouse.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.warehouse.model.*;
import com.warehouse.service.CostSettinsService;
import com.warehouse.service.TransferService;
import com.warehouse.service.repository.CustomerRepository;
import com.warehouse.service.repository.TransferDetailRepository;
import com.warehouse.service.repository.TransferRepository;
import com.warehouse.service.repository.WarehouseStockRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.CostSettinsOpType;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.enums.TransferState;
import com.warehouse.util.enums.TransferType;
import com.warehouse.util.tools.ClassAttrValCompare;
import com.warehouse.util.tools.EmptyUtil;
import com.warehouse.util.tools.ObjectCopyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 过户单
 */
@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WarehouseStockRepository warehouseStockRepository;
    @Autowired
    private TransferService transferService;
    @Autowired
    private TransferDetailRepository transferDetailRepository;
    @Autowired
    private CostSettinsService costSettinsService;

    /**
     * @api {GET} /transfer 过户列表
     * @apiGroup transfer
     * @apiVersion 0.0.1
     * @apiDescription 过户列表
     * @apiParam {String} [orderNo] 单号(支持模糊查询)
     * @apiParam {Integer} [transferState] 过户状态
     * - NEW_CREATE(70010, "新建"),
     * - HAD_TRANSFER(70020, "已过户"),
     * - INVALID(-70000, "作废");
     * @apiParam {Integer} [transferType] 过户方式
     * - DIRECT_TRANSFER(80010, "直接过户"),//（直接调整库存）
     * - NEED_WAREHOUSE_CONFIRM(80020, "需要仓库确认");//（库仓库确认为调整）
     * @apiParam {String} [fromCustomerId] 从某个客户Id
     * @apiParam {String} [toCustomerId] 到某个客户Id
     * @apiParam {String} [transferSettlementId] 过户结算单Id
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /transfer?opType=40010&sort=id,asc
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {Integer} transferState 过户状态
     * @apiSuccess (200) {String} transferStateTxt 过户状态Txt
     * @apiSuccess (200) {Integer} transferType 过户方式
     * @apiSuccess (200) {String} transferTypeTxt 过户类型Txt
     *
     * @apiSuccess (200) {BigDecimal} fee 费用
     * @apiSuccess (200) {String} remark 备注
     *
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     *
     * @apiSuccess (200) {Object} fromCustomerData 从某个客户对象
     * @apiSuccess (200) {String} fromCustomerData.id id
     * @apiSuccess (200) {String} fromCustomerData.name 企业名称
     *
     * @apiSuccess (200) {Object} toCustomerData 到某个客户对象
     * @apiSuccess (200) {String} toCustomerData.id id
     * @apiSuccess (200) {String} toCustomerData.name 企业名称
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Transfer>> findAll(Transfer entity,
                                                    @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable,
                                                     String fromCustomerId,
                                                     String toCustomerId,
                                                     String transferSettlementId) {

        //过户结算单Id查询
        if (EmptyUtil.isNotEmpty(transferSettlementId)) {
            TransferSettlement transferSettlement = new TransferSettlement();
            transferSettlement.setId(transferSettlementId);
            entity.setTransferSettlement(transferSettlement);
        }

        //从某个客户Id查询
        if (EmptyUtil.isNotEmpty(fromCustomerId)) {
            Customer fromCustomer = new Customer();
            fromCustomer.setId(fromCustomerId);
            entity.setFromCustomer(fromCustomer);
        }
        //到某个客户Id查询
        if (EmptyUtil.isNotEmpty(toCustomerId)) {
            Customer toCustomer = new Customer();
            toCustomer.setId(toCustomerId);
            entity.setToCustomer(toCustomer);
        }

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //模糊方式查
                .withMatcher("orderNo", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Transfer> example = Example.of(entity,matcher);

        Page<Transfer> apply = this.transferRepository.findAll(example, pageable);
        return new ResponseEntity<Page<Transfer>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /transfer/{id} 单个过户
     * @apiGroup transfer
     * @apiVersion 0.0.1
     * @apiDescription 单个过户
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /transfer/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {Integer} transferState 过户状态
     * @apiSuccess (200) {String} transferStateTxt 过户状态Txt
     * @apiSuccess (200) {Integer} transferType 过户方式
     * @apiSuccess (200) {String} transferTypeTxt 过户类型Txt
     * @apiSuccess (200) {BigDecimal} fee 费用
     * @apiSuccess (200) {String} remark 备注
     *
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     *
     * @apiSuccess (200) {Object} fromCustomerData 从某个客户对象
     * @apiSuccess (200) {String} fromCustomerData.id id
     * @apiSuccess (200) {String} fromCustomerData.name 企业名称
     *
     * @apiSuccess (200) {Object} toCustomerData 到某个客户对象
     * @apiSuccess (200) {String} toCustomerData.id id
     * @apiSuccess (200) {String} toCustomerData.name 企业名称
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        Transfer entity = this.transferRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Transfer>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /transfer 过户添加
     * @apiGroup transfer
     * @apiVersion 0.0.1
     * @apiDescription 过户添加
     * @apiParam {String} detailsJson 详细JSON数组
     * - 格式：[{"warehouseStockId":"2","num":"15","tonNum":"255"},
     * {"warehouseStockId":"5","num":"33","tonNum":"556"}]
     * - warehouseStockId:库存id
     * - num:操作件数
     * - tonNum:操作吨数
     * @apiParam {String} fromCustomerId 从某个客户Id
     * @apiParam {String} toCustomerId 到某个客户Id
     * @apiParam {Integer} transferType 过户方式
     * - DIRECT_TRANSFER(80010, "直接过户"),//（直接调整库存）
     * - NEED_WAREHOUSE_CONFIRM(80020, "需要仓库确认");//（库仓库确认为调整）
     * @apiParam {String} remark 备注

     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /transfer
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(Transfer entity,
                                  String fromCustomerId,
                                  String toCustomerId,
                                  String detailsJson) {

        HttpStatusContent status = null;

        String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format((new Date()).getTime());
        String orderNo = "GH" + yyyyMMdd;
        String newStrSN = String.format("%05d", 1);//每天默认00001
        //获取最大序号
        Transfer maxOrderNoEntity = this.transferRepository.findEveryDayMaxOrderNo(yyyyMMdd);
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
        entity.setIsSettlement(false);
        entity.setTransferState(TransferState.NEW_CREATE.getValue());

        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(!TransferType.isExist(entity.getTransferType())){
            status = new HttpStatusContent(OutputState.FAIL, "过户方式参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(EmptyUtil.isEmpty(fromCustomerId)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少从某个客户参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(EmptyUtil.isEmpty(toCustomerId)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少到某个客户参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Customer fromCustomer = this.customerRepository.findOne(fromCustomerId);
        if(fromCustomer == null) {
            status = new HttpStatusContent(OutputState.FAIL, "从某个客户不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity.setFromCustomer(fromCustomer);

        Customer toCustomer = this.customerRepository.findOne(toCustomerId);
        if(toCustomer == null) {
            status = new HttpStatusContent(OutputState.FAIL, "到某个客户不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity.setToCustomer(toCustomer);

        BigDecimal totalFee = BigDecimal.ZERO;
        Set<TransferDetail> detailSet = new HashSet<>();
        JSONArray jsonArray = JSON.parseArray(detailsJson);
        for (int i=0;i<jsonArray.size();i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String warehouseStockId = jsonObject.getString("warehouseStockId");
            BigDecimal num = jsonObject.getBigDecimal("num");
            BigDecimal tonNum = jsonObject.getBigDecimal("tonNum");
            if (EmptyUtil.isEmpty(warehouseStockId)) {
                status = new HttpStatusContent(OutputState.FAIL, "明细缺少库存参数");
                return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (EmptyUtil.isEmpty(num)
                    || num.compareTo(BigDecimal.ZERO)<=0) {
                status = new HttpStatusContent(OutputState.FAIL, "件数必须大于0");
                return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (EmptyUtil.isEmpty(tonNum)
                    || tonNum.compareTo(BigDecimal.ZERO)<=0) {
                status = new HttpStatusContent(OutputState.FAIL, "吨数必须大于0");
                return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            WarehouseStock warehouseStock = this.warehouseStockRepository.findOne(warehouseStockId);
            if (warehouseStock == null) {
                status = new HttpStatusContent(OutputState.FAIL, "库存不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }

            //操作件数，操作吨数与库存判断
            if(num.compareTo(warehouseStock.getNum())>0){
                status = new HttpStatusContent(OutputState.FAIL, "操作件数不能超过库存件数！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(tonNum.compareTo(warehouseStock.getTonNum())>0){
                status = new HttpStatusContent(OutputState.FAIL, "操作件数不能超过库存吨数！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            //计算费用
            Map<String, Object> unitPriceMap = this.costSettinsService.getUnitPrice(fromCustomer.getId(),
                    warehouseStock.getProduct().getCate().getId(), CostSettinsOpType.TRANSFER_NAME);
            //有客户的计费方式
            if(unitPriceMap.get("status").equals("no")){
                status = new HttpStatusContent(OutputState.FAIL,unitPriceMap.get("message").toString());
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            BigDecimal unitPrice = BigDecimal.valueOf(Double.valueOf(unitPriceMap.get("unitPrice").toString()));
            BigDecimal fee = unitPrice.multiply(tonNum);//单价*吨数
            totalFee = totalFee.add(fee);

            TransferDetail detail = new TransferDetail();
            detail.setNum(num);
            detail.setTonNum(tonNum);
            detail.setFee(fee);
            detail.setWarehouseStock(warehouseStock);
            detail.setTransfer(entity);
            detailSet.add(detail);
        }
        entity.setTransferDetailSet(detailSet);
        entity.setFee(totalFee);

        //记录新建状态日志
        TransferLog log = new TransferLog();
        log.setCreateMan(entity.getCreateMan());
        log.setCreateManId(entity.getCreateManId());
        log.setOrderState(entity.getTransferState());
        log.setTransfer(entity);
        log.setRemark(TransferState.getTxtByValue(entity.getTransferState()));

        Set<TransferLog> logSet = new HashSet<>();
        logSet.add(log);
        entity.setTransferLogSet(logSet);//2.记录状态变更日志(关联会自动写入)
        try {
            if(this.transferService.add(entity) != null){
                status = new HttpStatusContent(OutputState.SUCCESS);
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
            }
        } catch (Exception e) {
            status = new HttpStatusContent(OutputState.FAIL, e.getMessage());
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * @api {PUT} /transfer/{id} 过户修改
     * @apiGroup transfer
     * @apiVersion 0.0.1
     * @apiDescription 过户修改
     * @apiParam {String} id 主键id
     * @apiParam {String} detailsJson 明细json数组
     * - 格式：[{"warehouseStockId":"2","num":"15","tonNum":"255"},
     * {"warehouseStockId":"5","num":"33","tonNum":"556"}]
     * - warehouseStockId:库存id
     * - num:操作件数
     * - tonNum:操作吨数
     * @apiParam {String} fromCustomerId 从某个客户Id
     * @apiParam {String} toCustomerId 到某个客户Id
     * @apiParam {Integer} transferType 过户方式
     * - DIRECT_TRANSFER(80010, "直接过户"),//（直接调整库存）
     * - NEED_WAREHOUSE_CONFIRM(80020, "需要仓库确认");//（库仓库确认为调整）
     * @apiParam {String} remark 备注
     * @apiParamExample 请求明文：
     * /transfer/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @Transactional
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 Transfer entity,
                                 String detailsJson,
                                 String fromCustomerId,
                                 String toCustomerId,
                                 String createMan,
                                 String createManId) {
        HttpStatusContent status = null;

        entity.setTransferState(null);//状态不能通过此接口修改

        if(EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)){
            status = new HttpStatusContent(OutputState.FAIL, "缺少操作人信息！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Transfer transfer = this.transferRepository.findOne(id);
        if (transfer == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (!TransferState.NEW_CREATE.getValue().equals(transfer.getTransferState())) {
            status = new HttpStatusContent(OutputState.FAIL, "新建状态才能修改！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if(!TransferType.isExist(entity.getTransferType())){
            status = new HttpStatusContent(OutputState.FAIL, "过户方式参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(EmptyUtil.isNotEmpty(fromCustomerId)){
            Customer fromCustomer = this.customerRepository.findOne(fromCustomerId);
            if(fromCustomer == null) {
                status = new HttpStatusContent(OutputState.FAIL, "从某个客户不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            entity.setFromCustomer(fromCustomer);
        }

        if(EmptyUtil.isNotEmpty(toCustomerId)){
            Customer toCustomer = this.customerRepository.findOne(toCustomerId);
            if(toCustomer == null) {
                status = new HttpStatusContent(OutputState.FAIL, "到某个客户不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            entity.setToCustomer(toCustomer);
        }

        if(EmptyUtil.isNotEmpty(detailsJson)){
            BigDecimal totalFee = BigDecimal.ZERO;
            JSONArray jsonArray = JSONObject.parseArray(detailsJson);
            Set<TransferDetail> newDetailSet = new HashSet<>();
            for (int i=0;i<jsonArray.size();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String warehouseStockId = jsonObject.getString("warehouseStockId");
                BigDecimal num = jsonObject.getBigDecimal("num");
                BigDecimal tonNum = jsonObject.getBigDecimal("tonNum");
                if (EmptyUtil.isEmpty(warehouseStockId)) {
                    status = new HttpStatusContent(OutputState.FAIL, "明细缺少库存参数");
                    return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (EmptyUtil.isEmpty(num)
                        || num.compareTo(BigDecimal.ZERO)<=0) {
                    status = new HttpStatusContent(OutputState.FAIL, "件数必须大于0");
                    return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (EmptyUtil.isEmpty(tonNum)
                        || tonNum.compareTo(BigDecimal.ZERO)<=0) {
                    status = new HttpStatusContent(OutputState.FAIL, "吨数必须大于0");
                    return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }

                WarehouseStock warehouseStock = this.warehouseStockRepository.findOne(warehouseStockId);
                if (warehouseStock == null) {
                    status = new HttpStatusContent(OutputState.FAIL, "库存不存在！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
                }

                //操作件数，操作吨数与库存判断
                if(num.compareTo(warehouseStock.getNum())>0){
                    status = new HttpStatusContent(OutputState.FAIL, "操作件数不能超过库存件数！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if(tonNum.compareTo(warehouseStock.getTonNum())>0){
                    status = new HttpStatusContent(OutputState.FAIL, "操作件数不能超过库存吨数！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }

                //计算费用
                Map<String, Object> unitPriceMap = this.costSettinsService.getUnitPrice(transfer.getFromCustomer().getId(),
                        warehouseStock.getProduct().getCate().getId(), CostSettinsOpType.TRANSFER_NAME);
                //有客户的计费方式
                if(unitPriceMap.get("status").equals("no")){
                    status = new HttpStatusContent(OutputState.FAIL,unitPriceMap.get("message").toString());
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                BigDecimal unitPrice = BigDecimal.valueOf(Double.valueOf(unitPriceMap.get("unitPrice").toString()));
                BigDecimal fee = unitPrice.multiply(tonNum);//单价*吨数
                totalFee = totalFee.add(fee);

                TransferDetail detail = new TransferDetail();
                detail.setNum(num);
                detail.setTonNum(tonNum);
                detail.setFee(fee);
                detail.setWarehouseStock(warehouseStock);
                detail.setTransfer(entity);
                newDetailSet.add(detail);
            }

            if(newDetailSet.size()>0){
                //1.删掉老的明细
                List<TransferDetail> srcDetailList = new ArrayList<>(transfer.getTransferDetailSet());
                if (srcDetailList.size()>0) {
                    List<TransferDetail> oldDetailList = new ArrayList<>();
                    CollectionUtils.addAll(oldDetailList,  new Object[srcDetailList.size()]);
                    Collections.copy(oldDetailList,srcDetailList);//把之前明细进行深拷贝
                    transfer.getTransferDetailSet().clear();//清除老的对detail表数据的引用
                    Iterator<TransferDetail> iterator = oldDetailList.iterator();
                    while (iterator.hasNext()){
                        TransferDetail detail = iterator.next();
                        detail.setTransfer(null);
                        detail.setWarehouseStock(null);
                        TransferDetail one = this.transferDetailRepository.getOne(detail.getId());
                        if(one != null){//删除之前先查数据
                            this.transferDetailRepository.delete(one.getId());
                        }
                    }
                }
                //2.通过关联重新添加(明细)
                transfer.setTransferDetailSet(newDetailSet);
            }
        }

        TransferLog log = new TransferLog();
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(null);
        log.setTransfer(transfer);

        List<Map<String, Object>> maps = ClassAttrValCompare.newCompareToOld(transfer, entity);
        //log.setRemark(maps.toString());
        log.setRemark("修改");
        Set<TransferLog> logSet = new HashSet<>();
        logSet.add(log);
        transfer.setTransferLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        ObjectCopyUtil<Transfer> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, transfer);
        entity = this.transferRepository.save(transfer);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {POST} /transfer/transferState/change 过户状态变更
     * @apiGroup transfer
     * @apiVersion 0.0.1
     * @apiDescription 过户状态变更
     * @apiParam {String} id 主键id
     * @apiParam {Integer} transferState 过户状态
     * - INVALID(-70000, "作废");
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /transfer/transferState/change
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json", value = "/transferState/change")
    public ResponseEntity<?> transferStateChange(String id,
                                              Integer transferState,
                                              String createMan,
                                              String createManId) {
        HttpStatusContent status = null;

        if (EmptyUtil.isEmpty(id)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少id参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)) {
            status = new HttpStatusContent(OutputState.FAIL, "操作人和操作人Id必传！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(!TransferState.isExist(transferState)){
            status = new HttpStatusContent(OutputState.FAIL, "状态参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Transfer originEntity = this.transferRepository.findOne(id);
        if (originEntity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //变更前状态
        Integer preState = originEntity.getTransferState();

        if(transferState.equals(preState)){
            status = new HttpStatusContent(OutputState.FAIL,
                    "当前状态已经是："+TransferState.getTxtByValue(preState));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        originEntity.setTransferState(transferState);//1.修改委托单状态

        TransferLog log = new TransferLog();
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(transferState);
        log.setTransfer(originEntity);
        log.setRemark("状态由"
                +TransferState.getTxtByValue(preState)
                +"变为"+TransferState.getTxtByValue(transferState));
        Set<TransferLog> logSet = new HashSet<>();
        logSet.add(log);
        originEntity.setTransferLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        if(this.transferRepository.save(originEntity) != null){
            status = new HttpStatusContent(OutputState.SUCCESS);
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
        }
        status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @api {POST} /transfer/warehouseConfirm 确认
     * @apiGroup transfer
     * @apiVersion 0.0.1
     * @apiDescription 确认
     * @apiParam {String} id 主键id
     * @apiParam {Integer} transferState 过户状态
     * - HAD_TRANSFER(70020, "已过户")
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /transfer/warehouseConfirm
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json", value = "/warehouseConfirm")
    public ResponseEntity<?> warehouseConfirm(String id,
                                              Integer transferState,
                                              String createMan,
                                              String createManId) {
        HttpStatusContent status = null;

        if (EmptyUtil.isEmpty(id)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少id参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)) {
            status = new HttpStatusContent(OutputState.FAIL, "操作人和操作人Id必传！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(!transferState.equals(TransferState.HAD_TRANSFER.getValue())){
            status = new HttpStatusContent(OutputState.FAIL, "状态参数错误");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Transfer originEntity = this.transferRepository.findOne(id);
        if (originEntity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //变更前状态
        Integer preState = originEntity.getTransferState();

        if(transferState.equals(preState)){
            status = new HttpStatusContent(OutputState.FAIL,
                    "当前状态已经是："+TransferState.getTxtByValue(preState));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(!preState.equals(TransferState.NEW_CREATE.getValue())){
            status = new HttpStatusContent(OutputState.FAIL, "新建状态才能确认");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Set<TransferDetail> transferDetailSet = originEntity.getTransferDetailSet();
        if(transferDetailSet!=null && transferDetailSet.size()>0){
            Iterator<TransferDetail> iterator = transferDetailSet.iterator();
            while (iterator.hasNext()){
                TransferDetail next = iterator.next();
                if (next.getWarehouseStock().getWarehouseLevel().getLockState()!=null
                        && next.getWarehouseStock().getWarehouseLevel().getLockState()) {
                    status = new HttpStatusContent(OutputState.FAIL, "库层已锁定,无法操作！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }

        originEntity.setTransferState(transferState);//1.修改委托单状态

        TransferLog log = new TransferLog();
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(transferState);
        log.setTransfer(originEntity);
        log.setRemark("确认");
        Set<TransferLog> logSet = new HashSet<>();
        logSet.add(log);
        originEntity.setTransferLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        if(this.transferService.warehouseConfirm(originEntity,createMan,createManId) != null){
            status = new HttpStatusContent(OutputState.SUCCESS);
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
        }
        status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.warehouse.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.warehouse.model.*;
import com.warehouse.service.repository.*;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.*;
import com.warehouse.util.tools.*;
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
 * 锁库
 */
@RestController
@RequestMapping("/lockStockOrder")
public class LockStockOrderController {
    @Autowired
    private LockStockOrderRepository lockStockOrderRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    /**
     * @api {GET} /lockStockOrder 锁库列表
     * @apiGroup lockStockOrder
     * @apiVersion 0.0.1
     * @apiDescription 锁库列表
     * @apiParam {String} [orderNo] 单号(支持模糊查询)
     * @apiParam {Integer} [orderState] 锁库状态
     * - NEW_CREATE(16010, "新建"),
     * - LOCK(16020, "锁定"),
     * - UNLOCK(16030, "解锁"),
     * - INVALID(-16000, "作废");
     * @apiParam {String} [customerId] 客户Id
     * @apiParam {String} [warehouseId] 仓库Id
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /lockStockOrder?opType=40010&sort=id,asc
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {Integer} orderState 状态
     * @apiSuccess (200) {String} orderStateTxt 状态Txt
     *
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     *
     * @apiSuccess (200) {Object} customerData 客户对象
     * @apiSuccess (200) {String} customerData.id id
     * @apiSuccess (200) {String} customerData.name 企业名称
     *
     * @apiSuccess (200) {Object} warehouseData 仓库对象
     * @apiSuccess (200) {String} warehouseData.id id
     * @apiSuccess (200) {String} warehouseData.name 名称
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<LockStockOrder>> findAll(LockStockOrder entity,
                                                    @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable,
                                                     String warehouseId,
                                                     String customerId) {

        //仓库Id查询
        if (EmptyUtil.isNotEmpty(warehouseId)) {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(warehouseId);
            entity.setWarehouse(warehouse);
        }
        //客户Id查询
        if (EmptyUtil.isNotEmpty(customerId)) {
            Customer customer = new Customer();
            customer.setId(customerId);
            entity.setCustomer(customer);
        }

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //模糊方式查
                .withMatcher("orderNo", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<LockStockOrder> example = Example.of(entity,matcher);

        Page<LockStockOrder> apply = this.lockStockOrderRepository.findAll(example, pageable);
        return new ResponseEntity<Page<LockStockOrder>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /lockStockOrder/{id} 单个锁库
     * @apiGroup lockStockOrder
     * @apiVersion 0.0.1
     * @apiDescription 单个锁库
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /lockStockOrder/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {Integer} orderState 锁库状态
     * @apiSuccess (200) {String} orderStateTxt 锁库状态Txt
     *
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     *
     * @apiSuccess (200) {Object} warehouseStockData 库存对象
     * @apiSuccess (200) {String} warehouseStockData.id id
     *
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        LockStockOrder entity = this.lockStockOrderRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<LockStockOrder>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /lockStockOrder 锁库单添加
     * @apiGroup lockStockOrder
     * @apiVersion 0.0.1
     * @apiDescription 锁库单添加
     * @apiParam {String} warehouseId 仓库Id(第一级)
     * @apiParam {String} customerId 客户Id
     * @apiParam {String} strJsonArrayProduct 物资列表(json数组格式的字符串)
     * - 格式：[{"productId":"1","num":"80","tonNum":"50"},
     * {"productId":"2","num":"80","tonNum":"50"}]
     * - productId: 物资id
     * - num: 件数
     * - tonNum: 吨数
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /lockStockOrder
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(LockStockOrder entity,
                                  String warehouseId,
                                  String customerId,
                                  String strJsonArrayProduct) {

        HttpStatusContent status = null;

        String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format((new Date()).getTime());
        String orderNo = "SK" + yyyyMMdd;
        String newStrSN = String.format("%05d", 1);//每天默认00001
        //获取最大序号
        LockStockOrder maxOrderNoEntity = this.lockStockOrderRepository.findEveryDayMaxOrderNo(yyyyMMdd);
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
        entity.setOrderState(LockStockOrderState.NEW_CREATE.getValue());//默认状态新建

        if (!MyJSONUtil.isJSONArray(strJsonArrayProduct)) {
            status = new HttpStatusContent(OutputState.FAIL, "物资参数格式错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(EmptyUtil.isEmpty(warehouseId)){
            status = new HttpStatusContent(OutputState.FAIL, "缺少仓库参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(EmptyUtil.isEmpty(customerId)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少客户参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Warehouse warehouse = this.warehouseRepository.findOne(warehouseId);
        if(warehouse == null) {
            status = new HttpStatusContent(OutputState.FAIL, "仓库不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity.setWarehouse(warehouse);

        Customer customer = this.customerRepository.findOne(customerId);
        if(customer == null) {
            status = new HttpStatusContent(OutputState.FAIL, "客户不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity.setCustomer(customer);

        //委托单明细 列表
        JSONArray jsonArray = JSONObject.parseArray(strJsonArrayProduct);
        Set<LockStockOrderDetail> detailList = new HashSet<>();
        for (int i=0;i<jsonArray.size();i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String productId = jsonObject.getString("productId");
            BigDecimal num = jsonObject.getBigDecimal("num");
            BigDecimal tonNum = jsonObject.getBigDecimal("tonNum");
            if(EmptyUtil.isEmpty(productId)){
                status = new HttpStatusContent(OutputState.FAIL, "缺少物资参数！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Product product = this.productRepository.findOne(productId);
            if(EmptyUtil.isEmpty(product)){
                status = new HttpStatusContent(OutputState.FAIL, "物资不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            if(EmptyUtil.isEmpty(num)
                    || num.compareTo(BigDecimal.ZERO)<=0){
                status = new HttpStatusContent(OutputState.FAIL,
                        "物资:"+product.getProductName()+",件数必须大于0！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(EmptyUtil.isEmpty(tonNum)
                    || tonNum.compareTo(BigDecimal.ZERO)<=0){
                status = new HttpStatusContent(OutputState.FAIL,
                        "物资:"+product.getProductName()+",吨数必须大于0！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }


            LockStockOrderDetail detail = new LockStockOrderDetail();
            detail.setLockStockOrder(entity);
            detail.setProduct(product);
            detail.setNum(num);
            detail.setTonNum(tonNum);
            detailList.add(detail);
        }
        //因为配了1对多的关系，添加的时候同时把明细也添加进去
        entity.setLockStockOrderDetailSet(detailList);

        //记录新建状态日志
        LockStockOrderLog log = new LockStockOrderLog();
        log.setCreateMan(entity.getCreateMan());
        log.setCreateManId(entity.getCreateManId());
        log.setOrderState(entity.getOrderState());
        log.setLockStockOrder(entity);
        log.setRemark(LockStockOrderState.getTxtByValue(entity.getOrderState()));

        Set<LockStockOrderLog> logSet = new HashSet<>();
        logSet.add(log);
        entity.setLockStockOrderLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        if(this.lockStockOrderRepository.save(entity) != null){
            status = new HttpStatusContent(OutputState.SUCCESS);
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
        }

        status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * @api {PUT} /lockStockOrder/{id} 锁库单修改
     * @apiGroup lockStockOrder
     * @apiVersion 0.0.1
     * @apiDescription 锁库单修改
     * @apiParam {String} id 主键id
     * @apiParam {String} warehouseId 仓库Id
     * @apiParam {String} customerId 客户Id
     * @apiParam {String} strJsonArrayProduct 物资列表(json数组格式的字符串)
     * - 格式：[{"id":"2","productId":"1","num":"80","tonNum":"50"},
     * {"productId":"2","num":"80","tonNum":"50"}]
     * - productId: 物资id
     * - num: 件数
     * - tonNum: 吨数
     * - id：明细主键id
     *
     * @apiParamExample 请求明文：
     * /lockStockOrder/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 LockStockOrder entity,
                                 String warehouseId,
                                 String customerId,
                                 String createMan,
                                 String createManId,
                                 String strJsonArrayProduct) {
        HttpStatusContent status = null;
        entity.setCreateMan(null);//不能修改之前的创建人信息
        entity.setCreateManId(null);//不能修改之前的创建人信息
        entity.setOrderState(null);//状态不能通过此接口修改

        if(EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)){
            status = new HttpStatusContent(OutputState.FAIL, "缺少操作人信息！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LockStockOrder lockStockOrder = this.lockStockOrderRepository.findOne(id);
        if (lockStockOrder == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (!lockStockOrder.getOrderState().equals(LockStockOrderState.NEW_CREATE.getValue())) {
            status = new HttpStatusContent(OutputState.FAIL, "新建状态才能修改！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }


        if(EmptyUtil.isNotEmpty(warehouseId)){
            Warehouse warehouse = this.warehouseRepository.findOne(warehouseId);
            if(warehouse == null) {
                status = new HttpStatusContent(OutputState.FAIL, "仓库不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            lockStockOrder.setWarehouse(warehouse);
        }

        if(EmptyUtil.isNotEmpty(customerId)){
            Customer customer = this.customerRepository.findOne(customerId);
            if(customer == null) {
                status = new HttpStatusContent(OutputState.FAIL, "客户不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            lockStockOrder.setCustomer(customer);
        }

        Set<LockStockOrderDetail> newDetailSet = null;
        if(EmptyUtil.isNotEmpty(strJsonArrayProduct)){
            if (!MyJSONUtil.isJSONArray(strJsonArrayProduct)) {
                status = new HttpStatusContent(OutputState.FAIL, "物资明细参数格式错误！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            List<LockStockOrderDetail> entrustDetailList = JSONObject.parseArray(strJsonArrayProduct, LockStockOrderDetail.class);
            if (entrustDetailList == null || entrustDetailList.size() <= 0) {
                status = new HttpStatusContent(OutputState.FAIL, "缺少物资明细");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            for (LockStockOrderDetail item : entrustDetailList) {
                item.setLockStockOrder(lockStockOrder);
            }
            newDetailSet = new HashSet<>(entrustDetailList);
            entity.setLockStockOrderDetailSet(newDetailSet);
        }

        LockStockOrderLog log = new LockStockOrderLog();
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(null);
        log.setLockStockOrder(lockStockOrder);

        List<Map<String, Object>> maps = ClassAttrValCompare.newCompareToOld(lockStockOrder, entity);
        //log.setRemark(maps.toString());
        log.setRemark("修改");
        Set<LockStockOrderLog> logSet = new HashSet<>();
        logSet.add(log);
        lockStockOrder.setLockStockOrderLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        ObjectCopyUtil<LockStockOrder> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, lockStockOrder);

        entity = this.lockStockOrderRepository.save(lockStockOrder);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {POST} /lockStockOrder/orderState/change 锁库状态变更
     * @apiGroup lockStockOrder
     * @apiVersion 0.0.1
     * @apiDescription 锁库状态变更
     * @apiParam {String} id 主键id
     * @apiParam {Integer} orderState 锁库状态
     * - LOCK(16020, "锁定"),
     * - UNLOCK(16030, "解锁"),
     * - INVALID(-16000, "作废");
     * @apiParam {String} remark 备注(解锁/锁定必填)
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /lockStockOrder/orderState/change
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json", value = "/orderState/change")
    public ResponseEntity<?> stateChange(String id,
                                              Integer orderState,
                                              String remark,
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

        LockStockOrder originEntity = this.lockStockOrderRepository.findOne(id);
        if (originEntity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //变更前状态
        Integer preState = originEntity.getOrderState();

        if(orderState.equals(preState)){
            status = new HttpStatusContent(OutputState.FAIL,
                    "当前状态已经是："+LockStockOrderState.getTxtByValue(preState));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(!LockStockOrderState.isExist(orderState)){
            status = new HttpStatusContent(OutputState.FAIL, "状态参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        originEntity.setOrderState(orderState);//1.修改状态

        String logRemark = null;

        //解锁
        if(LockStockOrderState.UNLOCK.getValue().equals(orderState)){
            if(!LockStockOrderState.LOCK.getValue().equals(preState)){
                status = new HttpStatusContent(OutputState.FAIL, "锁定状态才能解锁！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(EmptyUtil.isEmpty(remark)){
                status = new HttpStatusContent(OutputState.FAIL, "解锁必须输入备注！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            logRemark = remark;
        }else if(LockStockOrderState.LOCK.getValue().equals(orderState)){
            //锁定
            if(!LockStockOrderState.NEW_CREATE.getValue().equals(preState)){
                status = new HttpStatusContent(OutputState.FAIL, "新建状态才能锁定！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(EmptyUtil.isEmpty(remark)){
                status = new HttpStatusContent(OutputState.FAIL, "锁定必须输入备注！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            logRemark = remark;
        }else if(LockStockOrderState.INVALID.getValue().equals(orderState)){
            //作废
            if(!LockStockOrderState.NEW_CREATE.getValue().equals(preState)){
                status = new HttpStatusContent(OutputState.FAIL, "新建状态才能作废！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if(EmptyUtil.isEmpty(logRemark)){
            logRemark = "状态由"
                    +LockStockOrderState.getTxtByValue(preState)
                    +"变为"+LockStockOrderState.getTxtByValue(orderState);
        }

        LockStockOrderLog log = new LockStockOrderLog();
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(orderState);
        log.setLockStockOrder(originEntity);
        log.setRemark(logRemark);
        Set<LockStockOrderLog> logSet = new HashSet<>();
        logSet.add(log);
        originEntity.setLockStockOrderLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        if(this.lockStockOrderRepository.save(originEntity) != null){
            status = new HttpStatusContent(OutputState.SUCCESS);
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
        }
        status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

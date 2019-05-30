package com.warehouse.controller;

import com.warehouse.model.*;
import com.warehouse.service.WarehouseStockService;
import com.warehouse.service.repository.*;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.*;
import com.warehouse.util.tools.BusinessNo;
import com.warehouse.util.tools.EmptyUtil;
import com.warehouse.util.tools.ObjectCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 库存调整-管理
 */
@RestController
@RequestMapping("/adjustOrder")
public class AdjustOrderController {

    @Autowired
    private AdjustOrderRepository adjustOrderRepository;
    @Autowired
    private WarehouseStockService warehouseStockService;
    @Autowired
    private WarehouseStockRepository warehouseStockRepository;

    /**
     * @api {GET} /adjustOrder 库存调整列表
     * @apiGroup adjustOrder
     * @apiVersion 0.0.1
     * @apiDescription 库存调整列表
     * @apiParam {String} [orderNo] 单号(支持模糊查询)
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=createTime,asc] 排序
     * - 格式： sort=createTime,desc 表示在按createTime由高到低排列
     * - 格式： sort=createTime,asc 表示在按createTime由低到高排列
     * @apiParamExample 请求明文：
     * /adjustOrder
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String}  warehouseStockId 库存id
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {BigDecimal} num 操作件数
     * @apiSuccess (200) {BigDecimal} tonNum 操作吨数
     * @apiSuccess (200) {String} orderStateTxt 订单状态名称
     * @apiSuccess (200) {Integer} orderState 订单状态
     * - NEW_CREATE(50010, "新建"),
     * - CONFIRM(50020, "确认"),
     * - INVALID(-50000, "作废");
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人ID
     * @apiSuccess (200) {Date}  createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {Object} customer 客户对象
     * @apiSuccess (200) {String} customer.id 客户id
     * @apiSuccess (200) {String} customer.name 企业名称
     * @apiSuccess (200) {Object} cate 物资分类对象
     * @apiSuccess (200) {String} cate.id 主键id
     * @apiSuccess (200) {String} cate.productCateName 物资分类名称
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<AdjustOrder>> findAll(AdjustOrder entity,
                                                     String warehouseStockId,
                                                     @PageableDefault(value = 10,
                                                             sort = {"createTime"},
                                                             direction = Sort.Direction.DESC)
                                                             Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //物资名称模糊方式查
                .withMatcher("orderNo", ExampleMatcher.GenericPropertyMatchers.contains());


        // 根据库存warehouseStockId查询
        if (EmptyUtil.isNotEmpty(warehouseStockId)) {
            WarehouseStock warehouseStock = new WarehouseStock();
            warehouseStock.setId(warehouseStockId);
            entity.setWarehouseStock(warehouseStock);
        }

        Example<AdjustOrder> example = Example.of(entity, matcher);
        Page<AdjustOrder> apply = this.adjustOrderRepository.findAll(example, pageable);
        return new ResponseEntity<Page<AdjustOrder>>(apply, HttpStatus.OK);
    }

    /**
     * @api {GET} /adjustOrder/{id} 库存调整详细
     * @apiGroup adjustOrder
     * @apiVersion 0.0.1
     * @apiDescription 库存调整详细
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /adjustOrder/1
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String}  warehouseStockId 库存id
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {BigDecimal} num 操作件数
     * @apiSuccess (200) {BigDecimal} tonNum 操作吨数
     * @apiSuccess (200) {String} orderStateTxt 订单状态名称
     * @apiSuccess (200) {Integer} orderState 订单状态
     * - NEW_CREATE(50010, "新建"),
     * - CONFIRM(50020, "确认"),
     * - INVALID(-50000, "作废");
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人ID
     * @apiSuccess (200) {Date}  createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {Object} customer 客户对象
     * @apiSuccess (200) {String} customer.id 客户id
     * @apiSuccess (200) {String} customer.name 企业名称
     * @apiSuccess (200) {Object} cate 物资分类对象
     * @apiSuccess (200) {String} cate.id 主键id
     * @apiSuccess (200) {String} cate.productCateName 物资分类名称
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        AdjustOrder entity = this.adjustOrderRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    /**
     * @api {POST} /adjustOrder 库存调整添加
     * @apiGroup adjustOrder
     * @apiVersion 0.0.1
     * @apiDescription 库存调整添加
     * @apiParam (200) {String} warehouseStockId 库存id
     * @apiParam (200) {BigDecimal} num 操作件数
     * @apiParam (200) {BigDecimal} tonNum 操作吨数
     * @apiParam (200) {String} remark 备注
     * @apiParam (200) {String} createMan 操作人
     * @apiParam (200) {String} createManId 操作人ID
     * @apiParamExample 请求明文：
     * /adjustOrder
     * @apiSuccess (200) {String} id 主键id
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(AdjustOrder entity, String warehouseStockId) {
        HttpStatusContent status = null;

        if (EmptyUtil.isEmpty(warehouseStockId)) {
            status = new HttpStatusContent(OutputState.FAIL, "未传入库存ID！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        entity.setOrderNo(BusinessNo.getBusinessNo("CKTZ"));
        entity.setOrderState(AdjustOrderState.NEW_CREATE.getValue());//默认状态新建

        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        WarehouseStock warehouseStock = this.warehouseStockRepository.findOne(warehouseStockId);
        if (warehouseStock == null) {
            status = new HttpStatusContent(OutputState.FAIL, "未找到相关的库存信息！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        entity.setWarehouseStock(warehouseStock);

        // 记录日志
        AdjustOrderLog log = new AdjustOrderLog();
        log.setAdjustOrder(entity);
        log.setCreateMan(entity.getCreateMan());
        log.setCreateManId(entity.getCreateManId());
        log.setRemark(entity.getRemark());
        log.setOrderState(AdjustOrderState.NEW_CREATE.getValue());

        Set<AdjustOrderLog> logSet = new HashSet<>();
        logSet.add(log);
        entity.setAdjustOrderLogSet(logSet);// 记录库存调整日志(关联会自动写入)

        entity = this.adjustOrderRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<>(status, HttpStatus.OK);

    }

    /**
     * @api {PUT} /adjustOrder 库存调整修改
     * @apiGroup adjustOrder
     * @apiVersion 0.0.1
     * @apiDescription 库存调整修改
     * @apiParam {String} id 主键id
     * @apiParam (200) {BigDecimal} num 操作件数
     * @apiParam (200) {BigDecimal} tonNum 操作吨数
     * @apiParam (200) {String} remark 备注
     * @apiParam (200) {String} createMan 操作人
     * @apiParam (200) {String} createManId 操作人ID
     * @apiParamExample 请求明文：
     * /adjustOrder
     * @apiSuccess (200) {String} id 主键id
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id, AdjustOrder entity) {

        HttpStatusContent status = null;

        AdjustOrder adjustOrder = this.adjustOrderRepository.findOne(id);
        if (adjustOrder == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }

        if (adjustOrder.getOrderState() == AdjustOrderState.CONFIRM.getValue()) {
            status = new HttpStatusContent(OutputState.FAIL, "该单号已确认，不能再修改！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
        if (adjustOrder.getOrderState() == AdjustOrderState.INVALID.getValue()) {
            status = new HttpStatusContent(OutputState.FAIL, "该单号已作废，不能再修改！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
        ObjectCopyUtil<AdjustOrder> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, adjustOrder);

        WarehouseStock warehouseStock = this.warehouseStockRepository.findOne(adjustOrder.getWarehouseStock().getId());
        if (warehouseStock == null) {
            status = new HttpStatusContent(OutputState.FAIL, "未找到相关的库存信息！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        // 记录日志
        AdjustOrderLog log = new AdjustOrderLog();
        log.setAdjustOrder(adjustOrder);
        log.setCreateMan(entity.getCreateMan());
        log.setCreateManId(entity.getCreateManId());
        log.setRemark(entity.getRemark());

        Set<AdjustOrderLog> logSet = new HashSet<>();
        logSet.add(log);
        entity.setAdjustOrderLogSet(logSet);// 记录库存调整日志(关联会自动写入)

        entity.setWarehouseStock(warehouseStock);
        entity = this.adjustOrderRepository.save(adjustOrder);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    /**
     * @api {POST} /adjustOrder/adjustConfirm 库存调整确认
     * @apiGroup adjustOrder
     * @apiVersion 0.0.1
     * @apiDescription 库存调整确认
     * @apiParam {String} id 主键id
     * @apiParam (200) {String} adjustOrderIds (以逗号隔开的格式字符串)
     * - 格式：1,2,3,4
     * - 调整单id,多个逗号隔开
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /adjustOrder/adjustConfirm
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json", value = "/adjustConfirm")
    public ResponseEntity<?> adjustConfirm(String adjustOrderIds, String createMan, String createManId) {
        HttpStatusContent status = null;


        if (EmptyUtil.isNotEmpty(adjustOrderIds) && adjustOrderIds.split(",").length > 0) {
            String[] array = adjustOrderIds.split(",");
            for (int i = 0; i < array.length; i++) {
                AdjustOrder adjustOrder = this.adjustOrderRepository.findOne(array[i]);
                if (adjustOrder == null) {
                    status = new HttpStatusContent(OutputState.FAIL, "未找到相关的库存调整信息！");
                    return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }

                if (adjustOrder.getOrderState() == -50000) {
                    status = new HttpStatusContent(OutputState.FAIL, "存在已作废单号！");
                    return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                List<WarehouseStock> warehouseStocks = new ArrayList<>();
                WarehouseStock warehouseStock = new WarehouseStock();
                warehouseStock.setWarehouseLevel(adjustOrder.getWarehouseStock().getWarehouseLevel());
                warehouseStock.setProduct(adjustOrder.getWarehouseStock().getProduct());
                warehouseStock.setCustomer(adjustOrder.getWarehouseStock().getCustomer());
                warehouseStock.setNum(adjustOrder.getNum());
                warehouseStock.setTonNum(adjustOrder.getTonNum());
                warehouseStock.setCreateMan(createMan);
                warehouseStock.setCreateManId(createManId);
                warehouseStocks.add(warehouseStock);
                //更新库存

                Boolean result = warehouseStockService.saveOrUpdate(warehouseStocks,
                        adjustOrder.getOrderNo(), adjustOrder.getId(),
                        WarehouseStockLogOpType.ADJUST_NAME.getValue());

                if (result) {
                    //更新调整单状态为确认
                    adjustOrder.setOrderState(AdjustOrderState.CONFIRM.getValue());

                    // 记录日志
                    AdjustOrderLog log = new AdjustOrderLog();
                    log.setAdjustOrder(adjustOrder);
                    log.setRemark(adjustOrder.getRemark());
                    log.setCreateMan(createMan);
                    log.setCreateManId(createManId);
                    log.setOrderState(adjustOrder.getOrderState());

                    Set<AdjustOrderLog> logSet = new HashSet<>();
                    logSet.add(log);
                    adjustOrder.setAdjustOrderLogSet(logSet);// 记录库存调整日志(关联会自动写入)

                    this.adjustOrderRepository.save(adjustOrder);
                }
            }
        } else {
            status = new HttpStatusContent(OutputState.FAIL, "请选择要确认的调整库存！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {POST} /adjustOrder/adjustState 调整订单状态变更
     * @apiGroup adjustOrder
     * @apiVersion 0.0.1
     * @apiDescription 调整订单状态变更
     * @apiParam {String} id 主键id
     * @apiParam {Integer} orderState 调整订单状态
     * - NEW_CREATE(50010, "新建"),
     * - CONFIRM(50020, "确认"),
     * - INVALID(-50000, "作废");
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /adjustOrder/adjustState
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */

    @PostMapping(produces = "application/json", value = "/adjustState")
    public ResponseEntity<?> adjustStateChange(String id,
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

        AdjustOrder adjustOrder = this.adjustOrderRepository.findOne(id);
        if (adjustOrder == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        //变更前状态
        Integer preState = adjustOrder.getOrderState();

        if (orderState.equals(preState)) {
            status = new HttpStatusContent(OutputState.FAIL, "当前状态已经是：" + TransferState.getTxtByValue(preState));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (!AdjustOrderState.isExist(orderState)) {
            status = new HttpStatusContent(OutputState.FAIL, "调整状态参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        adjustOrder.setOrderState(orderState); //1.修改仓库调整状态

        // 记录日志
        AdjustOrderLog log = new AdjustOrderLog();
        log.setAdjustOrder(adjustOrder);
        log.setRemark("状态由" + AdjustOrderState.getTxtByValue(preState) + "变为" + AdjustOrderState.getTxtByValue(orderState));
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(orderState);

        Set<AdjustOrderLog> logSet = new HashSet<>();
        logSet.add(log);
        adjustOrder.setAdjustOrderLogSet(logSet);// 记录库存调整日志(关联会自动写入)

        if (this.adjustOrderRepository.save(adjustOrder) != null) {
            status = new HttpStatusContent(OutputState.SUCCESS);
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
        }
        status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}

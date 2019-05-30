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
 * 移库-管理
 */
@RestController
@RequestMapping("/moveStockOrder")
public class MoveStockOrderController {

    @Autowired
    private MoveStockOrderRepository moveStockOrderRepository;
    @Autowired
    private WarehouseStockService warehouseStockService;
    @Autowired
    private WarehouseStockRepository warehouseStockRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    /**
     * @api {GET} /moveStockOrder 移库列表
     * @apiGroup moveStockOrder
     * @apiVersion 0.0.1
     * @apiDescription 移库列表
     * @apiParam {String} [orderNo] 单号(支持模糊查询)
     * @apiParam {String} [toWarehouseId] 到库位id
     * @apiParam {String} [productId] 物资id
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=createTime,asc] 排序
     * - 格式： sort=createTime,desc 表示在按id由高到低排列
     * - 格式： sort=createTime,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /moveStockOrder
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {String} num 操作件数
     * @apiSuccess (200) {String} tonNum 操作吨数
     * @apiSuccess (200) {String} orderStateTxt 订单状态名称
     * @apiSuccess (200) {Integer} orderState 订单状态
     * - NEW_CREATE(50010, "新建"),
     * - CONFIRM(50020, "确认"),
     * - INVALID(-50000, "作废");
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人ID
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {Object} toWarehouse 库层实体
     * @apiSuccess (200) {String} toWarehouse.id id
     * @apiSuccess (200) {String} toWarehouse.name 名称
     *
     * @apiSuccess (200) {Object} productData 库存物资对象
     * @apiSuccess (200) {String} productData.id id
     * @apiSuccess (200) {String} productData.productName 物资名称
     * @apiSuccess (200) {String} productData.unit 物资单位
     * @apiSuccess (200) {String} productData.rationale 物资理重
     * @apiSuccess (200) {String} productData.spec 物资规格
     * @apiSuccess (200) {String} productData.model 物资型号
     * @apiSuccess (200) {String} productData.material 物资材料
     * @apiSuccess (200) {String} productData.maker 物资厂家
     * @apiSuccess (200) {String} productData.productCateName 分类名
     * @apiSuccess (200) {String} forkliftMan 叉车工
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<MoveStockOrder>> findAll(MoveStockOrder entity,
                                                        String warehouseStockId,
                                                        String productId,
                                                        String toWarehouseId,
                                                        @PageableDefault(value = 10,
                                                                sort = {"createTime"},
                                                                direction = Sort.Direction.DESC)
                                                                Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //单号模糊方式查
                .withMatcher("orderNo", ExampleMatcher.GenericPropertyMatchers.contains());

        // 根据来源库存warehouseStockId查询
        if (EmptyUtil.isNotEmpty(warehouseStockId)) {
            WarehouseStock warehouseStock = new WarehouseStock();
            warehouseStock.setId(warehouseStockId);
            entity.setWarehouseStock(warehouseStock);
        }

        // 根据到库位toWarehouseId查询
        if (EmptyUtil.isNotEmpty(toWarehouseId)) {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(toWarehouseId);
            entity.setToWarehouse(warehouse);
        }

        // 根据物资productId查询
        if (EmptyUtil.isNotEmpty(productId)) {
            Product product = new Product();
            product.setId(productId);
            entity.setProduct(product);
        }

        Example<MoveStockOrder> example = Example.of(entity, matcher);
        Page<MoveStockOrder> apply = this.moveStockOrderRepository.findAll(example, pageable);
        return new ResponseEntity<Page<MoveStockOrder>>(apply, HttpStatus.OK);
    }

    /**
     * @api {GET} /moveStockOrder/{id} 移库详细
     * @apiGroup moveStockOrder
     * @apiVersion 0.0.1
     * @apiDescription 移库详细
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /moveStockOrder/1
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {String} num 操作件数
     * @apiSuccess (200) {String} tonNum 操作吨数
     * @apiSuccess (200) {String} orderStateTxt 订单状态名称
     * @apiSuccess (200) {Integer} orderState 订单状态
     * - NEW_CREATE(50010, "新建"),
     * - CONFIRM(50020, "确认"),
     * - INVALID(-50000, "作废");
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人ID
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {Object} toWarehouse 库层实体
     * @apiSuccess (200) {String} toWarehouse.id id
     * @apiSuccess (200) {String} toWarehouse.name 名称
     *
     * @apiSuccess (200) {Object} productData 库存物资对象
     * @apiSuccess (200) {String} productData.id id
     * @apiSuccess (200) {String} productData.productName 物资名称
     * @apiSuccess (200) {String} productData.unit 物资单位
     * @apiSuccess (200) {String} productData.rationale 物资理重
     * @apiSuccess (200) {String} productData.spec 物资规格
     * @apiSuccess (200) {String} productData.model 物资型号
     * @apiSuccess (200) {String} productData.material 物资材料
     * @apiSuccess (200) {String} productData.maker 物资厂家
     * @apiSuccess (200) {String} productData.productCateName 分类名
     * @apiSuccess (200) {String} forkliftMan 叉车工
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        MoveStockOrder entity = this.moveStockOrderRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /moveStockOrder 库存移库添加
     * @apiGroup moveStockOrder
     * @apiVersion 0.0.1
     * @apiDescription 库存移库添加
     * @apiParam (200) {String} toWarehouseId 仓库库层id
     * @apiParam (200) {String} warehouseStockId 库存id
     * @apiParam (200) {BigDecimal} num 操作件数
     * @apiParam (200) {BigDecimal} tonNum 操作吨数
     * @apiParam (200) {String} remark 备注
     * @apiParam {String} forkliftMan 叉车工

     * @apiParam (200) {String} createMan 操作人
     * @apiParam (200) {String} createManId 操作人ID
     * @apiParamExample 请求明文：
     * /moveStockOrder
     * @apiSuccess (200) {String} id 主键id
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(MoveStockOrder entity, String toWarehouseId, String warehouseStockId) {
        HttpStatusContent status = null;

        if (EmptyUtil.isEmpty(toWarehouseId)) {
            status = new HttpStatusContent(OutputState.FAIL, "未传入仓库库层ID！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (EmptyUtil.isEmpty(warehouseStockId)) {
            status = new HttpStatusContent(OutputState.FAIL, "未传入库存ID！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        entity.setOrderNo(BusinessNo.getBusinessNo("CKYK"));

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
        
        Warehouse toWarehouse = this.warehouseRepository.findOne(toWarehouseId);
        if (toWarehouse == null || toWarehouse.getLevelOrder() != 4) {
            status = new HttpStatusContent(OutputState.FAIL, "未找到相关的库层信息！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (toWarehouse.getIsUse() != null
                && !toWarehouse.getIsUse()) {
            status = new HttpStatusContent(OutputState.FAIL, "库层已停用,无法操作！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        entity.setToWarehouse(toWarehouse);
        entity.setWarehouseStock(warehouseStock);
        entity.setProduct(warehouseStock.getProduct());

        // 记录日志
        MoveStockOrderLog log = new MoveStockOrderLog();
        log.setMoveStockOrder(entity);
        log.setCreateMan(entity.getCreateMan());
        log.setCreateManId(entity.getCreateManId());
        log.setRemark(entity.getRemark());
        log.setOrderState(MoveStockOrderState.NEW_CREATE.getValue());

        Set<MoveStockOrderLog> logSet = new HashSet<>();
        logSet.add(log);
        entity.setMoveStockOrderLogSet(logSet);// 记录库存移库日志(关联会自动写入)

        entity = this.moveStockOrderRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<>(status, HttpStatus.OK);

    }

    /**
     * @api {POST} /moveStockOrder 库存移库修改
     * @apiGroup moveStockOrder
     * @apiVersion 0.0.1
     * @apiDescription 库存移库修改
     * @apiParam (200) {String} toWarehouseId 仓库库层id
     * @apiParam (200) {String} warehouseStockId 库存id
     * @apiParam (200) {BigDecimal} num 操作件数
     * @apiParam (200) {BigDecimal} tonNum 操作吨数
     * @apiParam (200) {String} remark 备注
     * @apiParam (200) {String} createMan 操作人
     * @apiParam (200) {String} createManId 操作人ID
     * @apiParamExample 请求明文：
     * /moveStockOrder
     * @apiSuccess (200) {String} id 主键id
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */

    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id, String toWarehouseId, String warehouseStockId, MoveStockOrder entity) {
        HttpStatusContent status = null;

        MoveStockOrder moveStockOrder = this.moveStockOrderRepository.findOne(id);
        if (moveStockOrder == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }

        if (EmptyUtil.isNotEmpty(toWarehouseId)) {
            Warehouse one = this.warehouseRepository.getOne(toWarehouseId);
            if(one ==null || one.getLevelOrder()!=4){
                status = new HttpStatusContent(OutputState.FAIL, "请传入库层ID！");
                return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            moveStockOrder.setToWarehouse(one);

        }

        if (EmptyUtil.isEmpty(warehouseStockId)) {
            status = new HttpStatusContent(OutputState.FAIL, "未传入库存ID！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        ObjectCopyUtil<MoveStockOrder> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, moveStockOrder);

        WarehouseStock warehouseStock = this.warehouseStockRepository.findOne(warehouseStockId);
        if (warehouseStock == null) {
            status = new HttpStatusContent(OutputState.FAIL, "未找到相关的库存信息！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity.setWarehouseStock(warehouseStock);

        // 记录日志
        MoveStockOrderLog log = new MoveStockOrderLog();
        log.setMoveStockOrder(entity);
        log.setCreateMan(entity.getCreateMan());
        log.setCreateManId(entity.getCreateManId());
        log.setRemark(entity.getRemark());

        Set<MoveStockOrderLog> logSet = new HashSet<>();
        logSet.add(log);
        entity.setMoveStockOrderLogSet(logSet);// 记录库存移库日志(关联会自动写入)

        entity = this.moveStockOrderRepository.save(moveStockOrder);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<>(status, HttpStatus.OK);

    }

    /**
     * @api {POST} /moveStockOrder/moveStockConfirm 确认移库
     * @apiGroup moveStockOrder
     * @apiVersion 0.0.1
     * @apiDescription 确认移库
     * @apiParam {String} id 主键id
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /moveStockOrder/moveStockConfirm
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */

    @PostMapping(produces = "application/json", value = "/moveStockConfirm")
    public ResponseEntity<?> post(MoveStockOrder entity) {

        HttpStatusContent status = null;

        MoveStockOrder moveStockOrder = this.moveStockOrderRepository.findOne(entity.getId());
        if (moveStockOrder == null) {
            status = new HttpStatusContent(OutputState.FAIL, "移库单不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }

        if (moveStockOrder.getToWarehouse().getIsUse() != null
                && !moveStockOrder.getToWarehouse().getIsUse()) {
            status = new HttpStatusContent(OutputState.FAIL, "库层已停用,无法操作！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (moveStockOrder.getToWarehouse().getLockState()!=null
                && moveStockOrder.getToWarehouse().getLockState()) {
            status = new HttpStatusContent(OutputState.FAIL, "库层已锁定,无法操作！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<WarehouseStock> warehouseStocks = new ArrayList<>();
        //先删除信息
        WarehouseStock warehouseStockDel =  new WarehouseStock();
        warehouseStockDel.setWarehouseLevel(moveStockOrder.getWarehouseStock().getWarehouseLevel());
        warehouseStockDel.setProduct(moveStockOrder.getWarehouseStock().getProduct());
        warehouseStockDel.setCustomer(moveStockOrder.getWarehouseStock().getCustomer());
        warehouseStockDel.setNum(moveStockOrder.getNum().negate());
        warehouseStockDel.setTonNum(moveStockOrder.getTonNum().negate());
        warehouseStockDel.setCreateMan(moveStockOrder.getCreateMan());
        warehouseStockDel.setCreateManId(moveStockOrder.getCreateManId());
        warehouseStocks.add(warehouseStockDel);
        //增加到新库位
        WarehouseStock warehouseStockAdd = new WarehouseStock();
        warehouseStockAdd.setWarehouseLevel(moveStockOrder.getToWarehouse());
        warehouseStockAdd.setNum(moveStockOrder.getNum());
        warehouseStockAdd.setTonNum(moveStockOrder.getTonNum());
        warehouseStockAdd.setProduct(moveStockOrder.getWarehouseStock().getProduct());
        warehouseStockAdd.setCustomer(moveStockOrder.getWarehouseStock().getCustomer());
        warehouseStockAdd.setCreateMan(moveStockOrder.getCreateMan());
        warehouseStockAdd.setCreateManId(moveStockOrder.getCreateManId());
        warehouseStocks.add(warehouseStockAdd);

        //更新库存
        Boolean result = warehouseStockService.saveOrUpdate(warehouseStocks,
                moveStockOrder.getOrderNo(), moveStockOrder.getId(),
                WarehouseStockLogOpType.REMOVE_STOCK.getValue());

        if (result) {

            //更新移库单状态为确认
            moveStockOrder.setOrderState(MoveStockOrderState.CONFIRM.getValue());

            // 记录日志
            MoveStockOrderLog log = new MoveStockOrderLog();
            log.setMoveStockOrder(moveStockOrder);
            log.setRemark(moveStockOrder.getRemark());
            log.setCreateMan(moveStockOrder.getCreateMan());
            log.setCreateManId(moveStockOrder.getCreateManId());
            log.setOrderState(MoveStockOrderState.CONFIRM.getValue());

            Set<MoveStockOrderLog> logSet = new HashSet<>();
            logSet.add(log);
            moveStockOrder.setMoveStockOrderLogSet(logSet);// 记录库存移库日志(关联会自动写入)


            entity = this.moveStockOrderRepository.save(moveStockOrder);
            if (entity == null) {
                status = new HttpStatusContent(OutputState.FAIL, "移库失败，请稍后重试！");
                return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    /**
     * @api {POST} /moveStockOrder/moveStockState 移库订单状态变更
     * @apiGroup moveStockOrder
     * @apiVersion 0.0.1
     * @apiDescription 移库订单状态变更
     * @apiParam {String} ids 主键id 多个id,以逗号隔开
     * @apiParam {Integer} orderState 移库订单状态
     * - NEW_CREATE(50010, "新建"),
     * - INVALID(-50000, "作废");
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /moveStockOrder/moveStockState
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */

    @PostMapping(produces = "application/json", value = "/moveStockState")
    public ResponseEntity<?> moveStockStateChange(String ids,
                                                  Integer orderState,
                                                  String createMan,
                                                  String createManId) {

        HttpStatusContent status = null;

        if (EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)) {
            status = new HttpStatusContent(OutputState.FAIL, "操作人和操作人Id必传！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        if (!MoveStockOrderState.isExist(orderState)) {
            status = new HttpStatusContent(OutputState.FAIL, "移库状态参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (EmptyUtil.isNotEmpty(ids) && ids.split(",").length > 0) {
            String[] array = ids.split(",");
            for (int i = 0; i < array.length; i++) {

                MoveStockOrder moveStockOrder = this.moveStockOrderRepository.findOne(array[i]);
                if (moveStockOrder != null) {
                    moveStockOrder.setOrderState(orderState); //1.修改移库状态

                    // 记录日志
                    MoveStockOrderLog log = new MoveStockOrderLog();
                    log.setMoveStockOrder(moveStockOrder);
                    log.setRemark("状态由" + MoveStockOrderState.getTxtByValue(moveStockOrder.getOrderState()) + "变为" +
                            MoveStockOrderState.getTxtByValue(orderState));
                    log.setCreateMan(createMan);
                    log.setCreateManId(createManId);
                    log.setOrderState(orderState);

                    Set<MoveStockOrderLog> logSet = new HashSet<>();
                    logSet.add(log);
                    moveStockOrder.setMoveStockOrderLogSet(logSet);// 记录库存移库日志(关联会自动写入)
                    this.moveStockOrderRepository.save(moveStockOrder);

                }
            }


        } else {
            status = new HttpStatusContent(OutputState.FAIL, "缺少id参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);

    }


}

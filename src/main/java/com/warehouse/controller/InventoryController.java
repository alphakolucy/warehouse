package com.warehouse.controller;

import com.alibaba.fastjson.JSON;
import com.warehouse.model.*;
import com.warehouse.service.repository.InventoryRepository;
import com.warehouse.service.repository.ProductRepository;
import com.warehouse.service.repository.WarehouseRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.InventoryState;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.tools.BusinessNo;
import com.warehouse.util.tools.EmptyUtil;
import com.warehouse.util.tools.ObjectCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 库存盘点-管理
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ProductRepository productRepository;

    /**
     * @api {GET} /inventory 库存盘点列表
     * @apiGroup inventory
     * @apiVersion 0.0.1
     * @apiDescription 库存盘点列表
     * @apiParam {String} [orderNo] 单号(支持模糊查询)
     * @apiParam {String} [warehouseSiteId] 仓库Id(一级)
     * @apiParam {String} [warehouseAreaId] 库区Id(二级)
     * @apiParam {String} [warehouseId] 库位Id(三级)
     * @apiParam {String} [warehouseLevelId] 库层Id(4级)
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /inventory
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {BigDecimal} num 操作件数
     * @apiSuccess (200) {BigDecimal} tonNum 操作吨数
     * @apiSuccess (200) {String} orderStateTxt 盘点状态名称
     * @apiSuccess (200) {Integer} orderState 盘点状态
     * - NEW_CREATE(50010, "新建"),
     * - CONFIRM(50020, "确认"),
     * - INVALID(-50000, "作废");
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {Object} warehouseSiteData 仓库对象
     * @apiSuccess (200) {String} warehouseSiteData.id id
     * @apiSuccess (200) {String} warehouseSiteData.name 名称
     * @apiSuccess (200) {Object} warehouseAreaData 库区对象
     * @apiSuccess (200) {String} warehouseAreaData.id id
     * @apiSuccess (200) {String} warehouseAreaData.name 名称
     * @apiSuccess (200) {Object} warehouseData 库位对象
     * @apiSuccess (200) {String} warehouseData.id id
     * @apiSuccess (200) {String} warehouseData.name 名称
     * @apiSuccess (200) {Object} warehouseLevelData 库层对象
     * @apiSuccess (200) {String} warehouseLevelData.id id
     * @apiSuccess (200) {String} warehouseLevelData.name 名称
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Inventory>> findAll(Inventory entity,
                                                   String warehouseSiteId,
                                                   String warehouseAreaId,
                                                   String warehouseId,
                                                   String warehouseLevelId,
                                                   @PageableDefault(value = 10,
                                                           sort = {"createTime"},
                                                           direction = Sort.Direction.DESC)
                                                           Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //盘点单号模糊方式查
                .withMatcher("orderNo", ExampleMatcher.GenericPropertyMatchers.contains());

        //仓库
        if (EmptyUtil.isNotEmpty(warehouseSiteId)) {
            Warehouse warehouseSite = new Warehouse();
            warehouseSite.setId(warehouseSiteId);
            entity.setWarehouseSite(warehouseSite);
        }
        //库区
        if (EmptyUtil.isNotEmpty(warehouseAreaId)) {
            Warehouse warehouseArea = new Warehouse();
            warehouseArea.setId(warehouseAreaId);
            entity.setWarehouseArea(warehouseArea);
        }
        //库位
        if (EmptyUtil.isNotEmpty(warehouseId)) {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(warehouseId);
            entity.setWarehouse(warehouse);
        }
        //库层
        if (EmptyUtil.isNotEmpty(warehouseLevelId)) {
            Warehouse warehouseLevel = new Warehouse();
            warehouseLevel.setId(warehouseLevelId);
            entity.setWarehouseLevel(warehouseLevel);
        }

        Example<Inventory> example = Example.of(entity, matcher);
        Page<Inventory> apply = this.inventoryRepository.findAll(example, pageable);
        return new ResponseEntity<Page<Inventory>>(apply, HttpStatus.OK);
    }

    /**
     * @api {GET} /inventory/{id} 库存盘点详情
     * @apiGroup inventory
     * @apiVersion 0.0.1
     * @apiDescription 库存盘点详情
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /inventory/1
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {BigDecimal} num 操作件数
     * @apiSuccess (200) {BigDecimal} tonNum 操作吨数
     * @apiSuccess (200) {String} orderStateTxt 盘点状态名称
     * @apiSuccess (200) {Integer} orderState 盘点状态
     * - NEW_CREATE(50010, "新建"),
     * - CONFIRM(50020, "确认"),
     * - INVALID(-50000, "作废");
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人Id
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {Object} warehouseSiteData 仓库对象
     * @apiSuccess (200) {String} warehouseSiteData.id id
     * @apiSuccess (200) {String} warehouseSiteData.name 名称
     * @apiSuccess (200) {Object} warehouseAreaData 库区对象
     * @apiSuccess (200) {String} warehouseAreaData.id id
     * @apiSuccess (200) {String} warehouseAreaData.name 名称
     * @apiSuccess (200) {Object} warehouseData 库位对象
     * @apiSuccess (200) {String} warehouseData.id id
     * @apiSuccess (200) {String} warehouseData.name 名称
     * @apiSuccess (200) {Object} warehouseLevelData 库层对象
     * @apiSuccess (200) {String} warehouseLevelData.id id
     * @apiSuccess (200) {String} warehouseLevelData.name 名称
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        Inventory entity = this.inventoryRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    /**
     * @api {POST} /inventory 库存盘点添加
     * @apiGroup inventory
     * @apiVersion 0.0.1
     * @apiDescription 库存盘点添加
     * @apiParam (200) {BigDecimal} num 操作件数
     * @apiParam (200) {BigDecimal} tonNum 操作吨数
     * @apiParam (200) {String} createMan 操作人
     * @apiParam (200) {String} createManId 操作人Id
     * @apiParam (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiParam (200) {String} remark 备注
     * @apiParam (200) {String} warehouseSiteId 仓库Id(四选一)
     * @apiParam (200) {String} warehouseAreaId 库区Id(四选一)
     * @apiParam (200) {String} warehouseId 库位Id(四选一)
     * @apiParam (200) {String} warehouseLevelId 库层Id(四选一)
     * @apiParam (200) {List} detailsJson 明细列表Json
     * @apiParam (200) {Object} Object 明细单个对象
     * @apiParam (200) {String} Object.productId 物资id
     * @apiParam (200) {BigDecimal} Object.num 件数
     * @apiParam (200) {BigDecimal} Object.tonNum 吨数
     * @apiParamExample 请求明文：
     * /inventory
     * @apiSuccess (200) {String} id 主键id
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(Inventory entity,
                                  String warehouseSiteId,
                                  String warehouseAreaId,
                                  String warehouseId,
                                  String warehouseLevelId,
                                  String detailsJson) {
        HttpStatusContent status = null;

        if (EmptyUtil.isEmpty(detailsJson)) {
            status = new HttpStatusContent(OutputState.FAIL, "盘点明细不能为空！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        entity.setOrderNo(BusinessNo.getBusinessNo("CKPD"));
        entity.setOrderState(InventoryState.NEW_CREATE.getValue());//默认状态新建 

        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Set<InventoryDetail> detailSet = new HashSet<>();
        List<InventoryDetail> detailList = JSON.parseArray(detailsJson, InventoryDetail.class);
        ;
        for (InventoryDetail detail : detailList) {
            if(EmptyUtil.isEmpty(detail.getProductId())){
                status = new HttpStatusContent(OutputState.FAIL, "明细缺少物资参数");
                return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Product product = this.productRepository.findOne(detail.getProductId());
            if (product == null) {
                status = new HttpStatusContent(OutputState.FAIL, "物资不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            if(EmptyUtil.isEmpty(detail.getWarehouseLevelId())){
                status = new HttpStatusContent(OutputState.FAIL, "明细缺少库层参数");
                return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Warehouse warehouseLevel = this.warehouseRepository.findOne(detail.getWarehouseLevelId());
            if (warehouseLevel == null || warehouseLevel.getLevelOrder() != 4) {
                status = new HttpStatusContent(OutputState.FAIL, "库层不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            detail.setWarehouseLevel(warehouseLevel);
            detail.setWarehouse(warehouseLevel.getParent());
            detail.setWarehouseArea(warehouseLevel.getParent().getParent());
            detail.setWarehouseSite(warehouseLevel.getParent().getParent().getParent());
            detail.setProduct(product);
            detail.setInventory(entity);
            detailSet.add(detail);
        }

        //选的库层
        if (EmptyUtil.isNotEmpty(warehouseLevelId)) {
            Warehouse warehouseLevel = this.warehouseRepository.findOne(warehouseLevelId);
            if (warehouseLevel == null || warehouseLevel.getLevelOrder() != 4) {
                status = new HttpStatusContent(OutputState.FAIL, "库层不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            entity.setWarehouseSite(warehouseLevel.getParent().getParent().getParent());
            entity.setWarehouseArea(warehouseLevel.getParent().getParent());
            entity.setWarehouse(warehouseLevel.getParent());
            entity.setWarehouseLevel(warehouseLevel);
        }//选的库位
        else if (EmptyUtil.isNotEmpty(warehouseId)) {
            Warehouse warehouse = this.warehouseRepository.findOne(warehouseId);
            if (warehouse == null || warehouse.getLevelOrder() != 3) {
                status = new HttpStatusContent(OutputState.FAIL, "库位不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            entity.setWarehouseSite(warehouse.getParent().getParent());
            entity.setWarehouseArea(warehouse.getParent());
            entity.setWarehouse(warehouse);
            entity.setWarehouseLevel(null);
        }//选的库区
        else if (EmptyUtil.isNotEmpty(warehouseAreaId)) {
            Warehouse warehouseArea = this.warehouseRepository.findOne(warehouseAreaId);
            if (warehouseArea == null || warehouseArea.getLevelOrder() != 2) {
                status = new HttpStatusContent(OutputState.FAIL, "库区不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            entity.setWarehouseSite(warehouseArea.getParent());
            entity.setWarehouseArea(warehouseArea);
            entity.setWarehouse(null);
            entity.setWarehouseLevel(null);
        }//选的仓库
        else if (EmptyUtil.isNotEmpty(warehouseSiteId)) {
            Warehouse warehouseSite = this.warehouseRepository.findOne(warehouseSiteId);
            if (warehouseSite == null || warehouseSite.getLevelOrder() != 1) {
                status = new HttpStatusContent(OutputState.FAIL, "仓库不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            entity.setWarehouseSite(warehouseSite);
            entity.setWarehouseArea(null);
            entity.setWarehouse(null);
            entity.setWarehouseLevel(null);
        } else {
            status = new HttpStatusContent(OutputState.FAIL, "请选择仓库信息！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }


        // 记录日志
        InventoryLog log = new InventoryLog();
        log.setInventory(entity);
        log.setOrderState(InventoryState.NEW_CREATE.getValue());
        log.setRemark(entity.getRemark());
        log.setCreateMan(entity.getCreateMan());
        log.setCreateManId(entity.getCreateManId());

        Set<InventoryLog> logSet = new HashSet<>();
        logSet.add(log);
        entity.setInventoryLogSet(logSet);// 记录库存盘点日志(关联会自动写入)
        entity.setInventoryDetailSet(detailSet);// 记录库存盘点明细(关联会自动写入)

        entity = this.inventoryRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    /**
     * @api {PUT} /inventory 库存盘点修改
     * @apiGroup inventory
     * @apiVersion 0.0.1
     * @apiDescription 库存盘点修改
     * @apiParam (200) {String} id 库存盘点Id
     * @apiParam (200) {String} warehouseSiteId 仓库Id(四选一)
     * @apiParam (200) {String} warehouseAreaId 库区Id(四选一)
     * @apiParam (200) {String} warehouseId 库位Id(四选一)
     * @apiParam (200) {String} warehouseLevelId 库层Id(四选一)
     * @apiParam (200) {List} detailsJson 明细对象列表
     * @apiParam (200) {Object} Object 明细单个对象
     * @apiParam (200) {String} Object.productId 物资id
     * @apiParam (200) {BigDecimal} Object.num 件数
     * @apiParam (200) {BigDecimal} Object.tonNum 吨数
     * @apiParam (200) {String} createMan 操作人
     * @apiParam (200) {String} createManId 操作人Id
     * @apiParam (200) {String} remark 备注
     * @apiParamExample 请求明文：
     * /inventory
     * @apiSuccess (200) {String} id 主键id
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 String warehouseSiteId,
                                 String warehouseAreaId,
                                 String warehouseId,
                                 String warehouseLevelId,
                                 String detailsJson,
                                 Inventory entity) {

        HttpStatusContent status = null;

        Inventory inventory = this.inventoryRepository.getOne(id);
        if (inventory == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }

        if (EmptyUtil.isNotEmpty(detailsJson)) {
            Set<InventoryDetail> detailSet = new HashSet<>();
            List<InventoryDetail> detailList = JSON.parseArray(detailsJson, InventoryDetail.class);
            ;
            for (InventoryDetail detail : detailList) {
                Map<String, StringBuffer> vIDetail = ValidatorUtil.validate(detail);
                if (vIDetail != null) {
                    status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(vIDetail));
                    return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if(EmptyUtil.isEmpty(detail.getProductId())){
                    status = new HttpStatusContent(OutputState.FAIL, "明细缺少物资参数");
                    return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                Product product = this.productRepository.findOne(detail.getProductId());
                if (product == null) {
                    status = new HttpStatusContent(OutputState.FAIL, "物资不存在！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
                }
                if(EmptyUtil.isEmpty(detail.getWarehouseLevelId())){
                    status = new HttpStatusContent(OutputState.FAIL, "明细缺少库层参数");
                    return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                Warehouse warehouseLevel = this.warehouseRepository.findOne(detail.getWarehouseLevelId());
                if (warehouseLevel == null || warehouseLevel.getLevelOrder() != 4) {
                    status = new HttpStatusContent(OutputState.FAIL, "库层不存在！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
                }
                detail.setWarehouseLevel(warehouseLevel);
                detail.setWarehouse(warehouseLevel.getParent());
                detail.setWarehouseArea(warehouseLevel.getParent().getParent());
                detail.setWarehouseSite(warehouseLevel.getParent().getParent().getParent());
                detail.setProduct(product);
                detail.setInventory(inventory);
                detailSet.add(detail);
            }
            inventory.setInventoryDetailSet(detailSet);
        }

        //选的库层
        if (EmptyUtil.isNotEmpty(warehouseLevelId)) {
            Warehouse warehouseLevel = this.warehouseRepository.findOne(warehouseLevelId);
            if (warehouseLevel == null || warehouseLevel.getLevelOrder() != 4) {
                status = new HttpStatusContent(OutputState.FAIL, "库层不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            entity.setWarehouseSite(warehouseLevel.getParent().getParent().getParent());
            entity.setWarehouseArea(warehouseLevel.getParent().getParent());
            entity.setWarehouse(warehouseLevel.getParent());
            entity.setWarehouseLevel(warehouseLevel);
        }//选的库位
        else if (EmptyUtil.isNotEmpty(warehouseId)) {
            Warehouse warehouse = this.warehouseRepository.findOne(warehouseId);
            if (warehouse == null || warehouse.getLevelOrder() != 3) {
                status = new HttpStatusContent(OutputState.FAIL, "库位不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            entity.setWarehouseSite(warehouse.getParent().getParent());
            entity.setWarehouseArea(warehouse.getParent());
            entity.setWarehouse(warehouse);
            entity.setWarehouseLevel(null);
        }//选的库区
        else if (EmptyUtil.isNotEmpty(warehouseAreaId)) {
            Warehouse warehouseArea = this.warehouseRepository.findOne(warehouseAreaId);
            if (warehouseArea == null || warehouseArea.getLevelOrder() != 2) {
                status = new HttpStatusContent(OutputState.FAIL, "库区不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            entity.setWarehouseSite(warehouseArea.getParent());
            entity.setWarehouseArea(warehouseArea);
            entity.setWarehouse(null);
            entity.setWarehouseLevel(null);
        }//选的仓库
        else if (EmptyUtil.isNotEmpty(warehouseSiteId)) {
            Warehouse warehouseSite = this.warehouseRepository.findOne(warehouseSiteId);
            if (warehouseSite == null || warehouseSite.getLevelOrder() != 1) {
                status = new HttpStatusContent(OutputState.FAIL, "仓库不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
            }
            entity.setWarehouseSite(warehouseSite);
            entity.setWarehouseArea(null);
            entity.setWarehouse(null);
            entity.setWarehouseLevel(null);
        }

        ObjectCopyUtil<Inventory> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, inventory);

        // 记录日志
        InventoryLog log = new InventoryLog();
        log.setInventory(inventory);
        log.setRemark(entity.getRemark());
        log.setCreateMan(entity.getCreateMan());
        log.setCreateManId(entity.getCreateManId());

        Set<InventoryLog> logSet = new HashSet<>();
        logSet.add(log);
        inventory.setInventoryLogSet(logSet);// 记录库存盘点日志(关联会自动写入)

        entity = this.inventoryRepository.save(inventory);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    /**
     * @api {POST} /inventory/inventoryState 盘点状态变更
     * @apiGroup inventory
     * @apiVersion 0.0.1
     * @apiDescription 盘点状态变更
     * @apiParam {String} id 主键id
     * @apiParam {Integer} orderState 盘点状态
     * - NEW_CREATE(50010, "新建"),
     * - CONFIRM(50020, "确认"),
     * - INVALID(-50000, "作废");
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /inventory/inventoryState
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */

    @PostMapping(produces = "application/json", value = "/inventoryState")
    public ResponseEntity<?> InventoryChange(String id,
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

        Inventory inventory = this.inventoryRepository.findOne(id);
        if (inventory == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        //变更前状态
        Integer preState = inventory.getOrderState();

        if (orderState.equals(preState)) {
            status = new HttpStatusContent(OutputState.FAIL, "当前状态已经是：" + InventoryState.getTxtByValue(preState));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (!InventoryState.isExist(orderState)) {
            status = new HttpStatusContent(OutputState.FAIL, "盘点状态参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        inventory.setOrderState(orderState); //1.修改库存盘点状态

        // 记录日志
        InventoryLog log = new InventoryLog();
        log.setInventory(inventory);
        log.setRemark("状态由" + InventoryState.getTxtByValue(preState) + "变为" + InventoryState.getTxtByValue(orderState));
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(orderState);

        Set<InventoryLog> logSet = new HashSet<>();
        logSet.add(log);
        inventory.setInventoryLogSet(logSet);// 记录库存调整日志(关联会自动写入)

        if (this.inventoryRepository.save(inventory) != null) {
            status = new HttpStatusContent(OutputState.SUCCESS);
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
        }
        status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
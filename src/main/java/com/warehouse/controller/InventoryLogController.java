package com.warehouse.controller;

import com.warehouse.model.Inventory;
import com.warehouse.model.InventoryDetail;
import com.warehouse.model.InventoryLog;
import com.warehouse.model.WarehouseStock;
import com.warehouse.service.repository.InventoryDetailRepository;
import com.warehouse.service.repository.InventoryLogRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.tools.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存盘点日志
 */
@RestController
@RequestMapping("/inventoryLog")
public class InventoryLogController {
    @Autowired
    private InventoryLogRepository inventoryLogRepository;

    /**
     * @api {GET} /inventoryLog 库存盘点日志列表
     * @apiGroup inventoryLog
     * @apiVersion 0.0.1
     * @apiDescription 库存盘点日志列表
     * @apiParam {String} [inventoryId] 库存盘点Id
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=createTime,desc 表示在按id由高到低排列
     * - 格式： sort=createTime,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /inventoryLog?sort=createTime,asc
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人id
     * @apiSuccess (200) {String} orderStateTxt  盘点日志状态名称
     * @apiSuccess (200) {Integer} orderState  盘点日志状态
     * - NEW_CREATE(50010, "新建"),
     * - CONFIRM(50020, "确认"),
     * - INVALID(-50000, "作废");
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {String} inventoryData.remark 备注
     * @apiSuccess (200) {Object} inventory 盘点对象
     * @apiSuccess (200) {String} inventory.id id
     * @apiSuccess (200) {String} inventory.warehouseStockId 库存id
     * @apiSuccess (200) {String} inventory.orderNo 盘点单号
     * @apiSuccess (200) {Integer} inventory.orderState 盘点状态
     * @apiSuccess (200) {String} inventory.orderStateTxt 盘点状态名称
     * @apiSuccess (200) {String} inventory.createMan 操作人
     * @apiSuccess (200) {String} inventory.createManId 操作人id
     * @apiSuccess (200) {BigDecimal} inventory.num 库存件数
     * @apiSuccess (200) {BigDecimal} inventory.tonNum 库存吨数
     * @apiSuccess (200) {DateTime} inventory.createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {String} inventory.remark 备注
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<InventoryLog>> findAll(InventoryLog entity,
                                                      @PageableDefault(value = 10, sort = {"createTime"},
                                                              direction = Sort.Direction.DESC)
                                                              Pageable pageable,
                                                      String inventoryId) {
        //库存盘点Id查询
        if (EmptyUtil.isNotEmpty(inventoryId)) {
            Inventory inventory = new Inventory();
            inventory.setId(inventoryId);
            entity.setInventory(inventory);
        }

        Example<InventoryLog> example = Example.of(entity);

        Page<InventoryLog> apply = this.inventoryLogRepository.findAll(example, pageable);
        return new ResponseEntity<Page<InventoryLog>>(apply, HttpStatus.OK);
    }

    /**
     * @api {GET} /inventoryLog/{id} 库存盘点日志详情
     * @apiGroup inventoryLog
     * @apiVersion 0.0.1
     * @apiDescription 库存盘点日志详情
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /inventoryLog/1
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人id
     * @apiSuccess (200) {String} orderStateTxt  盘点日志状态名称
     * @apiSuccess (200) {Integer} orderState  盘点日志状态
     * - NEW_CREATE(50010, "新建"),
     * - CONFIRM(50020, "确认"),
     * - INVALID(-50000, "作废");
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {String} inventoryData.remark 备注
     * @apiSuccess (200) {Object} inventory 盘点对象
     * @apiSuccess (200) {String} inventory.id id
     * @apiSuccess (200) {String} inventory.warehouseStockId 库存id
     * @apiSuccess (200) {String} inventory.orderNo 盘点单号
     * @apiSuccess (200) {Integer} inventory.orderState 盘点状态
     * @apiSuccess (200) {String} inventory.orderStateTxt 盘点状态名称
     * @apiSuccess (200) {String} inventory.createMan 操作人
     * @apiSuccess (200) {String} inventory.createManId 操作人id
     * @apiSuccess (200) {BigDecimal} inventory.num 库存件数
     * @apiSuccess (200) {BigDecimal} inventory.tonNum 库存吨数
     * @apiSuccess (200) {DateTime} inventory.createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {String} inventory.remark 备注
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        InventoryLog entity = this.inventoryLogRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}

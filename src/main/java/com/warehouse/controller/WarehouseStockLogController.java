package com.warehouse.controller;

import com.warehouse.model.*;
import com.warehouse.service.repository.WarehouseStockLogRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.tools.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 仓库库存日志
 */
@RestController
@RequestMapping("/warehouseStockLog")
public class WarehouseStockLogController {

    @Autowired
    private WarehouseStockLogRepository warehouseStockLogRepository;

    /**
     * @api {GET} /warehouseStockLog 仓库库存日志列表
     * @apiGroup warehouseStockLog
     * @apiVersion 0.0.1
     * @apiDescription 仓库库存日志列表
     * @apiParam {String} [warehouseSotckId] 库存Id
     * @apiParam {String} [warehouseSiteId] 仓库id
     * @apiParam {String} [warehouseAreaId] 库区id
     * @apiParam {String} [warehouseId] 库位id
     * @apiParam {String} [productId] 物资id
     * @apiParam {String} [opOrderNo] 操作单号(支持模糊查询)
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=createTime,asc] 排序
     * - 格式： sort=createTime,desc 表示在按createTime由高到低排列
     * - 格式： sort=createTime,asc 表示在按createTime由低到高排列
     * @apiParamExample 请求明文：
     * /warehouseStockLog
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} opOrderNo 操作单号
     * @apiSuccess (200) {String} opOrderId 操作单号Id
     * @apiSuccess (200) {String} opTypeTxt 操作单类型名称
     * @apiSuccess (200) {Integer} opType 操作单类型
     * - TAKE_OVER(15010, "收货"),
     * - CARRY_GOOD(15020, "提货"),
     * - REMOVE_STOCK(15030, "移库"),
     * - TRANSFER_NAME(15040, "过户"),
     * - ADJUST_NAME(15050, "调整"),
     * - INVENTORY_NAME(15060, "盘库");
     * @apiSuccess (200) {BigDecimal} opNum 操作件数
     * @apiSuccess (200) {BigDecimal} opTonNum 操作吨数
     * @apiSuccess (200) {BigDecimal} blaNum 结余件数
     * @apiSuccess (200) {BigDecimal} blaTonNum 结余吨数
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     *
     * @apiSuccess (200) {Object} warehouseStock 库存实体
     * @apiSuccess (200) {String} warehouseStock.id 仓库Id
     * @apiSuccess (200) {String} warehouseStock.num 库存件数
     * @apiSuccess (200) {String} warehouseStock.tonNum 库存吨数
     * @apiSuccess (200) {String} warehouseStock.createMan 操作人
     * @apiSuccess (200) {String} warehouseStock.createManId 操作人
     * @apiSuccess (200) {DateTime} warehouseStock.createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     *
     * @apiSuccess (200) {Object} warehouseStock.warehouseSiteData 仓库实体
     * @apiSuccess (200) {String} warehouseStock.warehouseSiteData.id 库区Id
     * @apiSuccess (200) {String} warehouseStock.warehouseSiteData.name 仓库名称
     *
     * @apiSuccess (200) {Object} warehouseStock.warehouseAreaData 库区实体
     * @apiSuccess (200) {String} warehouseStock.warehouseAreaData.id 库区Id
     * @apiSuccess (200) {String} warehouseStock.warehouseAreaData.name 库区名称
     *
     * @apiSuccess (200) {Object} warehouseStock.warehouseData 库位实体
     * @apiSuccess (200) {String} warehouseStock.warehouseData.id 库位Id
     * @apiSuccess (200) {String} warehouseStock.warehouseData.name 库位名称
     *
     * @apiSuccess (200) {Object} warehouseStock.productData 物资实体
     * @apiSuccess (200) {String} warehouseStock.productData.id 物资Id
     * @apiSuccess (200) {String} warehouseStock.productData.productName 物资名称    *
     *
     * @apiSuccess (200) {Object} warehouseStock.customerData 客户实体
     * @apiSuccess (200) {String} warehouseStock.customerData.id 客户Id
     * @apiSuccess (200) {String} warehouseStock.customerData.name 客户名称
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<WarehouseStockLog>> findAll(WarehouseStockLog entity,
                                                           String warehouseSotckId,
                                                           String warehouseSiteId,
                                                           String warehouseAreaId,
                                                           String warehouseId,
                                                           String productId,
                                                           String customerId,
                                                           @PageableDefault(value = 10,
                                                                   sort = {"createTime"},
                                                                   direction = Sort.Direction.DESC)
                                                                   Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //操作单号模糊方式查
                .withMatcher("opOrderNo", ExampleMatcher.GenericPropertyMatchers.contains());

        WarehouseStock warehouseStock = new WarehouseStock();
        // 根据仓库库存warehouseSotckId查询
        if (EmptyUtil.isNotEmpty(warehouseSotckId)) {
            warehouseStock.setId(warehouseSotckId);
            entity.setWarehouseStock(warehouseStock);
        }

        // 根据仓库customerId查询
        if (EmptyUtil.isNotEmpty(customerId)) {
            Customer customer = new Customer();
            customer.setId(customerId);
            entity.getWarehouseStock().setCustomer(customer);
        }

        // 根据仓库warehouseSiteId查询
        if (EmptyUtil.isNotEmpty(warehouseSiteId)) {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(warehouseSiteId);
            entity.getWarehouseStock().setWarehouseSite(warehouse);
        }

        // 根据仓库warehouseAreaId查询
        if (EmptyUtil.isNotEmpty(warehouseAreaId)) {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(warehouseAreaId);
            entity.getWarehouseStock().setWarehouseArea(warehouse);
        }

        // 根据仓库warehouseId查询
        if (EmptyUtil.isNotEmpty(warehouseId)) {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(warehouseId);
            entity.getWarehouseStock().setWarehouse(warehouse);
        }

        // 根据物资productId查询
        if (EmptyUtil.isNotEmpty(productId)) {
            Product product = new Product();
            product.setId(productId);
            entity.getWarehouseStock().setProduct(product);
        }


        Example<WarehouseStockLog> example = Example.of(entity, matcher);
        Page<WarehouseStockLog> apply = this.warehouseStockLogRepository.findAll(example, pageable);
        return new ResponseEntity<Page<WarehouseStockLog>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /warehouseStockLog/{id} 仓库库存日志详细
     * @apiGroup warehouseStockLog
     * @apiVersion 0.0.1
     * @apiDescription 仓库库存日志详细
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /warehouseStockLog/1
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} opOrderNo 操作单号
     * @apiSuccess (200) {String} opOrderId 操作单号Id
     * @apiSuccess (200) {String} opTypeTxt 操作单类型名称
     * @apiSuccess (200) {Integer} opType 操作单类型
     * - TAKE_OVER(15010, "收货"),
     * - CARRY_GOOD(15020, "提货"),
     * - REMOVE_STOCK(15030, "移库"),
     * - TRANSFER_NAME(15040, "过户"),
     * - ADJUST_NAME(15050, "调整"),
     * - INVENTORY_NAME(15060, "盘库");
     * @apiSuccess (200) {BigDecimal} opNum 操作件数
     * @apiSuccess (200) {BigDecimal} opTonNum 操作吨数
     * @apiSuccess (200) {BigDecimal} blaNum 结余件数
     * @apiSuccess (200) {BigDecimal} blaTonNum 结余吨数
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     *
     * @apiSuccess (200) {Object} warehouseStock 库存实体
     * @apiSuccess (200) {String} warehouseStock.id 仓库Id
     * @apiSuccess (200) {String} warehouseStock.num 库存件数
     * @apiSuccess (200) {String} warehouseStock.tonNum 库存吨数
     * @apiSuccess (200) {String} warehouseStock.createMan 操作人
     * @apiSuccess (200) {String} warehouseStock.createManId 操作人
     * @apiSuccess (200) {DateTime} warehouseStock.createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     *
     * @apiSuccess (200) {Object} warehouseStock.warehouseSiteData 仓库实体
     * @apiSuccess (200) {String} warehouseStock.warehouseSiteData.id 库区Id
     * @apiSuccess (200) {String} warehouseStock.warehouseSiteData.name 仓库名称
     *
     * @apiSuccess (200) {Object} warehouseStock.warehouseAreaData 库区实体
     * @apiSuccess (200) {String} warehouseStock.warehouseAreaData.id 库区Id
     * @apiSuccess (200) {String} warehouseStock.warehouseAreaData.name 库区名称
     *
     * @apiSuccess (200) {Object} warehouseStock.warehouseData 库位实体
     * @apiSuccess (200) {String} warehouseStock.warehouseData.id 库位Id
     * @apiSuccess (200) {String} warehouseStock.warehouseData.name 库位名称
     *
     * @apiSuccess (200) {Object} warehouseStock.productData 物资实体
     * @apiSuccess (200) {String} warehouseStock.productData.id 物资Id
     * @apiSuccess (200) {String} warehouseStock.productData.productName 物资名称    *
     *
     * @apiSuccess (200) {Object} warehouseStock.customerData 客户实体
     * @apiSuccess (200) {String} warehouseStock.customerData.id 客户Id
     * @apiSuccess (200) {String} warehouseStock.customerData.name 客户名称
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        WarehouseStockLog entity = this.warehouseStockLogRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }


}

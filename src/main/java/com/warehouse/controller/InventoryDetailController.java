package com.warehouse.controller;

import com.warehouse.model.*;
import com.warehouse.service.repository.InventoryDetailRepository;
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
 * 库存盘点单明细
 */
@RestController
@RequestMapping("/inventoryDetail")
public class InventoryDetailController {
    @Autowired
    private InventoryDetailRepository inventoryDetailRepository;

    /**
     * @api {GET} /inventoryDetail 库存盘点单明细列表
     * @apiGroup inventoryDetail
     * @apiVersion 0.0.1
     * @apiDescription 库存盘点单明细列表
     * @apiParam {String} [inventoryId] 库存盘点Id
     * @apiParam {String} [productId] 物资Id
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=createTime,desc 表示在按id由高到低排列
     * - 格式： sort=createTime,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /inventoryDetail?sort=createTime,asc
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {BigDecimal} num 件数
     * @apiSuccess (200) {BigDecimal} tonNum 吨数
     * @apiSuccess (200) {Object} inventoryData 库存盘点对象
     * @apiSuccess (200) {String} inventoryData.id id
     * @apiSuccess (200) {String} inventoryData.orderNo 盘点单号
     * @apiSuccess (200) {BigDecimal} inventoryData.num 库存件数
     * @apiSuccess (200) {BigDecimal} inventoryData.tonNum 库存吨数
     * @apiSuccess (200) {String} inventoryData.createMan 创建人
     * @apiSuccess (200) {String} inventoryData.createManId 创建人id
     * @apiSuccess (200) {DateTime} inventoryData.createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {String} inventoryData.orderStateTxt 盘点状态名称
     * @apiSuccess (200) {Integer} inventoryData.orderState 盘点状态
     * - NEW_CREATE(50010, "新建"),
     * - CONFIRM(50020, "确认"),
     * - INVALID(-50000, "作废");
     * @apiSuccess (200) {String} inventoryData.remark 备注
     *
     * @apiSuccess (200) {Object} productData 物资对象
     * @apiSuccess (200) {String} productData.id id
     * @apiSuccess (200) {String} productData.productName 物资名称
     * @apiSuccess (200) {String} productData.unit 物资单位
     * @apiSuccess (200) {String} productData.rationale 物资理重
     * @apiSuccess (200) {String} productData.spec 物资规格
     * @apiSuccess (200) {String} productData.model 物资型号
     * @apiSuccess (200) {String} productData.material 物资材料
     * @apiSuccess (200) {String} productData.maker 物资厂家
     * @apiSuccess (200) {String} productData.productCateName 分类名
     *
     * @apiSuccess (200) {Object} warehouseLevelData 库层对象
     * @apiSuccess (200) {String} warehouseLevelData.id id
     * @apiSuccess (200) {String} warehouseLevelData.name 名称
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<InventoryDetail>> findAll(InventoryDetail entity,
                                                         @PageableDefault(value = 10, sort = {"createTime"},
                                                                 direction = Sort.Direction.DESC)
                                                                 Pageable pageable,
                                                         String inventoryId,
                                                         String warehouseStockId) {
        //库存盘点Id查询
        if (EmptyUtil.isNotEmpty(inventoryId)) {
            Inventory inventory = new Inventory();
            inventory.setId(inventoryId);
            entity.setInventory(inventory);
        }

        Example<InventoryDetail> example = Example.of(entity);

        Page<InventoryDetail> apply = this.inventoryDetailRepository.findAll(example, pageable);
        return new ResponseEntity<Page<InventoryDetail>>(apply, HttpStatus.OK);
    }

    /**
     * @api {GET} /inventoryDetail/{id} 库存盘点单明细详情
     * @apiGroup inventoryDetail
     * @apiVersion 0.0.1
     * @apiDescription 库存盘点单明细详情
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /inventoryDetail/1
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {BigDecimal} num 件数
     * @apiSuccess (200) {BigDecimal} tonNum 吨数
     * @apiSuccess (200) {Object} inventoryData 库存盘点对象
     * @apiSuccess (200) {String} inventoryData.id id
     * @apiSuccess (200) {String} inventoryData.orderNo 盘点单号
     * @apiSuccess (200) {String} inventoryData.num 库存件数
     * @apiSuccess (200) {String} inventoryData.tonNum 库存吨数
     * @apiSuccess (200) {String} inventoryData.createMan 创建人
     * @apiSuccess (200) {String} inventoryData.createManId 创建人id
     * @apiSuccess (200) {DateTime} inventoryData.createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {String} inventoryData.orderStateTxt 盘点状态名称
     * @apiSuccess (200) {Integer} inventoryData.orderState 盘点状态
     * - NEW_CREATE(50010, "新建"),
     * - CONFIRM(50020, "确认"),
     * - INVALID(-50000, "作废");
     * @apiSuccess (200) {String} inventoryData.remark 备注
     *
     * @apiSuccess (200) {Object} productData 物资对象
     * @apiSuccess (200) {String} productData.id id
     * @apiSuccess (200) {String} productData.productName 物资名称
     * @apiSuccess (200) {String} productData.unit 物资单位
     * @apiSuccess (200) {String} productData.rationale 物资理重
     * @apiSuccess (200) {String} productData.spec 物资规格
     * @apiSuccess (200) {String} productData.model 物资型号
     * @apiSuccess (200) {String} productData.material 物资材料
     * @apiSuccess (200) {String} productData.maker 物资厂家
     * @apiSuccess (200) {String} productData.productCateName 分类名
     *
     * @apiSuccess (200) {Object} warehouseLevelData 库层对象
     * @apiSuccess (200) {String} warehouseLevelData.id id
     * @apiSuccess (200) {String} warehouseLevelData.name 名称
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        InventoryDetail entity = this.inventoryDetailRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}

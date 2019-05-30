package com.warehouse.controller;

import com.warehouse.model.Transfer;
import com.warehouse.model.TransferDetail;
import com.warehouse.model.WarehouseStock;
import com.warehouse.service.repository.TransferDetailRepository;
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
 * 过户单明细
 */
@RestController
@RequestMapping("/transferDetail")
public class TransferDetailController {
    @Autowired
    private TransferDetailRepository transferDetailRepository;

    /**
     * @api {GET} /transferDetail 过户单单明细列表
     * @apiGroup transferDetail
     * @apiVersion 0.0.1
     * @apiDescription 过户单单明细列表
     * @apiParam {String} [transferId] 过户单Id
     * @apiParam {String} [warehouseStockId] 库存单Id
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=createTime,desc 表示在按id由高到低排列
     * - 格式： sort=createTime,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /transferDetail?sort=createTime,asc
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {BigDecimal} num 件数
     * @apiSuccess (200) {BigDecimal} tonNum 吨数
     * @apiSuccess (200) {BigDecimal} fee 费用
     * @apiSuccess (200) {Object} transferData 过户单对象
     * @apiSuccess (200) {String} transferData.id id
     * @apiSuccess (200) {String} transferData.orderNo 单号
     * @apiSuccess (200) {BigDecimal} transferData.num 库存件数
     *
     * @apiSuccess (200) {Object} warehouseStockData 库存对象
     * @apiSuccess (200) {String} warehouseStockData.id id
     * @apiSuccess (200) {BigDecimal} warehouseStockData.num 库存件数
     * @apiSuccess (200) {BigDecimal} warehouseStockData.tonNum 库存吨数
     * @apiSuccess (200) {DateTime} warehouseStockData.createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {Object} warehouseStockData.customerData 客户对象
     * @apiSuccess (200) {String} warehouseStockData.customerData.id id
     * @apiSuccess (200) {String} warehouseStockData.customerData.name 企业名称
     *
     * @apiSuccess (200) {Object} warehouseStockData.productData 物资分类对象
     * @apiSuccess (200) {String} warehouseStockData.productData.id 主键id
     * @apiSuccess (200) {String} warehouseStockData.productData.productName 物资分类名称
     *
     * @apiSuccess (200) {Object} warehouseStockData.warehouseSiteData 仓库实体
     * @apiSuccess (200) {String} warehouseStockData.warehouseSiteData.id 主键id
     * @apiSuccess (200) {String} warehouseStockData.warehouseSiteData.name 仓库名称
     *
     * @apiSuccess (200) {Object} warehouseStockData.warehouseAreaData 库区实体
     * @apiSuccess (200) {String} warehouseStockData.warehouseAreaData.id 主键id
     * @apiSuccess (200) {String} warehouseStockData.warehouseAreaData.name 库区名称
     *
     * @apiSuccess (200) {Object} warehouseStockData.warehouseData 库位实体
     * @apiSuccess (200) {String} warehouseStockData.warehouseData.id 主键id
     * @apiSuccess (200) {String} warehouseStockData.warehouseData.name 名称
     *
     * @apiSuccess (200) {Object} warehouseStockData.warehouseLevelData 库位实体
     * @apiSuccess (200) {String} warehouseStockData.warehouseLevelData.id 主键id
     * @apiSuccess (200) {String} warehouseStockData.warehouseLevelData.name 名称
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<TransferDetail>> findAll(TransferDetail entity,
                                                         @PageableDefault(value = 10, sort = {"createTime"},
                                                                 direction = Sort.Direction.DESC)
                                                                 Pageable pageable,
                                                         String transferId,
                                                         String warehouseStockId) {
        //过户单Id查询
        if (EmptyUtil.isNotEmpty(transferId)) {
            Transfer transfer = new Transfer();
            transfer.setId(transferId);
            entity.setTransfer(transfer);
        }
        //仓库库存Id查询
        if (EmptyUtil.isNotEmpty(warehouseStockId)) {
            WarehouseStock warehouseStock = new WarehouseStock();
            warehouseStock.setId(warehouseStockId);
            entity.setWarehouseStock(warehouseStock);
        }
        Example<TransferDetail> example = Example.of(entity);

        Page<TransferDetail> apply = this.transferDetailRepository.findAll(example, pageable);
        return new ResponseEntity<Page<TransferDetail>>(apply, HttpStatus.OK);
    }

    /**
     * @api {GET} /transferDetail/{id} 过户单单明细详情
     * @apiGroup transferDetail
     * @apiVersion 0.0.1
     * @apiDescription 过户单单明细详情
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /transferDetail/1
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {BigDecimal} num 件数
     * @apiSuccess (200) {BigDecimal} tonNum 吨数
     * @apiSuccess (200) {BigDecimal} fee 费用
     * @apiSuccess (200) {Object} transferData 过户单对象
     * @apiSuccess (200) {String} transferData.id id
     * @apiSuccess (200) {String} transferData.orderNo 单号
     * @apiSuccess (200) {BigDecimal} transferData.num 库存件数
     *
     * @apiSuccess (200) {Object} warehouseStockData 库存对象
     * @apiSuccess (200) {String} warehouseStockData.id id
     * @apiSuccess (200) {BigDecimal} warehouseStockData.num 库存件数
     * @apiSuccess (200) {BigDecimal} warehouseStockData.tonNum 库存吨数
     * @apiSuccess (200) {DateTime} warehouseStockData.createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {Object} warehouseStockData.customerData 客户对象
     * @apiSuccess (200) {String} warehouseStockData.customerData.id id
     * @apiSuccess (200) {String} warehouseStockData.customerData.name 企业名称
     *
     * @apiSuccess (200) {Object} warehouseStockData.productData 物资分类对象
     * @apiSuccess (200) {String} warehouseStockData.productData.id 主键id
     * @apiSuccess (200) {String} warehouseStockData.productData.productName 物资分类名称
     *
     * @apiSuccess (200) {Object} warehouseStockData.warehouseSiteData 仓库实体
     * @apiSuccess (200) {String} warehouseStockData.warehouseSiteData.id 主键id
     * @apiSuccess (200) {String} warehouseStockData.warehouseSiteData.name 仓库名称
     *
     * @apiSuccess (200) {Object} warehouseStockData.warehouseAreaData 库区实体
     * @apiSuccess (200) {String} warehouseStockData.warehouseAreaData.id 主键id
     * @apiSuccess (200) {String} warehouseStockData.warehouseAreaData.name 库区名称
     *
     * @apiSuccess (200) {Object} warehouseStockData.warehouseData 库位实体
     * @apiSuccess (200) {String} warehouseStockData.warehouseData.id 主键id
     * @apiSuccess (200) {String} warehouseStockData.warehouseData.name 名称
     *
     * @apiSuccess (200) {Object} warehouseStockData.warehouseLevelData 库位实体
     * @apiSuccess (200) {String} warehouseStockData.warehouseLevelData.id 主键id
     * @apiSuccess (200) {String} warehouseStockData.warehouseLevelData.name 名称
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        TransferDetail entity = this.transferDetailRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}

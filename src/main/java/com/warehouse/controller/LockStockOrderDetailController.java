package com.warehouse.controller;

import com.warehouse.model.LockStockOrder;
import com.warehouse.model.LockStockOrderDetail;
import com.warehouse.model.Product;
import com.warehouse.service.repository.LockStockOrderDetailRepository;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 锁库单明细
 */
@RestController
@RequestMapping("/lockStockOrderDetail")
public class LockStockOrderDetailController {
    @Autowired
    private LockStockOrderDetailRepository lockStockOrderDetailRepository;

    /**
     * @api {GET} /lockStockOrderDetail 锁库单明细列表
     * @apiGroup lockStockOrderDetail
     * @apiVersion 0.0.1
     * @apiDescription 锁库单明细列表
     * @apiParam {String} [productId] 物资Id
     * @apiParam {String} [lockStockOrderId] 锁库单Id
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /lockStockOrderDetail?opType=40010&sort=id,asc
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} vehicleNo 车号
     * @apiSuccess (200) {BigDecimal} num 件数
     * @apiSuccess (200) {BigDecimal} tonNum 吨数
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
     *
     *
     * @apiSuccess (200) {Object} lockStockOrderData 锁库单对象
     * @apiSuccess (200) {String} lockStockOrderData.id id
     * @apiSuccess (200) {String} lockStockOrderData.orderNo 单号
     *
     * @apiSuccess {Date} createTime 创建时间
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<LockStockOrderDetail>> findAll(LockStockOrderDetail entity,
                                                    @PageableDefault(value = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable,
                                                     String lockStockOrderId,
                                                     String productId) {
        //锁库单Id查询
        if (EmptyUtil.isNotEmpty(lockStockOrderId)) {
            LockStockOrder lockStockOrder = new LockStockOrder();
            lockStockOrder.setId(lockStockOrderId);
            entity.setLockStockOrder(lockStockOrder);
        }
        //物资Id查询
        if (EmptyUtil.isNotEmpty(productId)) {
            Product product = new Product();
            product.setId(productId);
            entity.setProduct(product);
        }
        Example<LockStockOrderDetail> example = Example.of(entity);

        Page<LockStockOrderDetail> apply = this.lockStockOrderDetailRepository.findAll(example, pageable);
        return new ResponseEntity<Page<LockStockOrderDetail>>(apply, HttpStatus.OK);
    }
}

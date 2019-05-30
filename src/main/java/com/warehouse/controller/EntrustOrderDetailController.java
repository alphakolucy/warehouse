package com.warehouse.controller;

import com.warehouse.model.*;
import com.warehouse.service.repository.CustomerRepository;
import com.warehouse.service.repository.EntrustOrderDetailRepository;
import com.warehouse.service.repository.ProductRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.EntrustOrderState;
import com.warehouse.util.enums.EntrustOrderType;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.tools.EmptyUtil;
import com.warehouse.util.tools.ObjectCopyUtil;
import com.warehouse.util.tools.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 委托单明细
 */
@RestController
@RequestMapping("/entrustOrderDetail")
public class EntrustOrderDetailController {
    @Autowired
    private EntrustOrderDetailRepository entrustOrderDetailRepository;

    /**
     * @api {GET} /entrustOrderDetail 委托单明细列表
     * @apiGroup entrustOrderDetail
     * @apiVersion 0.0.1
     * @apiDescription 委托单明细列表
     * @apiParam {String} [productId] 物资Id
     * @apiParam {String} [entrustOrderId] 委托单Id
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /entrustOrderDetail?opType=40010&sort=id,asc
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
     * @apiSuccess (200) {Object} entrustOrderData 委托单对象
     * @apiSuccess (200) {String} entrustOrderData.id id
     * @apiSuccess (200) {String} entrustOrderData.orderNo 委托单单号
     *
     * @apiSuccess {Date} createTime 创建时间
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<EntrustOrderDetail>> findAll(EntrustOrderDetail entity,
                                                    @PageableDefault(value = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable,
                                                     String entrustOrderId,
                                                     String productId) {
        //委托单Id查询
        if (EmptyUtil.isNotEmpty(entrustOrderId)) {
            EntrustOrder entrustOrder = new EntrustOrder();
            entrustOrder.setId(entrustOrderId);
            entity.setEntrustOrder(entrustOrder);
        }
        //物资Id查询
        if (EmptyUtil.isNotEmpty(productId)) {
            Product product = new Product();
            product.setId(productId);
            entity.setProduct(product);
        }
        Example<EntrustOrderDetail> example = Example.of(entity);

        Page<EntrustOrderDetail> apply = this.entrustOrderDetailRepository.findAll(example, pageable);
        return new ResponseEntity<Page<EntrustOrderDetail>>(apply, HttpStatus.OK);
    } 
}

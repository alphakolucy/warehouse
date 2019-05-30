package com.warehouse.controller;

import com.warehouse.model.EntrustOrder;
import com.warehouse.model.EntrustOrderLog;
import com.warehouse.model.Product;
import com.warehouse.service.repository.EntrustOrderLogRepository;
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
 * 委托单日志
 */
@RestController
@RequestMapping("/entrustOrderLog")
public class EntrustOrderLogController {
    @Autowired
    private EntrustOrderLogRepository entrustOrderLogRepository;

    /**
     * @api {GET} /entrustOrderLog 委托单日志列表
     * @apiGroup entrustOrderLog
     * @apiVersion 0.0.1
     * @apiDescription 委托单日志列表
     * @apiParam {String} [entrustOrderId] 委托单Id
     * @apiParam {String} [orderState] 订单状态
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /entrustOrderLog?opType=40010&sort=id,asc
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {Integer} orderState 订单状态
     * @apiSuccess (200) {String} orderStateTxt 订单状态Txt
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人ID
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {Object} entrustOrderData 委托单对象
     * @apiSuccess (200) {String} entrustOrderData.id id
     * @apiSuccess (200) {String} entrustOrderData.orderNo 委托单单号
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<EntrustOrderLog>> findAll(EntrustOrderLog entity,
                                                    @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable,
                                                     String entrustOrderId) {
        //委托单Id查询
        if (EmptyUtil.isNotEmpty(entrustOrderId)) {
            EntrustOrder entrustOrder = new EntrustOrder();
            entrustOrder.setId(entrustOrderId);
            entity.setEntrustOrder(entrustOrder);
        }

        Example<EntrustOrderLog> example = Example.of(entity);

        Page<EntrustOrderLog> apply = this.entrustOrderLogRepository.findAll(example, pageable);
        return new ResponseEntity<Page<EntrustOrderLog>>(apply, HttpStatus.OK);
    }
    
}

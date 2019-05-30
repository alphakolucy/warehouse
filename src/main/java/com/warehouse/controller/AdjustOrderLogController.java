package com.warehouse.controller;

import com.warehouse.model.AdjustOrder;
import com.warehouse.model.AdjustOrderLog;
import com.warehouse.model.WarehouseStock;
import com.warehouse.model.WarehouseStockLog;
import com.warehouse.service.repository.AdjustOrderLogRepository;
import com.warehouse.service.repository.WarehouseStockLogRepository;
import com.warehouse.util.tools.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存调整日志
 */
@RestController
@RequestMapping("/adjustOrderLog")
public class AdjustOrderLogController {

    @Autowired
    private AdjustOrderLogRepository adjustOrderLogRepository;

    /**
     * @api {GET} /adjustOrderLog 库存调整日志列表
     * @apiGroup adjustOrderLog
     * @apiVersion 0.0.1
     * @apiDescription 库存调整日志列表
     * @apiParam {Integer} [orderState] 订单状态(支持模糊查询)
     * - TAKE_OVER(20010, "新建"),
     * - CARRY_GOOD(20020, "确认"),
     * - REMOVE_STOCK(40040, "作废")，
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=createTime,asc] 排序
     * - 格式： sort=createTime,desc 表示在按createTime由高到低排列
     * - 格式： sort=createTime,asc 表示在按createTime由低到高排列
     * @apiParamExample 请求明文：
     * /adjustOrderLog
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人
     * @apiSuccess (200) {String} orderStateTxt 订单状态名称
     * @apiSuccess (200) {Integer} orderState 订单状态
     * - TAKE_OVER(20010, "新建"),
     * - CARRY_GOOD(20020, "确认"),
     * - REMOVE_STOCK(40040, "作废")，
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {Object} adjustOrder 调整实体
     * @apiSuccess (200) {String} adjustOrder.id 库存调整id
     * @apiSuccess (200) {String} adjustOrder.orderNo 单号
     * @apiSuccess (200) {BigDecimal} adjustOrder.tonNum 操作吨数
     * @apiSuccess (200) {BigDecimal} adjustOrder.num 操作件数
     * @apiSuccess (200) {Integer} adjustOrder.orderState 订单状态
     * - TAKE_OVER(20010, "新建"),
     * - CARRY_GOOD(20020, "确认"),
     * - REMOVE_STOCK(40040, "作废")，
     * @apiSuccess (200) {String} adjustOrder.remark 备注
     * @apiSuccess (200) {DateTime} adjustOrder.createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<AdjustOrderLog>> findAll(AdjustOrderLog entity,
                                                        String adjustOrderId,
                                                        @PageableDefault(value = 10, sort = {"createTime"},
                                                                direction = Sort.Direction.DESC)
                                                                Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //操作单号模糊方式查
                .withMatcher("orderState", ExampleMatcher.GenericPropertyMatchers.contains());

        // 根据库存调整adjustOrderId查询
        if (EmptyUtil.isNotEmpty(adjustOrderId)) {
            AdjustOrder adjustOrder = new AdjustOrder();
            adjustOrder.setId(adjustOrderId);
            entity.setAdjustOrder(adjustOrder);
        }


        Example<AdjustOrderLog> example = Example.of(entity, matcher);
        Page<AdjustOrderLog> apply = this.adjustOrderLogRepository.findAll(example, pageable);
        return new ResponseEntity<Page<AdjustOrderLog>>(apply, HttpStatus.OK);
    }
}

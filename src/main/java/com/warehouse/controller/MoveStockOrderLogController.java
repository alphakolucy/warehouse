package com.warehouse.controller;

import com.warehouse.model.MoveStockOrder;
import com.warehouse.model.MoveStockOrderLog;
import com.warehouse.service.repository.MoveStockOrderLogRepository;
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
 * 移库单日志
 */
@RestController
@RequestMapping("/moveStockOrderLog")
public class MoveStockOrderLogController {

    @Autowired
    private MoveStockOrderLogRepository moveStockOrderLogRepository;

    /**
     * @api {GET} /moveStockOrderLog 移库单日志列表
     * @apiGroup moveStockOrderLog
     * @apiVersion 0.0.1
     * @apiDescription 移库单日志列表
     * @apiParam {String} [orderState] 移库单状态
     * @apiParam {String} [moveStockOrderId] 移库id
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=createTime,asc] 排序
     * - 格式： sort=createTime,desc 表示在按createTime由高到低排列
     * - 格式： sort=createTime,asc 表示在按createTime由低到高排列
     * @apiParamExample 请求明文：
     * /moveStockOrderLog
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
     * @apiSuccess (200) {Object} moveStockOrder 库存移库
     * @apiSuccess (200) {String} moveStockOrder.id 库存移库id
     * @apiSuccess (200) {String} moveStockOrder.orderNo 单号
     * @apiSuccess (200) {BigDecimal} moveStockOrder.tonNum 操作吨数
     * @apiSuccess (200) {BigDecimal} moveStockOrder.num 操作件数
     * @apiSuccess (200) {String} moveStockOrder.remark 备注
     * @apiSuccess (200) {DateTime} moveStockOrder.createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {String} moveStockOrder.createMan 操作人
     * @apiSuccess (200) {String} moveStockOrder.createManId 操作人Id
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<MoveStockOrderLog>> findAll(MoveStockOrderLog entity,
                                                           String moveStockOrderId,
                                                           @PageableDefault(value = 10, sort = {"createTime"},
                                                                   direction = Sort.Direction.DESC)
                                                                   Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //操作单号模糊方式查
                .withMatcher("orderState", ExampleMatcher.GenericPropertyMatchers.contains());

        // 根据库存移库moveStockOrderId查询
        if (EmptyUtil.isNotEmpty(moveStockOrderId)) {
            MoveStockOrder moveStockOrder = new MoveStockOrder();
            moveStockOrder.setId(moveStockOrderId);
            entity.setMoveStockOrder(moveStockOrder);
        }

        Example<MoveStockOrderLog> example = Example.of(entity, matcher);
        Page<MoveStockOrderLog> apply = this.moveStockOrderLogRepository.findAll(example, pageable);
        return new ResponseEntity<Page<MoveStockOrderLog>>(apply, HttpStatus.OK);
    }
}

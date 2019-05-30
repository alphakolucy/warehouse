package com.warehouse.controller;

import com.warehouse.model.LockStockOrder;
import com.warehouse.model.LockStockOrderLog;
import com.warehouse.service.repository.LockStockOrderLogRepository;
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
 * 锁库日志
 */
@RestController
@RequestMapping("/lockStockOrderLog")
public class LockStockOrderLogController {

    @Autowired
    private LockStockOrderLogRepository lockStockOrderLogRepository;

    /**
     * @api {GET} /lockStockOrderLog 锁库日志列表
     * @apiGroup lockStockOrderLog
     * @apiVersion 0.0.1
     * @apiDescription 锁库日志列表
     * @apiParam {String} [orderState] 锁库状态
     * @apiParam {String} [lockStockOrderId] 锁库id
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=createTime,asc] 排序
     * - 格式： sort=createTime,desc 表示在按createTime由高到低排列
     * - 格式： sort=createTime,asc 表示在按createTime由低到高排列
     * @apiParamExample 请求明文：
     * /lockStockOrderLog
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
     * @apiSuccess (200) {Object} lockStockOrderData 锁库对象
     * @apiSuccess (200) {String} lockStockOrderData.id 锁库id
     * @apiSuccess (200) {String} lockStockOrderData.orderNo 单号
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<LockStockOrderLog>> findAll(LockStockOrderLog entity,
                                                           String lockStockOrderId,
                                                           @PageableDefault(value = 10, sort = {"createTime"},
                                                                   direction = Sort.Direction.DESC)
                                                                   Pageable pageable) {

        // 根据锁库lockStockOrderId查询
        if (EmptyUtil.isNotEmpty(lockStockOrderId)) {
            LockStockOrder lockStockOrder = new LockStockOrder();
            lockStockOrder.setId(lockStockOrderId);
            entity.setLockStockOrder(lockStockOrder);
        }

        Example<LockStockOrderLog> example = Example.of(entity);
        Page<LockStockOrderLog> apply = this.lockStockOrderLogRepository.findAll(example, pageable);
        return new ResponseEntity<Page<LockStockOrderLog>>(apply, HttpStatus.OK);
    }
}

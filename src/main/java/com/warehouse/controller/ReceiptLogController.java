package com.warehouse.controller;

import com.warehouse.model.Receipt;
import com.warehouse.model.Transfer;
import com.warehouse.model.ReceiptLog;
import com.warehouse.service.repository.ReceiptLogRepository;
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
 * 收发货日志
 */
@RestController
@RequestMapping("/receiptLog")
public class ReceiptLogController {
    @Autowired
    private ReceiptLogRepository receiptLogRepository;

    /**
     * @api {GET} /receiptLog 收发货日志列表
     * @apiGroup receiptLog
     * @apiVersion 0.0.1
     * @apiDescription 收发货日志列表
     * @apiParam {String} [receiptId] 收发货Id
     * @apiParam {String} [orderState] 订单状态
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /receiptLog?opType=40010&sort=id,asc
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {Integer} orderState 订单状态
     * @apiSuccess (200) {String} orderStateTxt 订单状态Txt
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人ID
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {Object} receiptData 收发货对象
     * @apiSuccess (200) {String} receiptData.id id
     * @apiSuccess (200) {String} receiptData.orderNo 收发货单号
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<ReceiptLog>> findAll(ReceiptLog entity,
                                                    @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable,
                                                     String receiptId) {
        //收发货Id查询
        if (EmptyUtil.isNotEmpty(receiptId)) {
            Receipt receipt = new Receipt();
            receipt.setId(receiptId);
            entity.setReceipt(receipt);
        }

        Example<ReceiptLog> example = Example.of(entity);

        Page<ReceiptLog> apply = this.receiptLogRepository.findAll(example, pageable);
        return new ResponseEntity<Page<ReceiptLog>>(apply, HttpStatus.OK);
    }
    
}

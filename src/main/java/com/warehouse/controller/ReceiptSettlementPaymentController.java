package com.warehouse.controller;

import com.warehouse.model.ReceiptSettlement;
import com.warehouse.model.ReceiptSettlementPayment;
import com.warehouse.service.repository.ReceiptSettlementPaymentRepository;
import com.warehouse.service.repository.ReceiptSettlementRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.tools.EmptyUtil;
import com.warehouse.util.tools.ObjectCopyUtil;
import com.warehouse.util.tools.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 收发货结算支付单
 */
@RestController
@RequestMapping("/receiptSettlementPayment")
public class ReceiptSettlementPaymentController {
    @Autowired
    private ReceiptSettlementPaymentRepository receiptSettlementPaymentRepository;
    @Autowired
    private ReceiptSettlementRepository receiptSettlementRepository;

    /**
     * @api {GET} /receiptSettlementPayment 收发货结算支付单列表
     * @apiGroup receiptSettlementPayment
     * @apiVersion 0.0.1
     * @apiDescription 收发货结算支付单列表
     * @apiParam {String} [orderNo] 支付单号(支持模糊查询)
     * @apiParam {String} [receiptSettlementId] 收发货结算单Id
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /receiptSettlementPayment?contract_num=1111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo  结算单号
     * @apiSuccess (200) {String} bankVoucherNo 支付银行凭证号
     * @apiSuccess (200) {String} bankName 支付银行
     * @apiSuccess (200) {DateTime} draweeTime 支付时间
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     *
     * @apiSuccess (200) {Object} transferSettlementData 收发货结算单对象
     * @apiSuccess (200) {String} transferSettlementData.id id
     * @apiSuccess (200) {String} transferSettlementData.orderNo 结算单号
     * @apiSuccess (200) {String} transferSettlementData.amount 结算费用
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<ReceiptSettlementPayment>> findAll(ReceiptSettlementPayment entity,
                                                                   @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.DESC)
                                                                           Pageable pageable,
                                                                   String receiptSettlementId) {
        //按收发货结算单Id查
        if (EmptyUtil.isNotEmpty(receiptSettlementId)) {
            ReceiptSettlement receiptSettlement = new ReceiptSettlement();
            receiptSettlement.setId(receiptSettlementId);
            entity.setReceiptSettlement(receiptSettlement);
        }

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //模糊方式查
                .withMatcher("orderNo", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<ReceiptSettlementPayment> example = Example.of(entity, matcher);

        Page<ReceiptSettlementPayment> apply = this.receiptSettlementPaymentRepository.findAll(example, pageable);
        return new ResponseEntity<Page<ReceiptSettlementPayment>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /receiptSettlementPayment/{id} 单个收发货结算支付单
     * @apiGroup receiptSettlementPayment
     * @apiVersion 0.0.1
     * @apiDescription 单个收发货结算支付单
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /receiptSettlementPayment/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo  结算单号
     * @apiSuccess (200) {String} bankVoucherNo 支付银行凭证号
     * @apiSuccess (200) {String} bankName 支付银行
     * @apiSuccess (200) {DateTime} draweeTime 支付时间
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     *
     * @apiSuccess (200) {Object} transferSettlementData 收发货结算单对象
     * @apiSuccess (200) {String} transferSettlementData.id id
     * @apiSuccess (200) {String} transferSettlementData.orderNo 结算单号
     * @apiSuccess (200) {String} transferSettlementData.amount 结算费用
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        ReceiptSettlementPayment entity = this.receiptSettlementPaymentRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ReceiptSettlementPayment>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /receiptSettlementPayment 收发货结算支付单添加
     * @apiGroup receiptSettlementPayment
     * @apiVersion 0.0.1
     * @apiDescription 收发货结算支付单添加
     * @apiParam {String} receiptSettlementId 收发货结算单id
     * @apiParam {String} bankVoucherNo 支付银行凭证号
     * @apiParam {String} bankName 支付银行
     * @apiParam {DateTime} draweeTime 支付时间
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /receiptSettlementPayment
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(ReceiptSettlementPayment entity,
                                  String receiptSettlementId) {

        HttpStatusContent status = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNo = "ZF" +
                format.format((new Date()).getTime()) +
                RandomUtil.toFixdLengthString((int) ((Math.random() * 9 + 1) * 10000), 5);
        entity.setOrderNo(orderNo);
        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (EmptyUtil.isEmpty(receiptSettlementId)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少收发货结算单参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ReceiptSettlement receiptSettlement = this.receiptSettlementRepository.findOne(receiptSettlementId);
        if (receiptSettlement == null) {
            status = new HttpStatusContent(OutputState.FAIL, "收发货单不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (receiptSettlement.getIsPay() != null
                && receiptSettlement.getIsPay()) {
            status = new HttpStatusContent(OutputState.FAIL,
                    "收发货单:" + receiptSettlement.getOrderNo() + "已经支付过了！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        receiptSettlement.setIsPay(true);

        //设置关联，添加收发货结算支付单的同时修改收发货结算单信息
        entity.setReceiptSettlement(receiptSettlement);

        entity = this.receiptSettlementPaymentRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {PUT} /receiptSettlementPayment/{id} 收发货结算支付单修改
     * @apiGroup receiptSettlementPayment
     * @apiVersion 0.0.1
     * @apiDescription 收发货结算支付单修改
     * @apiParam {String} id 主键id
     * @apiParam {String} receiptSettlementId 收发货结算单id
     * @apiParam {String} bankVoucherNo 支付银行凭证号
     * @apiParam {String} bankName 支付银行
     * @apiParam {DateTime} draweeTime 支付时间
     * @apiParamExample 请求明文：
     * /receiptSettlementPayment/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 ReceiptSettlementPayment entity,
                                 String receiptSettlementId) {
        HttpStatusContent status = null;
        entity.setOrderNo(null);//单号不能修改

        ReceiptSettlementPayment originEntity = this.receiptSettlementPaymentRepository.findOne(id);
        if (originEntity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (EmptyUtil.isNotEmpty(receiptSettlementId)) {
            ReceiptSettlement receiptSettlement = this.receiptSettlementRepository.findOne(receiptSettlementId);
            if (receiptSettlement == null) {
                status = new HttpStatusContent(OutputState.FAIL, "收发货单不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (receiptSettlement.getIsPay() != null
                    && receiptSettlement.getIsPay()) {
                status = new HttpStatusContent(OutputState.FAIL,
                        "收发货单:" + receiptSettlement.getOrderNo() + "已经支付过了！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            receiptSettlement.setIsPay(true);

            //设置关联，添加收发货结算支付单的同时修改收发货结算单信息
            originEntity.setReceiptSettlement(receiptSettlement);
        }

        ObjectCopyUtil<ReceiptSettlementPayment> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, originEntity);

        originEntity = this.receiptSettlementPaymentRepository.save(originEntity);
        if (originEntity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }
}

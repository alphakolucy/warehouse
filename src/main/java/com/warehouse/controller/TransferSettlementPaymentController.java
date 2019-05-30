package com.warehouse.controller;

import com.warehouse.model.TransferSettlement;
import com.warehouse.model.TransferSettlementPayment;
import com.warehouse.service.repository.TransferSettlementPaymentRepository;
import com.warehouse.service.repository.TransferSettlementRepository;
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
 * 过户结算支付单
 */
@RestController
@RequestMapping("/transferSettlementPayment")
public class TransferSettlementPaymentController {
    @Autowired
    private TransferSettlementPaymentRepository transferSettlementPaymentRepository;
    @Autowired
    private TransferSettlementRepository transferSettlementRepository;

    /**
     * @api {GET} /transferSettlementPayment 过户结算支付单列表
     * @apiGroup transferSettlementPayment
     * @apiVersion 0.0.1
     * @apiDescription 过户结算支付单列表
     * @apiParam {String} [orderNo] 支付单号(支持模糊查询)
     * @apiParam {String} [transferSettlementId] 过户结算单Id
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /transferSettlementPayment?contract_num=1111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo  结算单号
     * @apiSuccess (200) {String} bankVoucherNo 支付银行凭证号
     * @apiSuccess (200) {String} bankName 支付银行
     * @apiSuccess (200) {DateTime} draweeTime 支付时间
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     *
     * @apiSuccess (200) {Object} receiptSettlementData 过户结算单对象
     * @apiSuccess (200) {String} receiptSettlementData.id id
     * @apiSuccess (200) {String} receiptSettlementData.orderNo 结算单号
     * @apiSuccess (200) {String} receiptSettlementData.amount 结算费用
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<TransferSettlementPayment>> findAll(TransferSettlementPayment entity,
                                                                   @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.DESC)
                                                                           Pageable pageable,
                                                                   String transferSettlementId) {
        //按过户结算单Id查
        if (EmptyUtil.isNotEmpty(transferSettlementId)) {
            TransferSettlement transferSettlement = new TransferSettlement();
            transferSettlement.setId(transferSettlementId);
            entity.setTransferSettlement(transferSettlement);
        }

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //模糊方式查
                .withMatcher("orderNo", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<TransferSettlementPayment> example = Example.of(entity, matcher);

        Page<TransferSettlementPayment> apply = this.transferSettlementPaymentRepository.findAll(example, pageable);
        return new ResponseEntity<Page<TransferSettlementPayment>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /transferSettlementPayment/{id} 单个过户结算支付单
     * @apiGroup transferSettlementPayment
     * @apiVersion 0.0.1
     * @apiDescription 单个过户结算支付单
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /transferSettlementPayment/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo  结算单号
     * @apiSuccess (200) {String} bankVoucherNo 支付银行凭证号
     * @apiSuccess (200) {String} bankName 支付银行
     * @apiSuccess (200) {DateTime} draweeTime 支付时间
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     *
     * @apiSuccess (200) {Object} receiptSettlementData 过户结算单对象
     * @apiSuccess (200) {String} receiptSettlementData.id id
     * @apiSuccess (200) {String} receiptSettlementData.orderNo 结算单号
     * @apiSuccess (200) {String} receiptSettlementData.amount 结算费用
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        TransferSettlementPayment entity = this.transferSettlementPaymentRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TransferSettlementPayment>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /transferSettlementPayment 过户结算支付单添加
     * @apiGroup transferSettlementPayment
     * @apiVersion 0.0.1
     * @apiDescription 过户结算支付单添加
     * @apiParam {String} transferSettlementId 过户结算单id
     * @apiParam {String} bankVoucherNo 支付银行凭证号
     * @apiParam {String} bankName 支付银行
     * @apiParam {DateTime} draweeTime 支付时间
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /transferSettlementPayment
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(TransferSettlementPayment entity,
                                  String transferSettlementId) {

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

        if (EmptyUtil.isEmpty(transferSettlementId)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少过户结算单参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        TransferSettlement transferSettlement = this.transferSettlementRepository.findOne(transferSettlementId);
        if (transferSettlement == null) {
            status = new HttpStatusContent(OutputState.FAIL, "过户单不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (transferSettlement.getIsPay() != null
                && transferSettlement.getIsPay()) {
            status = new HttpStatusContent(OutputState.FAIL,
                    "过户单:" + transferSettlement.getOrderNo() + "已经支付过了！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        transferSettlement.setIsPay(true);

        //设置关联，添加过户结算支付单的同时修改过户结算单信息
        entity.setTransferSettlement(transferSettlement);

        entity = this.transferSettlementPaymentRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {PUT} /transferSettlementPayment/{id} 过户结算支付单修改
     * @apiGroup transferSettlementPayment
     * @apiVersion 0.0.1
     * @apiDescription 过户结算支付单修改
     * @apiParam {String} id 主键id
     * @apiParam {String} transferSettlementId 过户结算单id
     * @apiParam {String} bankVoucherNo 支付银行凭证号
     * @apiParam {String} bankName 支付银行
     * @apiParam {DateTime} draweeTime 支付时间
     * @apiParamExample 请求明文：
     * /transferSettlementPayment/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 TransferSettlementPayment entity,
                                 String transferSettlementId) {
        HttpStatusContent status = null;
        entity.setOrderNo(null);//单号不能修改

        TransferSettlementPayment originEntity = this.transferSettlementPaymentRepository.findOne(id);
        if (originEntity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (EmptyUtil.isNotEmpty(transferSettlementId)) {
            TransferSettlement transferSettlement = this.transferSettlementRepository.findOne(transferSettlementId);
            if (transferSettlement == null) {
                status = new HttpStatusContent(OutputState.FAIL, "过户单不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (transferSettlement.getIsPay() != null
                    && transferSettlement.getIsPay()) {
                status = new HttpStatusContent(OutputState.FAIL,
                        "过户单:" + transferSettlement.getOrderNo() + "已经支付过了！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            transferSettlement.setIsPay(true);

            //设置关联，添加过户结算支付单的同时修改过户结算单信息
            originEntity.setTransferSettlement(transferSettlement);
        }

        ObjectCopyUtil<TransferSettlementPayment> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, originEntity);

        originEntity = this.transferSettlementPaymentRepository.save(originEntity);
        if (originEntity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }
}

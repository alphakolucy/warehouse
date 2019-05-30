package com.warehouse.controller;

import com.warehouse.model.Receipt;
import com.warehouse.model.ReceiptSettlement;
import com.warehouse.service.repository.ReceiptRepository;
import com.warehouse.service.repository.ReceiptSettlementRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.tools.EmptyUtil;
import com.warehouse.util.tools.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 收发货结算单
 */
@RestController
@RequestMapping("/receiptSettlement")
public class ReceiptSettlementController {
    @Autowired
    private ReceiptSettlementRepository receiptSettlementRepository;
    @Autowired
    private ReceiptRepository receiptRepository;

    /**
     * @api {GET} /receiptSettlement 收发货结算单列表
     * @apiGroup receiptSettlement
     * @apiVersion 0.0.1
     * @apiDescription 收发货结算单列表
     * @apiParam {String} [orderNo] 结算单单号(支持模糊查询)
     * @apiParam {Boolean=true,false} [isPay] 是否生成过支付单
     * - true：已生成
     * - false:未生成
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /receiptSettlement?contract_num=1111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo  结算单号
     * @apiSuccess (200) {Boolean=true,false} isPay 是否生成过支付单
     * - true：已生成
     * - false:未生成
     * @apiSuccess (200) {BigDecimal} amount 结算费用
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<ReceiptSettlement>> findAll(ReceiptSettlement entity,
                                                    @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //模糊方式查
                .withMatcher("orderNo", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<ReceiptSettlement> example = Example.of(entity,matcher);

        Page<ReceiptSettlement> apply = this.receiptSettlementRepository.findAll(example, pageable);
        return new ResponseEntity<Page<ReceiptSettlement>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /receiptSettlement/{id} 单个收发货结算单
     * @apiGroup receiptSettlement
     * @apiVersion 0.0.1
     * @apiDescription 单个收发货结算单
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /receiptSettlement/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo  结算单号
     * @apiSuccess (200) {Boolean=true,false} isPay 是否生成过支付单
     * - true：已生成
     * - false:未生成
     * @apiSuccess (200) {BigDecimal} amount 结算费用
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        ReceiptSettlement entity = this.receiptSettlementRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ReceiptSettlement>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /receiptSettlement 收发货结算单添加
     * @apiGroup receiptSettlement
     * @apiVersion 0.0.1
     * @apiDescription 收发货结算单添加
     * @apiParam {String} receiptIds 收发货单id列表
     * - 格式："1,2,3,4"
     * @apiParam {BigDecimal} amount 结算费用
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /receiptSettlement
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(ReceiptSettlement entity,
                                  String receiptIds) {

        HttpStatusContent status = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNo = "JS" +
                format.format((new Date()).getTime())+
                RandomUtil.toFixdLengthString((int) ((Math.random() * 9 + 1) * 10000), 6);
        entity.setOrderNo(orderNo);
        entity.setIsPay(false);//默认未支付
        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(EmptyUtil.isEmpty(receiptIds)
                || receiptIds.split(",").length<=0) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少收发货单参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Set<Receipt> receiptSet = new HashSet<>();
        String[] rtIds = receiptIds.split(",");
        for(int i=0;i<rtIds.length;i++){
            Receipt receipt = this.receiptRepository.findOne(rtIds[i]);
            if(receipt == null) {
                status = new HttpStatusContent(OutputState.FAIL, "收发货单不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(receipt.getIsSettlement()!=null
                    && receipt.getIsSettlement()) {
                status = new HttpStatusContent(OutputState.FAIL,
                        "收发货单:"+receipt.getOrderNo() +"已经结算过了！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            receipt.setIsSettlement(true);
            receipt.setReceiptSettlement(entity);
            receiptSet.add(receipt);
        }
        //设置关联，添加结算单的同时修改收发货单信息
        entity.setReceiptSet(receiptSet);

        entity = this.receiptSettlementRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {PUT} /receiptSettlement/{id} 收发货结算单修改
     * @apiGroup receiptSettlement
     * @apiVersion 0.0.1
     * @apiDescription 收发货结算单修改
     * @apiParam {String} id 主键id
     * @apiParam {String} receiptIds 收发货单id列表
     * - 格式："1,2,3,4"
     * @apiParam {BigDecimal} amount 结算费用
     * @apiParamExample 请求明文：
     * /receiptSettlement/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 BigDecimal amount,
                                 String receiptIds) {
        HttpStatusContent status = null;

        ReceiptSettlement originEntity = this.receiptSettlementRepository.findOne(id);
        if (originEntity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if(amount != null){
            originEntity.setAmount(amount);
        }

        if(EmptyUtil.isNotEmpty(receiptIds)
                && receiptIds.split(",").length<=0){
            Set<Receipt> receiptSet = new HashSet<>();
            String[] rtIds = receiptIds.split(",");
            for(int i=0;i<rtIds.length;i++){
                Receipt receipt = this.receiptRepository.findOne(rtIds[i]);
                if(receipt == null) {
                    status = new HttpStatusContent(OutputState.FAIL, "收发货单不存在！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if(receipt.getIsSettlement()!=null
                        && receipt.getIsSettlement()) {
                    status = new HttpStatusContent(OutputState.FAIL,
                            "收发货单:"+receipt.getOrderNo() +"已经结算过了！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                receipt.setIsSettlement(true);
                receipt.setReceiptSettlement(originEntity);
                receiptSet.add(receipt);
            }
            //设置关联，添加结算单的同时修改收发货单信息
            originEntity.setReceiptSet(receiptSet);
        }

        originEntity = this.receiptSettlementRepository.save(originEntity);
        if (originEntity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }
}

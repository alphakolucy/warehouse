package com.warehouse.controller;

import com.warehouse.model.CostSettins;
import com.warehouse.model.Customer;
import com.warehouse.model.ProductCates;
import com.warehouse.service.repository.CostSettinsRepository;
import com.warehouse.service.repository.CustomerRepository;
import com.warehouse.service.repository.ProductCatesRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.CostSettinsChargeUnit;
import com.warehouse.util.enums.CostSettinsFeePurpose;
import com.warehouse.util.enums.CostSettinsOpType;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.tools.EmptyUtil;
import com.warehouse.util.tools.ObjectCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 计费方式
 */
@RestController
@RequestMapping("/costSettins")
public class CostSettinsController {
    @Autowired
    private CostSettinsRepository costSettinsRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductCatesRepository productCatesRepository;

    /**
     * @api {GET} /costSettins 计费方式列表
     * @apiGroup costSettins
     * @apiVersion 0.0.1
     * @apiDescription 计费方式列表
     * @apiParam {Integer} [opType] 收费类型
     * - IN_STOCK(40010, "入库"),
     * - OUT_STOCK(40020, "出库"),
     * - PROCESS(40030, "加工"),
     * - REMOVE_STOCK(40040, "移库"),
     * - BUTT_JOINT(40050, "对接"),
     * - TRANSFER_NAME(40060, "过户"),
     * - TRAY(40070, "托盘"),
     * - STORAGE(40080, "仓储");
     * @apiParam {String} [customerId] 客户Id
     * @apiParam {String} [cateId] 物资分类Id
     * @apiParam {Integer} [feePurpose] 费用用途
     * - THEORY(13010, "理重"),
     * - FACT(13020, "实重");
     * @apiParam {Boolean} [isUse] 是否使用
     * - true:使用
     * - false:停用;
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /costSettins?contract_num=1111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {Integer} opType 收费类型
     * @apiSuccess (200) {String} opTypeTxt 收费类型Txt
     * @apiSuccess (200) {BigDecimal} fee 费用
     * @apiSuccess (200) {DateTime} startTime 开始时间
     * @apiSuccess (200) {DateTime} endTime 结束时间
     * @apiSuccess (200) {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     * @apiSuccess (200) {Integer} chargeUnit 计费单位
     * @apiSuccess (200) {String} chargeUnit 计费单位Txt
     * @apiSuccess (200) {Integer} feePurpose 费用用途
     * @apiSuccess (200) {String} feePurpose 费用用途Txt
     *
     * @apiSuccess (200) {Object} cateData 物资分类对象
     * @apiSuccess (200) {String} cateData.id id
     * @apiSuccess (200) {String} cateData.productCateName 物资分类名称
     *
     * @apiSuccess (200) {Object} customerData 客户对象
     * @apiSuccess (200) {String} customerData.id id
     * @apiSuccess (200) {String} customerData.name 企业名称
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<CostSettins>> findAll(CostSettins entity,
                                                    @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable,
                                                     String customerId,
                                                     String cateId) {
        //客户Id查询
        if (EmptyUtil.isNotEmpty(customerId)) {
            Customer customer = new Customer();
            customer.setId(customerId);
            entity.setCustomer(customer);
        }
        //物资分类Id查询
        if (EmptyUtil.isNotEmpty(cateId)) {
            ProductCates cate = new ProductCates();
            cate.setId(cateId);
            entity.setCate(cate);
        }
        Example<CostSettins> example = Example.of(entity);

        Page<CostSettins> apply = this.costSettinsRepository.findAll(example, pageable);
        return new ResponseEntity<Page<CostSettins>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /costSettins/{id} 单个计费方式
     * @apiGroup costSettins
     * @apiVersion 0.0.1
     * @apiDescription 单个计费方式
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /costSettins/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {Integer} opType 收费类型
     * @apiSuccess (200) {String} opTypeTxt 收费类型Txt
     * @apiSuccess (200) {BigDecimal} fee 费用
     * @apiSuccess (200) {DateTime} startTime 开始时间
     * @apiSuccess (200) {DateTime} endTime 结束时间
     * @apiSuccess (200) {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     * @apiSuccess (200) {Integer} chargeUnit 计费单位
     * @apiSuccess (200) {String} chargeUnit 计费单位Txt
     * @apiSuccess (200) {Integer} feePurpose 费用用途
     * @apiSuccess (200) {String} feePurpose 费用用途Txt
     *
     * @apiSuccess (200) {Object} cateData 物资分类对象
     * @apiSuccess (200) {String} cateData.id id
     * @apiSuccess (200) {String} cateData.productCateName 物资分类名称
     *
     * @apiSuccess (200) {Object} customerData 客户对象
     * @apiSuccess (200) {String} customerData.id id
     * @apiSuccess (200) {String} customerData.name 企业名称
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        CostSettins entity = this.costSettinsRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CostSettins>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /costSettins 计费方式添加
     * @apiGroup costSettins
     * @apiVersion 0.0.1
     * @apiDescription 计费方式添加
     * @apiParam {String} customerId 客户Id
     * @apiParam {String} cateId 物资分类Id
     * @apiParam {Integer} opType 收费类型
     * - IN_STOCK(40010, "入库"),
     * - OUT_STOCK(40020, "出库"),
     * - PROCESS(40030, "加工"),
     * - REMOVE_STOCK(40040, "移库"),
     * - BUTT_JOINT(40050, "对接"),
     * - TRANSFER_NAME(40060, "过户"),
     * - TRAY(40070, "托盘"),
     * - STORAGE(40080, "仓储");
     * @apiParam {Integer} feePurpose 费用用途
     * - THEORY(13010, "理重"),
     * - FACT(13020, "实重");
     *
     * @apiParam {BigDecimal} fee 费用
     * @apiParam {DateTime} startTime 开始时间
     * @apiParam {DateTime} endTime 结束时间
     * @apiParam {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /costSettins
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(CostSettins entity,
                                  String customerId,
                                  String cateId) {

        HttpStatusContent status = null;
        entity.setChargeUnit(CostSettinsChargeUnit.TON.getValue());
        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(!CostSettinsOpType.isExist(entity.getOpType())){
            status = new HttpStatusContent(OutputState.FAIL, "收费类型参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(!CostSettinsFeePurpose.isExist(entity.getFeePurpose())){
            status = new HttpStatusContent(OutputState.FAIL, "费用用途参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(EmptyUtil.isEmpty(customerId)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少客户参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(EmptyUtil.isEmpty(cateId)){
            status = new HttpStatusContent(OutputState.FAIL, "缺少物资分类参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Customer customer = this.customerRepository.findOne(customerId);
        if(customer == null) {
            status = new HttpStatusContent(OutputState.FAIL, "客户不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity.setCustomer(customer);

        ProductCates cate = this.productCatesRepository.findOne(cateId);
        if(cate == null) {
            status = new HttpStatusContent(OutputState.FAIL, "物资分类不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity.setCate(cate);

        //查找物资分类的计费方式(一种分类一种收费类型只能有一种费用用途),取列表第1条数据
        CostSettins queryCostSettins = new CostSettins();
        ProductCates queryCate = new ProductCates();
        Customer queryCustomer = new Customer();
        queryCustomer.setId(customer.getId());
        queryCate.setId(cate.getId());
        queryCostSettins.setCate(queryCate);
        queryCostSettins.setCustomer(queryCustomer);
        queryCostSettins.setOpType(entity.getOpType());

        Example<CostSettins> example = Example.of(queryCostSettins);

        List<CostSettins> apply = this.costSettinsRepository.findAll(example);
        if(apply != null && apply.size() > 0){
            status = new HttpStatusContent(OutputState.FAIL,
                    "客户:"+customer.getName()+
                    ",物资分类:"+cate.getProductCateName()+",已经存在收费类型:"+
                    CostSettinsOpType.getTxtByValue(entity.getOpType()));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        entity = this.costSettinsRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {PUT} /costSettins/{id} 计费方式修改
     * @apiGroup costSettins
     * @apiVersion 0.0.1
     * @apiDescription 计费方式修改
     * @apiParam {String} id 主键id
     * @apiParam {String} customerId 客户Id
     * @apiParam {String} cateId 物资分类Id
     * @apiParam {Integer} opType 收费类型
     * - IN_STOCK(40010, "入库"),
     * - OUT_STOCK(40020, "出库"),
     * - PROCESS(40030, "加工"),
     * - REMOVE_STOCK(40040, "移库"),
     * - BUTT_JOINT(40050, "对接"),
     * - TRANSFER_NAME(40060, "过户"),
     * - TRAY(40070, "托盘"),
     * - STORAGE(40080, "仓储");
     * @apiParam {Integer} feePurpose 费用用途
     * - THEORY(13010, "理重"),
     * - FACT(13020, "实重");
     * @apiParam {BigDecimal} fee 费用
     * @apiParam {DateTime} startTime 开始时间
     * @apiParam {DateTime} endTime 结束时间
     * @apiParam {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     * @apiParamExample 请求明文：
     * /costSettins/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 CostSettins entity,
                                 String customerId,
                                 String cateId) {
        HttpStatusContent status = null;

        CostSettins costSettins = this.costSettinsRepository.findOne(id);
        if (costSettins == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        if(entity.getOpType() != null && !CostSettinsOpType.isExist(entity.getOpType())){
            status = new HttpStatusContent(OutputState.FAIL, "收费方式参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(EmptyUtil.isNotEmpty(customerId)){
            Customer customer = this.customerRepository.findOne(customerId);
            if(customer == null) {
                status = new HttpStatusContent(OutputState.FAIL, "客户不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            entity.setCustomer(customer);
        }

        if(EmptyUtil.isNotEmpty(cateId)){
            ProductCates cate = this.productCatesRepository.findOne(cateId);
            if(cate == null) {
                status = new HttpStatusContent(OutputState.FAIL, "物资分类不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            entity.setCate(cate);
        }
        
        ObjectCopyUtil<CostSettins> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, costSettins);
        entity = this.costSettinsRepository.save(costSettins);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }
}

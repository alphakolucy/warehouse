package com.warehouse.controller;

import com.alibaba.fastjson.JSON;
import com.warehouse.model.Customer;
import com.warehouse.model.CustomerLog;
import com.warehouse.model.EntrustOrder;
import com.warehouse.model.EntrustOrderLog;
import com.warehouse.model.Product;
import com.warehouse.service.repository.CustomerLogRepository;
import com.warehouse.service.repository.CustomerRepository;
import com.warehouse.service.repository.ProductRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.EntrustOrderState;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.enums.SettlementMethod;
import com.warehouse.util.enums.WeightMethod;
import com.warehouse.util.tools.EmptyUtil;
import com.warehouse.util.tools.ObjectCopyUtil; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 客户
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerLogRepository customerLogRepository;

    /**
     * @api {GET} /customer 客户列表
     * @apiGroup customer
     * @apiVersion 0.0.1
     * @apiDescription 客户列表
     * @apiParam {String} [name] 企业名称(支持模糊查询)
     * @apiParam {Integer} [settlementMethod] 结算方式
     * - MONTH_SETTLEMENT(20010, "月结"),
     * - NOW_SETTLEMENT(20020, "现结");
     * @apiParam {Integer} [weightMethod] 计重方式
     * - EXCEPT_WEIGHT(30010, "理重"),
     * - FACT_WEIGHT(30020, "实重");
     * @apiParam {Boolean} [isUse] 是否启用
     * - true:启用
     * - false:停用
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /customer?contract_num=1111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} name 企业名称
     * @apiSuccess (200) {Integer} settlementMethod 结算方式
     * @apiSuccess (200) {String} settlementMethodTxt 结算方式Txt
     * @apiSuccess (200) {Integer} weightMethod 计重方式
     * @apiSuccess (200) {String} weightMethodTxt 计重方式Txt
     * @apiSuccess (200) {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Customer>> findAll(Customer entity,
                                                    @PageableDefault(value = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //企业名称模糊方式查
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Customer> example = Example.of(entity, matcher);

        Page<Customer> apply = this.customerRepository.findAll(example, pageable);
        return new ResponseEntity<Page<Customer>>(apply, HttpStatus.OK);
    } 

    /**
     * @api {GET} /customer/{id} 单个客户
     * @apiGroup customer
     * @apiVersion 0.0.1
     * @apiDescription 单个客户
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /customer/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} name 企业名称
     * @apiSuccess (200) {Integer} settlementMethod 结算方式
     * @apiSuccess (200) {String} settlementMethodTxt 结算方式Txt
     * @apiSuccess (200) {Integer} weightMethod 计重方式
     * @apiSuccess (200) {String} weightMethodTxt 计重方式Txt
     * @apiSuccess (200) {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        Customer entity = this.customerRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /customer 客户添加
     * @apiGroup customer
     * @apiVersion 0.0.1
     * @apiDescription 客户添加
     * @apiParam {String} name 企业名称
     * @apiParam {Integer} settlementMethod 结算方式
     * - MONTH_SETTLEMENT(20010, "月结"),
     * - NOW_SETTLEMENT(20020, "现结");
     * @apiParam {Integer} weightMethod 计重方式
     * - EXCEPT_WEIGHT(30010, "理重"),
     * - FACT_WEIGHT(30020, "实重");
     * @apiParam {Boolean} [isUse=true] 是否启用
     * - true:启用
     * - false:停用
     * @apiParamExample 请求明文：
     * /customer
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(Customer entity) {

        HttpStatusContent status = null;

        if(entity.getIsUse() == null){
            //null时设为true
            entity.setIsUse(true);
        }

        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(!SettlementMethod.isExist(entity.getSettlementMethod())){
            status = new HttpStatusContent(OutputState.FAIL, "结算方式参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(!WeightMethod.isExist(entity.getWeightMethod())){
            status = new HttpStatusContent(OutputState.FAIL, "计重方式参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //记录新建状态日志
        CustomerLog log = new CustomerLog();
        log.setCreateMan(entity.getCreateMan());
        log.setCreateManId(entity.getCreateManId());
        log.setOpState("添加");
        log.setCustomer(entity);
        log.setRemark("添加");
        Set<CustomerLog> logSet = new HashSet<>();
        logSet.add(log);
        entity.setCustomerLog(logSet);//2.记录状态变更日志(关联会自动写入)
        
        entity = this.customerRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {PUT} /customer/{id} 客户修改
     * @apiGroup customer
     * @apiVersion 0.0.1
     * @apiDescription 客户修改
     * @apiParam {String} id 主键id
     * @apiParam {String} name 企业名称
     * @apiParam {Integer} settlementMethod 结算方式
     * - MONTH_SETTLEMENT(20010, "月结"),
     * - NOW_SETTLEMENT(20020, "现结");
     * @apiParam {Integer} weightMethod 计重方式
     * - EXCEPT_WEIGHT(30010, "理重"),
     * - FACT_WEIGHT(30020, "实重");
     * @apiParam {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     * @apiParamExample 请求明文：
     * /customer/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id, Customer entity) {
        HttpStatusContent status = null;

        Customer customer = this.customerRepository.findOne(id);
        if (customer == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if(entity.getSettlementMethod()!= null
            && !SettlementMethod.isExist(entity.getSettlementMethod())){
            status = new HttpStatusContent(OutputState.FAIL, "结算方式参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(entity.getWeightMethod()!= null
            && !WeightMethod.isExist(entity.getWeightMethod())){
            status = new HttpStatusContent(OutputState.FAIL, "计重方式参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
         
        
        //记录新建状态日志
        CustomerLog log = new CustomerLog();
        log.setCreateMan(customer.getCreateMan());
        log.setCreateManId(customer.getCreateManId());
        log.setOpState("修改");
        log.setCustomer(customer);
        log.setRemark("修改, " + customer.toString() + " to " + entity.toString());
        Set<CustomerLog> logSet = new HashSet<>();
        logSet.add(log);
        customer.setCustomerLog(logSet);//2.记录状态变更日志(关联会自动写入)

        ObjectCopyUtil<Customer> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, customer);
        entity = this.customerRepository.save(customer);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }
    
    /**
     * @api {POST} /customer/product 客户添加
     * @apiGroup customer
     * @apiVersion 0.0.1
     * @apiDescription 客户添加
     * @apiParam {String} name 企业名称
     * @apiParam {Integer} settlementMethod 结算方式
     * - MONTH_SETTLEMENT(20010, "月结"),
     * - NOW_SETTLEMENT(20020, "现结");
     * @apiParam {Integer} weightMethod 计重方式
     * - EXCEPT_WEIGHT(30010, "理重"),
     * - FACT_WEIGHT(30020, "实重");
     * @apiParamExample 请求明文：
     * /customer/product
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json", value="/product")
    public ResponseEntity<?> product(String customerId, String productIds) {

        HttpStatusContent status = null;
        

        Customer customer = this.customerRepository.findOne(customerId);
        if (customer == null) {
            status = new HttpStatusContent(OutputState.FAIL, "客户数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        } 
         
        List<Product> ts = new ArrayList<>();
 
        if (EmptyUtil.isNotEmpty(productIds) && productIds.split(",").length > 0) {
            String[] strProductIds = productIds.split(",");
            for (String rId : strProductIds) {
                Product one = productRepository.findOne(rId);
                if(one == null){
                    status = new HttpStatusContent(OutputState.FAIL,"物资不存在");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if(one.getIsUse() == null || !one.getIsUse()){
                    status = new HttpStatusContent(OutputState.FAIL,
                            "物资："
                                    +one.getProductName()
                                    +"规格:"+one.getSpec()
                            +",未启用");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                ts.add(one);
            }
        }
        
        //记录新建状态日志
        CustomerLog log = new CustomerLog();
        log.setCreateMan(customer.getCreateMan());
        log.setCreateManId(customer.getCreateManId());
        log.setOpState("管理物资");
        log.setCustomer(customer);
        log.setRemark("管理物资");
        Set<CustomerLog> logSet = new HashSet<>();
        logSet.add(log);
        customer.setCustomerLog(logSet);//2.记录状态变更日志(关联会自动写入)
  
        customer.setProducts(ts); 
        customer = this.customerRepository.save(customer);
        if (customer == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }
    
    @GetMapping(produces = "application/json", value="/{id}/log")
    public ResponseEntity<List<CustomerLog>> log(@PathVariable(value = "id") String id) {
    	
    		CustomerLog entity = new CustomerLog();
        //委托单Id查询
        if (EmptyUtil.isNotEmpty(id)) {
            Customer customer = new Customer();
            customer.setId(id);
            entity.setCustomer(customer);
        }

        Example<CustomerLog> example = Example.of(entity); 
        List<CustomerLog> log = this.customerLogRepository.findAll(example);
        return new ResponseEntity<List<CustomerLog>>(log, HttpStatus.OK);
    }
}

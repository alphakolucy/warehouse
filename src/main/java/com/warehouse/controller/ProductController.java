package com.warehouse.controller;
  
import com.warehouse.model.Customer;
import com.warehouse.model.Product;
import com.warehouse.model.ProductCates;
import com.warehouse.service.repository.CustomerRepository;
import com.warehouse.service.repository.ProductCatesRepository;
import com.warehouse.service.repository.ProductRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.tools.EmptyUtil;
import com.warehouse.util.tools.ObjectCopyUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 物资-管理
 */
@RestController
@RequestMapping("/product")
public class ProductController {
	
	private  Logger logger = LoggerFactory.getLogger(ProductController.class);
	
//	@GetMapping("/test")
//    public void test(){
//        logger.info("测试初始一些日志吧！fffffffffffffffffffffffff");
//    }

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductCatesRepository productCatesRepository;

    /**
     * @api {GET} /product 物资列表
     * @apiGroup product
     * @apiVersion 0.0.1
     * @apiDescription 物资列表
     * @apiParam {String} [productName] 物资名称(支持模糊查询)
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {Boolean} [isUse] 是否启用
     * - true:启用
     * - false:停用
     *
     * @apiParam {String} [sort=createTime,asc] 排序
     * - 格式： sort=createTime,desc 表示在按createTime由高到低排列
     * - 格式： sort=createTime,asc 表示在按createTime由低到高排列
     * @apiParamExample 请求明文：
     * /product
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} productName 物资名称
     * @apiSuccess (200) {String} unit 物资单位
     * @apiSuccess (200) {String} rationale 物资理重
     * @apiSuccess (200) {String} spec 物资规格
     * @apiSuccess (200) {String} model 物资型号
     * @apiSuccess (200) {String} material 物资材料
     * @apiSuccess (200) {String} maker 物资厂家
     * @apiSuccess {Date} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     *
     * @apiSuccess (200) {Object} customer 客户对象
     * @apiSuccess (200) {String} customer.id id
     * @apiSuccess (200) {String} customer.name 企业名称
     *
     * @apiSuccess (200) {Object} cate 物资分类对象
     * @apiSuccess (200) {String} cate.id 主键id
     * @apiSuccess (200) {String} cate.productCateName 物资分类名称
     *
     * @apiSuccess (200) {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
//    @PreAuthorize("hasAuthority('ROLE_HR12312312313')")
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Product>> findAll(Product entity,
                                                 String customerId,
                                                 String cateId,
                                                 @PageableDefault(value = 10,
                                                         sort = {"createTime"},
                                                         direction = Sort.Direction.DESC)
                                                         Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //物资名称模糊方式查
                .withMatcher("productName", ExampleMatcher.GenericPropertyMatchers.contains());


        // 根据客户customerId查询
        if (EmptyUtil.isNotEmpty(customerId)) {
            Customer customer = new Customer();
            customer.setId(customerId);
            List<Customer> lsCustomer = new ArrayList<Customer>();
            lsCustomer.add(customer);
            entity.setCustomers(lsCustomer);
        }

        // 根据物资分类cateId查询
        if (EmptyUtil.isNotEmpty(cateId)) {
            ProductCates cate = new ProductCates();
            cate.setId(cateId);
            entity.setCate(cate);
        }

        Example<Product> example = Example.of(entity, matcher);
        Page<Product> apply = this.productRepository.findAll(example, pageable);
        return new ResponseEntity<Page<Product>>(apply, HttpStatus.OK);
    }

    /**
     * @api {GET} /product/{id} 物资详细
     * @apiGroup product
     * @apiVersion 0.0.1
     * @apiDescription 物资详细
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /product/1
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} productName 物资名称
     * @apiSuccess (200) {String} unit 物资单位
     * @apiSuccess (200) {String} rationale 物资理重
     * @apiSuccess (200) {String} spec 物资规格
     * @apiSuccess (200) {String} model 物资型号
     * @apiSuccess (200) {String} material 物资材料
     * @apiSuccess (200) {String} maker 物资厂家
     * @apiSuccess {Date} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     *
     * @apiSuccess (200) {Object} customer 客户对象
     * @apiSuccess (200) {String} customer.id id
     * @apiSuccess (200) {String} customer.name 企业名称
     *
     * @apiSuccess (200) {Object} cate 物资分类对象
     * @apiSuccess (200) {String} cate.id 主键id
     * @apiSuccess (200) {String} cate.productCateName 物资分类名称
     *
     * @apiSuccess (200) {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        Product entity = this.productRepository.findOne(id); 
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    /**
     * @api {POST} /product 物资添加
     * @apiGroup product
     * @apiVersion 0.0.1
     * @apiDescription 物资添加
     * @apiParam {String} customerId 客户id
     * @apiParam {String} cateId 物资分类id
     * @apiParam {String} productName 物资名称
     * @apiParam {String} unit 物资单位
     * @apiParam {String} rationale 物资理重
     * @apiParam {String} spec 物资规格
     * @apiParam {String} model 物资型号
     * @apiParam {String} material 物资材料
     * @apiParam {String} maker 物资厂家
     * @apiParam {Boolean} [isUse=true] 是否启用
     * - true:启用
     * - false:停用
     * @apiParamExample 请求明文：
     * /product
     * @apiSuccess (200) {String} id 主键id
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(Product entity,  String cateId) {
        HttpStatusContent status = null;

        if(entity.getIsUse() == null){
            //null时设为true
            entity.setIsUse(true);
        }

        if (EmptyUtil.isEmpty(cateId)) {
            status = new HttpStatusContent(OutputState.FAIL, "未传入分类ID！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        Product exist = new Product();
        exist.setMaker(entity.getMaker().trim());
        exist.setMaterial(entity.getMaterial().trim());
        exist.setModel(entity.getModel().trim());
        exist.setProductName(entity.getProductName().trim()); 
        exist.setSpec(entity.getSpec().trim());
        
        Example<Product> example = Example.of(exist);
        List<Product> plist = this.productRepository.findAll(example);
        if(plist.size() > 0) {
        		status = new HttpStatusContent(OutputState.FAIL, "商品已存在！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ProductCates cate = this.productCatesRepository.findOne(cateId);
        if (cate == null) {
            status = new HttpStatusContent(OutputState.FAIL, "未找到相关的分类信息！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

//        List<Customer> lsCustomer = new ArrayList<Customer>();
//        lsCustomer.add(customer);
//        entity.setCustomer(lsCustomer);
        
        entity.setCate(cate);
        entity = this.productRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    /**
     * @api {PUT} /product 物资修改
     * @apiGroup product/1
     * @apiVersion 0.0.1
     * @apiDescription 物资修改
     * @apiParam {String} id 主键id
     * @apiParam {String} customerId 客户id
     * @apiParam {String} cateId 物资分类id
     * @apiParam {String} productName 物资名称
     * @apiParam {String} unit 物资单位
     * @apiParam {String} rationale 物资理重
     * @apiParam {String} spec 物资规格
     * @apiParam {String} model 物资型号
     * @apiParam {String} material 物资材料
     * @apiParam {String} maker 物资厂家
     * @apiParam {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     * @apiParamExample 请求明文：
     * /product
     * @apiSuccess (200) {String} id 主键id
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,  String cateId, Product
            entity) {

        HttpStatusContent status = null;


        Product product = this.productRepository.getOne(id);
        if (product == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }

//        if (EmptyUtil.isEmpty(customerId)) {
//            status = new HttpStatusContent(OutputState.FAIL, "未传入客户ID！");
//            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        if (EmptyUtil.isEmpty(cateId)) {
            status = new HttpStatusContent(OutputState.FAIL, "未传入分类ID！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ObjectCopyUtil<Product> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, product);

//        Customer customer = this.customerRepository.findOne(customerId);
//        if (customer == null) {
//            status = new HttpStatusContent(OutputState.FAIL, "未找到相关的客户信息！");
//            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        ProductCates cate = this.productCatesRepository.findOne(cateId);
        if (cate == null) {
            status = new HttpStatusContent(OutputState.FAIL, "未找到相关的分类信息！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
      
        product.setCate(cate);

        entity = this.productRepository.save(product);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


}

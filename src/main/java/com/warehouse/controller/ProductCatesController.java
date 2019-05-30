package com.warehouse.controller;

import com.warehouse.model.ProductCates;
import com.warehouse.service.repository.ProductCatesRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.tools.ObjectCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 物资分类-管理
 */
@RestController
@RequestMapping("/productCates")
public class ProductCatesController {

    @Autowired
    private ProductCatesRepository productCatesRepository;

    /**
     * @api {GET} /productCates 物资分类列表
     * @apiGroup productCates
     * @apiVersion 0.0.1
     * @apiDescription 物资分类列表
     * @apiParam {String} [productCateName] 物资分类名称(支持模糊查询)
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=createTime,asc] 排序
     * - 格式： sort=createTime,desc 表示在按createTime由高到低排列
     * - 格式： sort=createTime,asc 表示在按createTime由低到高排列
     * @apiParamExample 请求明文：
     * /productCates
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} productCateName 物资分类名称
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<ProductCates>> findAll(ProductCates entity,
                                                      @PageableDefault(value = 10,
                                                              sort = {"createTime"},
                                                              direction = Sort.Direction.DESC)
                                                              Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //物资分类名称模糊方式查
                .withMatcher("productCateName", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<ProductCates> example = Example.of(entity, matcher);
        Page<ProductCates> apply = this.productCatesRepository.findAll(example, pageable);
        return new ResponseEntity<Page<ProductCates>>(apply, HttpStatus.OK);
    }

    /**
     * @api {GET} /productCates/{id} 物资分类详细
     * @apiGroup productCates
     * @apiVersion 0.0.1
     * @apiDescription 物资分类详细
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /productCates/1
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} productCateName 物资名称
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        ProductCates entity = this.productCatesRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    /**
     * @api {POST} /productCates 物资分类添加
     * @apiGroup productCates
     * @apiVersion 0.0.1
     * @apiDescription 物资分类添加
     * @apiParam {String} name 物资分类名称
     * @apiParamExample 请求明文：
     * /productCates
     * @apiSuccess (200) {String} id 主键id
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(ProductCates entity) {
        HttpStatusContent status = null;
        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity = this.productCatesRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    /**
     * @api {PUT} /productCates 物资分类修改
     * @apiGroup productCates
     * @apiVersion 0.0.1
     * @apiDescription 物资分类修改
     * @apiParam {String} id 主键id
     * @apiParam {String} name 物资分类名称
     * @apiParamExample 请求明文：
     * /productCates
     * @apiSuccess (200) {String} id 主键id
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id, ProductCates entity) {

        HttpStatusContent status = null;

        ProductCates productCates = this.productCatesRepository.getOne(id);
        if (productCates == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }

        ObjectCopyUtil<ProductCates> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, productCates);
        entity = this.productCatesRepository.save(productCates);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


}

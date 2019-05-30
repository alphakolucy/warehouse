package com.warehouse.controller;

import com.warehouse.model.Steelyard;
import com.warehouse.model.Warehouse;
import com.warehouse.service.repository.SteelyardRepository;
import com.warehouse.service.repository.WarehouseRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.tools.DateUtil;
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
 * 秤
 */
@RestController
@RequestMapping("/steelyard")
public class SteelyardController {
    @Autowired
    private SteelyardRepository steelyardRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    /**
     * @api {GET} /steelyard 秤列表
     * @apiGroup steelyard
     * @apiVersion 0.0.1
     * @apiDescription 秤列表
     * @apiParam {String} [steelyardNo] 吊车编号(支持模糊查询)
     * @apiParam {String} [warehouseId] 库区Id
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
     * /steelyard?contract_num=1111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} steelyardNo 吊车编号
     * @apiSuccess (200) {Integer} maintainPeriod 维护周期
     * @apiSuccess (200) {Date} maintainDate 维护日期
     *
     * @apiSuccess (200) {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Steelyard>> findAll(Steelyard entity,
                                                    String warehouseId,
                                                    @PageableDefault(value = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //编号模糊方式查
                .withMatcher("steelyardNo", ExampleMatcher.GenericPropertyMatchers.contains());
        //根据库区Id
        if(EmptyUtil.isNotEmpty(warehouseId)){
            Warehouse warehouse = new Warehouse();
            warehouse.setId(warehouseId);
            entity.setWarehouse(warehouse);
        }

        Example<Steelyard> example = Example.of(entity, matcher);

        Page<Steelyard> apply = this.steelyardRepository.findAll(example, pageable);
        return new ResponseEntity<Page<Steelyard>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /steelyard/{id} 单个秤
     * @apiGroup steelyard
     * @apiVersion 0.0.1
     * @apiDescription 单个秤
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /steelyard/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} steelyardNo 吊车编号
     * @apiSuccess (200) {Integer} maintainPeriod 维护周期
     * @apiSuccess (200) {Date} maintainDate 维护日期
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
        Steelyard entity = this.steelyardRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Steelyard>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /steelyard 秤添加
     * @apiGroup steelyard
     * @apiVersion 0.0.1
     * @apiDescription 秤添加
     * @apiParam {String} steelyardNo 吊车编号
     * @apiParam {String} warehouseId 库区Id(第二级)
     * @apiParam {Integer} maintainPeriod 维护周期
     * @apiParam {Date} putIntoDate 投用日期
     *
     * @apiParam {Boolean} [isUse=true] 是否启用
     * - true:启用
     * - false:停用
     * @apiParamExample 请求明文：
     * /steelyard
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(Steelyard entity,
                                  String warehouseId) {

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

        Steelyard querySteelyard = new Steelyard();
        querySteelyard.setSteelyardNo(entity.getSteelyardNo());
        Example<Steelyard> example = Example.of(querySteelyard);
        boolean exists = this.steelyardRepository.exists(example);
        if(exists){
            status = new HttpStatusContent(OutputState.FAIL, "编号已经存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(EmptyUtil.isEmpty(warehouseId)){
            status = new HttpStatusContent(OutputState.FAIL, "缺少库区参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Warehouse warehouse = this.warehouseRepository.findOne(warehouseId);
        if(warehouse == null){
            status = new HttpStatusContent(OutputState.FAIL, "库区不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity.setWarehouse(warehouse);

        if(entity.getPutIntoDate() != null
                && entity.getMaintainPeriod() != null){
            entity.setMaintainDate(DateUtil.addDate(entity.getPutIntoDate(),0,0,entity.getMaintainPeriod(),0,0,0,0));
        }

        entity = this.steelyardRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {PUT} /steelyard/{id} 秤修改
     * @apiGroup steelyard
     * @apiVersion 0.0.1
     * @apiDescription 秤修改
     * @apiParam {String} id 主键id
     * @apiParam {String} steelyardNo 吊车编号
     * @apiParam {String} warehouseId 库区Id(第二级)
     * @apiParam {Integer} maintainPeriod 维护周期
     *
     * @apiParam {Boolean} [isUse] 是否启用
     * - true:启用
     * - false:停用
     * @apiParamExample 请求明文：
     * /steelyard/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 Steelyard entity,
                                 String warehouseId) {
        HttpStatusContent status = null;
        entity.setMaintainDate(null);//不能直接修改

        Steelyard steelyard = this.steelyardRepository.findOne(id);
        if (steelyard == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if(EmptyUtil.isNotEmpty(entity.getSteelyardNo())){
            Steelyard querySteelyard = new Steelyard();
            querySteelyard.setSteelyardNo(entity.getSteelyardNo());
            Example<Steelyard> example = Example.of(querySteelyard);
            List<Steelyard> all = this.steelyardRepository.findAll(example);
            if(all != null && all.size()>0){
                for(int i=0;i<all.size();i++){
                    if(!all.get(i).getId().equals(steelyard.getId())){
                        status = new HttpStatusContent(OutputState.FAIL, "编号已经存在！");
                        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
            }
        }

        if(EmptyUtil.isNotEmpty(warehouseId)){
            Warehouse warehouse = this.warehouseRepository.findOne(warehouseId);
            if(warehouse == null){
                status = new HttpStatusContent(OutputState.FAIL, "库区不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            entity.setWarehouse(warehouse);
        }

        ObjectCopyUtil<Steelyard> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, steelyard);
        entity = this.steelyardRepository.save(steelyard);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }
}

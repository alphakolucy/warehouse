package com.warehouse.controller;

import com.warehouse.model.Crane;
import com.warehouse.model.CraneBindSteelyardLog;
import com.warehouse.model.Steelyard;
import com.warehouse.model.Warehouse;
import com.warehouse.service.repository.CraneRepository;
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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 龙门吊
 */
@RestController
@RequestMapping("/crane")
public class CraneController {
    @Autowired
    private CraneRepository craneRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private SteelyardRepository steelyardRepository;

    /**
     * @api {GET} /crane 龙门吊列表
     * @apiGroup crane
     * @apiVersion 0.0.1
     * @apiDescription 龙门吊列表
     * @apiParam {String} [craneNo] 吊车编号(支持模糊查询)
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
     * /crane?contract_num=1111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} craneNo 吊车编号
     * @apiSuccess (200) {Integer} maintainPeriod 维护周期
     * @apiSuccess (200) {Date} maintainDate 维护日期
     *
     * @apiSuccess (200) {Object} steelyardData 秤对象
     * @apiSuccess (200) {String} steelyardData.id id
     * @apiSuccess (200) {String} steelyardData.steelyardNo 编号
     *
     * @apiSuccess (200) {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Crane>> findAll(Crane entity,
                                                    String warehouseId,
                                                    @PageableDefault(value = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //编号模糊方式查
                .withMatcher("craneNo", ExampleMatcher.GenericPropertyMatchers.contains());
        //根据仓库Id
        if(EmptyUtil.isNotEmpty(warehouseId)){
            Warehouse warehouse = new Warehouse();
            warehouse.setId(warehouseId);
            entity.setWarehouse(warehouse);
        }

        Example<Crane> example = Example.of(entity, matcher);

        Page<Crane> apply = this.craneRepository.findAll(example, pageable);
        return new ResponseEntity<Page<Crane>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /crane/{id} 单个龙门吊
     * @apiGroup crane
     * @apiVersion 0.0.1
     * @apiDescription 单个龙门吊
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /crane/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} craneNo 吊车编号
     * @apiSuccess (200) {Integer} maintainPeriod 维护周期
     * @apiSuccess (200) {Date} maintainDate 维护日期
     *
     * @apiSuccess (200) {Object} steelyardData 秤对象
     * @apiSuccess (200) {String} steelyardData.id id
     * @apiSuccess (200) {String} steelyardData.steelyardNo 编号
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
        Crane entity = this.craneRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Crane>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /crane 龙门吊添加
     * @apiGroup crane
     * @apiVersion 0.0.1
     * @apiDescription 龙门吊添加
     * @apiParam {String} craneNo 吊车编号
     * @apiParam {String} warehouseId 库区Id(第二级)
     * @apiParam {Integer} maintainPeriod 维护周期
     * @apiParam {Date} putIntoDate 投用日期
     *
     * @apiParam {Boolean} [isUse=true] 是否启用
     * - true:启用
     * - false:停用
     * @apiParamExample 请求明文：
     * /crane
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(Crane entity,
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

        Crane queryCrane = new Crane();
        queryCrane.setCraneNo(entity.getCraneNo());
        Example<Crane> example = Example.of(queryCrane);
        boolean exists = this.craneRepository.exists(example);
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

        entity = this.craneRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {PUT} /crane/{id} 龙门吊修改
     * @apiGroup crane
     * @apiVersion 0.0.1
     * @apiDescription 龙门吊修改
     * @apiParam {String} id 主键id
     * @apiParam {String} craneNo 吊车编号
     * @apiParam {String} warehouseId 库区Id(第二级)
     * @apiParam {Integer} maintainPeriod 维护周期
     *
     * @apiParam {Boolean} [isUse] 是否启用
     * - true:启用
     * - false:停用
     *
     * @apiParamExample 请求明文：
     * /crane/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 Crane entity,
                                 String warehouseId) {
        HttpStatusContent status = null;
        entity.setMaintainDate(null);//不能直接修改

        Crane crane = this.craneRepository.findOne(id);
        if (crane == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if(EmptyUtil.isNotEmpty(entity.getCraneNo())){
            Crane queryCrane = new Crane();
            queryCrane.setCraneNo(entity.getCraneNo());
            Example<Crane> example = Example.of(queryCrane);
            List<Crane> all = this.craneRepository.findAll(example);
            if(all != null && all.size()>0){
                for(int i=0;i<all.size();i++){
                    if(!all.get(i).getId().equals(crane.getId())){
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

        ObjectCopyUtil<Crane> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, crane);
        entity = this.craneRepository.save(crane);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {POST} /crane/bind 龙门吊绑定/解绑秤
     * @apiGroup crane
     * @apiVersion 0.0.1
     * @apiDescription 龙门吊绑定/解绑秤
     * @apiParam {String} id 主键id
     * @apiParam {String} steelyardId 秤Id(绑定)
     * @apiParam {Boolean} opType 操作类型
     * - true:绑定
     * - false:解绑
     * @apiParam {String} remark 备注(解绑)
     * @apiParamExample 请求明文：
     * /crane/bind
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json", value = "/bind")
    public ResponseEntity<?> bind(String id,
                                 String createMan,
                                 String createManId,
                                 Boolean opType,
                                 String steelyardId,
                                  String remark) {
        HttpStatusContent status = null;

        if (EmptyUtil.isEmpty(id)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少参数id！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (EmptyUtil.isEmpty(opType)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少操作类型！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)) {
            status = new HttpStatusContent(OutputState.FAIL, "操作人和操作人Id必传！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        Crane crane = this.craneRepository.findOne(id);
        if (crane == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        //都要写日志
        CraneBindSteelyardLog log = new CraneBindSteelyardLog();
        log.setCrane(crane);
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOpType(opType);//操作类型

        if(opType){
            //绑定
            if(crane.getSteelyard()!=null){
                status = new HttpStatusContent(OutputState.FAIL, "再次绑定请先解绑！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(EmptyUtil.isEmpty(steelyardId)){
                status = new HttpStatusContent(OutputState.FAIL, "绑定请输入秤参数！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Steelyard steelyard = this.steelyardRepository.findOne(steelyardId);
            if(steelyard == null){
                status = new HttpStatusContent(OutputState.FAIL, "秤不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
            if (steelyard.getCrane().size() > 0) {
            		status = new HttpStatusContent(OutputState.FAIL, "秤已绑定龙门吊！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
            
            if(steelyard.getIsUse() == null || !steelyard.getIsUse()){
                status = new HttpStatusContent(OutputState.FAIL,
                        "秤："+steelyard.getSteelyardNo()
                                +",未启用");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            crane.setSteelyard(steelyard);
            log.setSteelyard(steelyard);
        }else {
            //解绑
            if(EmptyUtil.isEmpty(remark)){
                status = new HttpStatusContent(OutputState.FAIL, "解绑时备注必填！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(crane.getSteelyard() == null){
                status = new HttpStatusContent(OutputState.FAIL, "未绑定无法解绑！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            log.setSteelyard(crane.getSteelyard());
            log.setRemark(remark);
            crane.setSteelyard(null);
        }
        //修改龙门吊时，通过关联添加日志
        Set<CraneBindSteelyardLog> logSet = new HashSet<>();
        logSet.add(log);
        crane.setCraneBindSteelyardLogSet(logSet);

        crane = this.craneRepository.save(crane);
        if (crane == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }
}

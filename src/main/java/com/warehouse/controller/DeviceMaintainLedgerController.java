package com.warehouse.controller;

import com.warehouse.model.Crane;
import com.warehouse.model.DeviceMaintainLedger;
import com.warehouse.model.Steelyard;
import com.warehouse.service.repository.CraneRepository;
import com.warehouse.service.repository.DeviceMaintainLedgerRepository;
import com.warehouse.service.repository.SteelyardRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.DeviceType;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.tools.DateUtil;
import com.warehouse.util.tools.ObjectCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 设备维护台账
 */
@RestController
@RequestMapping("/deviceMaintainLedger")
public class DeviceMaintainLedgerController {
    @Autowired
    private DeviceMaintainLedgerRepository deviceMaintainLedgerRepository;
    @Autowired
    private CraneRepository craneRepository;
    @Autowired
    private SteelyardRepository steelyardRepository;

    /**
     * @api {GET} /deviceMaintainLedger 设备维护台账列表
     * @apiGroup deviceMaintainLedger
     * @apiVersion 0.0.1
     * @apiDescription 设备维护台账列表
     * @apiParam {String} deviceNo 设备编号
     * @apiParam {String} deviceId 设备id
     * @apiParam {Integer} deviceType 设备类型
     * - CRANE(17010, "龙门吊"),
     * - STEELYARD(17020, "秤");
     * @apiParam {String} [maintainDate] 维护日期
     * - yyyy-MM-dd
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /deviceMaintainLedger?name=1111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} deviceNo 设备编号
     * @apiSuccess (200) {String} deviceId 设备id
     * @apiSuccess (200) {String} deviceTypeTxt 设备类型Txt
     * @apiSuccess (200) {Integer} deviceType 设备类型
     * - CRANE(17010, "龙门吊"),
     * - STEELYARD(17020, "秤");
     * @apiSuccess (200) {String} content 维护内容
     * @apiSuccess (200) {String} maintainMan 维护人员(来自排班)
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {Date} maintainDate 维护日期
     * - yyyy-MM-dd
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<DeviceMaintainLedger>> findAll(DeviceMaintainLedger entity,
                                                    @PageableDefault(value = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                //模糊方式查
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("job", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<DeviceMaintainLedger> example = Example.of(entity, matcher);

        Page<DeviceMaintainLedger> apply = this.deviceMaintainLedgerRepository.findAll(example, pageable);
        return new ResponseEntity<Page<DeviceMaintainLedger>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /deviceMaintainLedger/{id} 单个设备维护台账
     * @apiGroup deviceMaintainLedger
     * @apiVersion 0.0.1
     * @apiDescription 单个设备维护台账
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /deviceMaintainLedger/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} deviceNo 设备编号
     * @apiSuccess (200) {String} deviceId 设备id
     * @apiSuccess (200) {String} deviceTypeTxt 设备类型Txt
     * @apiSuccess (200) {Integer} deviceType 设备类型
     * - CRANE(17010, "龙门吊"),
     * - STEELYARD(17020, "秤");
     * @apiSuccess (200) {String} content 维护内容
     * @apiSuccess (200) {String} maintainMan 维护人员(来自排班)
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {Date} maintainDate 维护日期
     * - yyyy-MM-dd
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        DeviceMaintainLedger entity = this.deviceMaintainLedgerRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<DeviceMaintainLedger>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /deviceMaintainLedger 设备维护台账添加
     * @apiGroup deviceMaintainLedger
     * @apiVersion 0.0.1
     * @apiDescription 设备维护台账添加
     * @apiParam {String} deviceNo 设备编号
     * @apiParam {String} deviceId 设备id
     * @apiParam {Integer} deviceType 设备类型
     * - CRANE(17010, "龙门吊"),
     * - STEELYARD(17020, "秤");
     * @apiParam {String} content 维护内容
     * @apiParam {String} maintainMan 维护人员(来自排班)
     * @apiParam {String} remark 备注
     * @apiParam {Date} maintainDate 维护日期
     * - yyyy-MM-dd
     * @apiParamExample 请求明文：
     * /deviceMaintainLedger
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @Transactional
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(DeviceMaintainLedger entity) {

        HttpStatusContent status = null;
        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //修改相关设备的维护日期
        if(entity.getDeviceType().equals(DeviceType.CRANE.getValue())){
            Crane crane = this.craneRepository.findOne(entity.getDeviceId());
            if(crane==null){
                status = new HttpStatusContent(OutputState.FAIL,
                        DeviceType.getTxtByValue(entity.getDeviceType())+"数据不存在");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            crane.setMaintainDate(DateUtil.addDate(entity.getMaintainDate(),0,0,crane.getMaintainPeriod(),0,0,0,0));
            this.craneRepository.save(crane);
        }else if(entity.getDeviceType().equals(DeviceType.STEELYARD.getValue())){
            Steelyard steelyard = this.steelyardRepository.findOne(entity.getDeviceId());
            if(steelyard==null){
                status = new HttpStatusContent(OutputState.FAIL,
                        DeviceType.getTxtByValue(entity.getDeviceType())+"数据不存在");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            steelyard.setMaintainDate(DateUtil.addDate(entity.getMaintainDate(),0,0,steelyard.getMaintainPeriod(),0,0,0,0));
            this.steelyardRepository.save(steelyard);
        }

        entity = this.deviceMaintainLedgerRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {PUT} /deviceMaintainLedger/{id} 设备维护台账修改
     * @apiGroup deviceMaintainLedger
     * @apiVersion 0.0.1
     * @apiDescription 设备维护台账修改
     * @apiParam {String} id 主键id
     * @apiParam {String} deviceNo 设备编号
     * @apiParam {String} deviceId 设备id
     * @apiParam {Integer} deviceType 设备类型
     * - CRANE(17010, "龙门吊"),
     * - STEELYARD(17020, "秤");
     * @apiParam {String} content 维护内容
     * @apiParam {String} maintainMan 维护人员(来自排班)
     * @apiParam {String} remark 备注
     * @apiParam {Date} maintainDate 维护日期
     * - yyyy-MM-dd
     * @apiParamExample 请求明文：
     * /deviceMaintainLedger/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @Transactional
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 DeviceMaintainLedger entity) {
        HttpStatusContent status = null;

        DeviceMaintainLedger deviceMaintainLedger = this.deviceMaintainLedgerRepository.findOne(id);
        if (deviceMaintainLedger == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        //修改维护日期
        if(entity.getMaintainDate()!=null){
            //修改相关设备的维护日期
            if(deviceMaintainLedger.getDeviceType().equals(DeviceType.CRANE.getValue())){
                Crane crane = this.craneRepository.findOne(deviceMaintainLedger.getDeviceId());
                crane.setMaintainDate(DateUtil.addDate(entity.getMaintainDate(),0,0,crane.getMaintainPeriod(),0,0,0,0));
                this.craneRepository.save(crane);
            }else if(deviceMaintainLedger.getDeviceType().equals(DeviceType.STEELYARD.getValue())){
                Steelyard steelyard = this.steelyardRepository.findOne(deviceMaintainLedger.getDeviceId());
                steelyard.setMaintainDate(DateUtil.addDate(entity.getMaintainDate(),0,0,steelyard.getMaintainPeriod(),0,0,0,0));
                this.steelyardRepository.save(steelyard);
            }
        }

        ObjectCopyUtil<DeviceMaintainLedger> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, deviceMaintainLedger);
        entity = this.deviceMaintainLedgerRepository.save(deviceMaintainLedger);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }
}

package com.warehouse.controller;

import com.warehouse.model.Warehouse;
import com.sun.tools.example.debug.expr.ParseException;
import com.warehouse.model.Scheduling;
import com.warehouse.model.Worker;
import com.warehouse.service.repository.SteelyardRepository;
import com.warehouse.service.repository.WarehouseRepository;
import com.warehouse.service.repository.SchedulingRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.OutputState;
import com.warehouse.util.tools.DateUtil;
import com.warehouse.util.tools.EmptyUtil;
import com.warehouse.util.tools.ObjectCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 排班
 */
@RestController
@RequestMapping("/scheduling")
public class SchedulingController {
    @Autowired
    private SchedulingRepository schedulingRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    /**
     * @api {GET} /scheduling 排班列表
     * @apiGroup scheduling
     * @apiVersion 0.0.1
     * @apiDescription 排班列表
     * @apiParam {String} [name] 名称(支持模糊查询)
     * @apiParam {String} [job] 岗位(支持模糊查询)
     * @apiParam {String} [schedulingTime] 当值时间
     * - yyyy-MM-dd
     * @apiParam {String} [startDate] 当值时间开始>=
     * - yyyy-MM-dd
     * @apiParam {String} [endDate] 当值时间结束<=
     * - yyyy-MM-dd
     * @apiParam {String} [warehouseSiteId] 仓库Id(第一级)
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /scheduling?name=1111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} name 名称
     * @apiSuccess (200) {String} job 岗位
     * @apiSuccess (200) {Date} schedulingTime 当值时间
     * - yyyy-MM-dd
     * @apiSuccess (200) {DateTime} createTime 创建时间
     *
     * @apiSuccess (200) {Object} warehouseSiteData 仓库对象
     * @apiSuccess (200) {String} warehouseSiteData.id id
     * @apiSuccess (200) {String} warehouseSiteData.name 名称
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Scheduling>> findAll(Scheduling entity,
                                                    String startDate,
                                                    String endDate,
                                                    String warehouseSiteId,
                                                    @PageableDefault(value = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable) {

        Specification<Scheduling> specification = new Specification<Scheduling>() {
            @Override
            public Predicate toPredicate(Root<Scheduling> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                //删除状态为null,查询未删除的数据
                if(entity.getDeleteStatus()==null){
                    entity.setDeleteStatus(false);
                }
                if (!EmptyUtil.isEmpty(warehouseSiteId)) {
                    Warehouse warehouseSite = new Warehouse();
                    warehouseSite.setId(warehouseSiteId);
                    list.add(cb.equal(root.get("warehouseSite"), warehouseSite));
                }

                if (EmptyUtil.isNotEmpty(startDate)) {
                    Date payStartDate = DateUtil.parseStrToDate(startDate, DateUtil.DATE_FORMAT_YYYY_MM_DD);
                    list.add(cb.greaterThanOrEqualTo(root.get("schedulingTime"), payStartDate));
                }
                if (EmptyUtil.isNotEmpty(endDate)) {
                    Date payEndDate = DateUtil.parseStrToDate(endDate, DateUtil.DATE_FORMAT_YYYY_MM_DD);
                    list.add(cb.lessThanOrEqualTo(root.get("schedulingTime"), payEndDate));
                }

                Class clazz = entity.getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);  //启用访问控制权限
                    try {
                        String name = field.getName();
                        Object value = field.get(entity);
                        Class<?> type = field.getType();
                        //拿到对象实例(t1)的 域成员的值
                        if (null != value
                                && !Collection.class.isAssignableFrom(type)) {
                            //不为为null值且不是集合的字段名加入查询
                            if ("name".equals(name)||"job".equals(name)) {
                                //模糊查询
                                list.add(cb.like(root.get(name), "%" + value + "%"));
                            } else {
                                list.add(cb.equal(root.get(name), value));
                            }
                        }
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
        Page<Scheduling> apply = this.schedulingRepository.findAll(specification,pageable);
        return new ResponseEntity<Page<Scheduling>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /scheduling/{id} 单个排班
     * @apiGroup scheduling
     * @apiVersion 0.0.1
     * @apiDescription 单个排班
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /scheduling/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} name 名称
     * @apiSuccess (200) {String} job 岗位
     * @apiSuccess (200) {Date} schedulingTime 当值时间
     * - yyyy-MM-dd
     * @apiSuccess (200) {DateTime} createTime 创建时间
     *
     * @apiSuccess (200) {Object} warehouseSiteData 仓库对象
     * @apiSuccess (200) {String} warehouseSiteData.id id
     * @apiSuccess (200) {String} warehouseSiteData.name 名称
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        Scheduling entity = this.schedulingRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Scheduling>(entity, HttpStatus.OK);
    }


    /** 
     * @api {POST} /scheduling 排班添加
     * @apiGroup scheduling
     * @apiVersion 0.0.1
     * @apiDescription 排班添加
     * @apiParam {String} name 名称(来自worker)
     * @apiParam {String} job 岗位(来自worker)
     * @apiParam {Date} schedulingTime 当值时间
     * - yyyy-MM-dd
     * @apiParam {String} warehouseSiteId 仓库Id(第一级)
     * @apiParamExample 请求明文：
     * /scheduling
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(Scheduling entity,String warehouseSiteId, String startTime, String endTime) throws java.text.ParseException {

        HttpStatusContent status = null;
        entity.setDeleteStatus(false);
        
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");    
        java.util.Date beginDate;
        java.util.Date endDate;
        beginDate = format.parse(startTime);
		endDate = format.parse(endTime);    
		day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);   
		 
		Calendar sTime = Calendar.getInstance();
		
        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        } 

        if(EmptyUtil.isEmpty(warehouseSiteId)){
            status = new HttpStatusContent(OutputState.FAIL, "缺少仓库参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Warehouse warehouseSite = this.warehouseRepository.findOne(warehouseSiteId);
        if(warehouseSite == null){
            status = new HttpStatusContent(OutputState.FAIL, "仓库不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(warehouseSite.getIsUse() == null
                || !warehouseSite.getIsUse()){
            status = new HttpStatusContent(OutputState.FAIL,
                    "仓库："+warehouseSite.getName() +",未启用");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
        entity.setWarehouseSite(warehouseSite);
        
        ObjectCopyUtil<Scheduling> uUtil = new ObjectCopyUtil<>();
        List<Scheduling> schedulings = new ArrayList<>();
        for (int i=0 ; i <= day ; i++) {
        		Scheduling obj = new Scheduling();
        		
        		uUtil.copyProperties(entity, obj);
	        	sTime.setTime(beginDate);
	    		sTime.add(Calendar.DAY_OF_MONTH,i);  
	    		
	    		obj.setSchedulingTime(sTime.getTime());
        		schedulings.add(obj);
        }
        
        schedulings = this.schedulingRepository.save(schedulings);
        if (schedulings == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {PUT} /scheduling/{id} 排班修改
     * @apiGroup scheduling
     * @apiVersion 0.0.1
     * @apiDescription 排班修改
     * @apiParam {String} id 主键id
     * @apiParam {String} name 名称(来自worker)
     * @apiParam {String} job 岗位(来自worker)
     * @apiParam {Date} schedulingTime 当值时间
     * - yyyy-MM-dd
     * @apiParam {String} [warehouseSiteId] 仓库Id(第一级)
     * @apiParamExample 请求明文：
     * /scheduling/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 Scheduling entity,
                                 String warehouseSiteId) {
        HttpStatusContent status = null;
        entity.setDeleteStatus(false);

        Scheduling scheduling = this.schedulingRepository.findOne(id);
        if (scheduling == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if(EmptyUtil.isNotEmpty(warehouseSiteId)){
            Warehouse warehouseSite = this.warehouseRepository.findOne(warehouseSiteId);
            if(warehouseSite == null){
                status = new HttpStatusContent(OutputState.FAIL, "仓库不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            scheduling.setWarehouseSite(warehouseSite);
        }

        ObjectCopyUtil<Scheduling> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, scheduling);
        entity = this.schedulingRepository.save(scheduling);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {DELETE} /scheduling/{id} 排班删除
     * @apiGroup scheduling
     * @apiVersion 0.0.1
     * @apiDescription 排班删除
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /scheduling/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @DeleteMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;

        Scheduling scheduling = this.schedulingRepository.findOne(id);
        if (scheduling == null ||
                (scheduling.getDeleteStatus() != null && scheduling.getDeleteStatus())) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        scheduling.setDeleteStatus(true);
        scheduling = this.schedulingRepository.save(scheduling);
        if (scheduling == null) {
            status = new HttpStatusContent(OutputState.FAIL, "失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }
}

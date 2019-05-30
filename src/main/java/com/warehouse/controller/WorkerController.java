package com.warehouse.controller;

import com.warehouse.model.Warehouse;
import com.warehouse.model.Worker;
import com.warehouse.service.repository.WarehouseRepository;
import com.warehouse.service.repository.WorkerRepository;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.OutputState;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 工作人员
 */
@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    /**
     * @api {GET} /worker 工作人员列表
     * @apiGroup worker
     * @apiVersion 0.0.1
     * @apiDescription 工作人员列表
     * @apiParam {String} [name] 名称(支持模糊查询)
     * @apiParam {String} [job] 岗位(支持模糊查询)
     * @apiParam {String} [warehouseSiteId] 仓库Id(第一级)
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
     * /worker?name=1111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} name 名称
     * @apiSuccess (200) {String} job 岗位
     * @apiSuccess (200) {DateTime} createTime 创建时间
     *
     * @apiSuccess (200) {Object} warehouseSiteData 仓库对象
     * @apiSuccess (200) {String} warehouseSiteData.id id
     * @apiSuccess (200) {String} warehouseSiteData.name 名称
     *
     * @apiSuccess (200) {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Worker>> findAll(Worker entity,
                                                    String warehouseSiteId,
                                                    @PageableDefault(value = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable) {

        Specification<Worker> specification = new Specification<Worker>() {
            @Override
            public Predicate toPredicate(Root<Worker> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if (!EmptyUtil.isEmpty(warehouseSiteId)) {
                    Warehouse warehouseSite = new Warehouse();
                    warehouseSite.setId(warehouseSiteId);
                    list.add(cb.equal(root.get("warehouseSite"), warehouseSite));
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
        Page<Worker> apply = this.workerRepository.findAll(specification,pageable);
        return new ResponseEntity<Page<Worker>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /worker/{id} 单个工作人员
     * @apiGroup worker
     * @apiVersion 0.0.1
     * @apiDescription 单个工作人员
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /worker/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} name 名称
     * @apiSuccess (200) {String} job 岗位
     * @apiSuccess (200) {DateTime} createTime 创建时间
     *
     * @apiSuccess (200) {Object} warehouseSiteData 仓库对象
     * @apiSuccess (200) {String} warehouseSiteData.id id
     * @apiSuccess (200) {String} warehouseSiteData.name 名称
     *
     * @apiSuccess (200) {Boolean} isUse 是否启用
     * - true:启用
     * - false:停用
     *
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        Worker entity = this.workerRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Worker>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /worker 工作人员添加
     * @apiGroup worker
     * @apiVersion 0.0.1
     * @apiDescription 工作人员添加
     * @apiParam {String} warehouseSiteId 仓库Id(第1级)
     * @apiParam {String} name 名称
     * @apiParam {String} job 岗位
     * - 调度员：负责货场调度
     * - 理货员：负责现场安排卸货库位
     * - 行车工：负责行车操作，吊装卸货物
     * - 装卸工：负责配合行车工吊装货物时套钢索之类的工作
     * - 叉车工：负责装卸货及整理库房时的移库工作及部分装卸车工作
     * - 计量员：在磅房负责各个行吊的重量记录
     * - 稽核员：负责进出库数据稽核（等同于仓库的库管）
     * - 换单/结算员：负责办理出库单据及费用结算收费等
     * @apiParam {Boolean} [isUse=true] 是否启用
     * - true:启用
     * - false:停用
     *
     * @apiParamExample 请求明文：
     * /worker
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(Worker entity,
                                  String warehouseSiteId) {

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

        if(EmptyUtil.isEmpty(warehouseSiteId)){
            status = new HttpStatusContent(OutputState.FAIL, "缺少仓库参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Warehouse warehouseSite = this.warehouseRepository.findOne(warehouseSiteId);
        if(warehouseSite == null){
            status = new HttpStatusContent(OutputState.FAIL, "仓库不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity.setWarehouseSite(warehouseSite);

        entity = this.workerRepository.save(entity);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {PUT} /worker/{id} 工作人员修改
     * @apiGroup worker
     * @apiVersion 0.0.1
     * @apiDescription 工作人员修改
     * @apiParam {String} id 主键id
     * @apiParam {String} warehouseSiteId 仓库Id(第1级)
     * @apiParam {String} name 名称
     * @apiParam {String} job 岗位
     * - 调度员：负责货场调度
     * - 理货员：负责现场安排卸货库位
     * - 行车工：负责行车操作，吊装卸货物
     * - 装卸工：负责配合行车工吊装货物时套钢索之类的工作
     * - 叉车工：负责装卸货及整理库房时的移库工作及部分装卸车工作
     * - 计量员：在磅房负责各个行吊的重量记录
     * - 稽核员：负责进出库数据稽核（等同于仓库的库管）
     * - 换单/结算员：负责办理出库单据及费用结算收费等
     * @apiParam {Boolean} [isUse] 是否启用
     * - true:启用
     * - false:停用
     * @apiParamExample 请求明文：
     * /worker/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 Worker entity,
                                 String warehouseSiteId) {
        HttpStatusContent status = null;

        Worker worker = this.workerRepository.findOne(id);
        if (worker == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if(EmptyUtil.isNotEmpty(warehouseSiteId)){
            Warehouse warehouseSite = this.warehouseRepository.findOne(warehouseSiteId);
            if(warehouseSite == null){
                status = new HttpStatusContent(OutputState.FAIL, "仓库不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            entity.setWarehouseSite(warehouseSite);
        }

        ObjectCopyUtil<Worker> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, worker);
        entity = this.workerRepository.save(worker);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }
}

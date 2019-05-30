//package com.warehouse.controller;
//
//import com.warehouse.model.*;
//import com.warehouse.service.repository.WarehouseRepository;
//import com.warehouse.util.HttpStatusContent;
//import com.warehouse.util.ValidatorUtil;
//import com.warehouse.util.enums.OutputState;
//import com.warehouse.util.tools.EmptyUtil;
//import com.warehouse.util.tools.ObjectCopyUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.*;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.lang.reflect.Field;
//import java.util.*;
//
///**
// * 仓库-管理
// */
//@RestController
//@RequestMapping("/warehouse")
//public class WarehouseController {
//
//    @Autowired
//    private WarehouseRepository warehouseRepository;
//
//    /**
//     * @api {GET} /warehouse 仓库列表
//     * @apiGroup warehouse
//     * @apiVersion 0.0.1
//     * @apiDescription 仓库列表
//     * @apiParam {String} [name] 仓库名称(支持模糊查询)
//     * @apiParam {String} [parentId] 仓库父级Id
//     * @apiParam {Boolean=true} [parentIsNull] 一级菜单是否为null
//     * @apiParam {Boolean} [isUse] 是否启用
//     * - true:启用
//     * - false:停用
//     *
//     * @apiParam {Integer} [page=0] 当前页
//     * @apiParam {Integer} [size=10] 每页数量
//     * @apiParam {String} [sort=id,asc] 排序
//     * - 格式： sort=id,desc 表示在按id由高到低排列
//     * - 格式： sort=id,asc 表示在按id由低到高排列
//     * @apiParamExample 请求明文：
//     * /warehouse
//     * @apiSuccess (200) {String} id  id主键
//     * @apiSuccess (200) {String} name 名称
//     * @apiSuccess (200) {String} province 省
//     * @apiSuccess (200) {String} city 市
//     * @apiSuccess (200) {String} area 区
//     * @apiSuccess (200) {String} regionCode 地区编号
//     * @apiSuccess (200) {BigDecimal} numMaxCapacity 件数最大容量(目前只对库位这一级)
//     * @apiSuccess (200) {BigDecimal} tonNumMaxCapacity 吨数最大容量(目前只对库位这一级)
//     * @apiSuccess (200) {Integer} levelOrder 层级顺序
//     *
//     * @apiSuccess (200) {Object} parentData 父级对象
//     * @apiSuccess (200) {String} parentData.id id
//     * @apiSuccess (200) {String} parentData.name 名称
//     * @apiSuccess (200) {List} child 子组织
//     *
//     * @apiSuccess (200) {Boolean} isUse 是否启用
//     * - true:启用
//     * - false:停用
//     * @apiSuccessExample {json} 成功:
//     * {}
//     */
//    @GetMapping(produces = "application/json")
//    public ResponseEntity<Page<Warehouse>> findAll(Warehouse entity,
//                                                   String parentId,
//                                                   Boolean parentIsNull,
//                                                   @PageableDefault(value = 10,
//                                                           sort = {"id"},
//                                                           direction = Sort.Direction.DESC)
//                                                           Pageable pageable) {
//        Specification<Warehouse> specification = new Specification<Warehouse>() {
//            @Override
//            public Predicate toPredicate(Root<Warehouse> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                List<Predicate> list = new ArrayList<Predicate>();
//                //一级菜单是否为null
//                if (parentIsNull != null && parentIsNull) {
//                    list.add(cb.isNull(root.get("parent")));
//                }
//
//                //父级Id
//                if (!EmptyUtil.isEmpty(parentId)) {
//                    Warehouse parent = new Warehouse();
//                    parent.setId(parentId);
//                    list.add(cb.equal(root.get("parent"), parent));
//                }
//
//                Class clazz = entity.getClass();
//                Field[] fields = clazz.getDeclaredFields();
//                for (Field field : fields) {
//                    field.setAccessible(true);  //启用访问控制权限
//                    try {
//                        String name = field.getName();
//                        Object value = field.get(entity);
//                        Class<?> type = field.getType();
//                        //拿到对象实例(t1)的 域成员的值
//                        if (null != value
//                                && !Collection.class.isAssignableFrom(type)) {
//                            //不为为null值且不是集合的字段名加入查询
//                            if ("name".equals(name)) {
//                                //模糊查询
//                                list.add(cb.like(root.get(name), "%" + value + "%"));
//                            } else {
//                                list.add(cb.equal(root.get(name), value));
//                            }
//                        }
//                    } catch (IllegalArgumentException | IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                Predicate[] p = new Predicate[list.size()];
//                return cb.and(list.toArray(p));
//            }
//        };
//        Page<Warehouse> apply = this.warehouseRepository.findAll(specification,pageable);
//        return new ResponseEntity<Page<Warehouse>>(apply, HttpStatus.OK);
//    }
//
//    /**
//     * @api {GET} /warehouse/{id} 仓库详细
//     * @apiGroup warehouse
//     * @apiVersion 0.0.1
//     * @apiDescription 仓库详细
//     * @apiParam {String} id 主键id
//     * @apiParamExample 请求明文：
//     * /warehouse/1
//     * @apiSuccess (200) {String} id  id主键
//     * @apiSuccess (200) {String} name 名称
//     * @apiSuccess (200) {String} province 省
//     * @apiSuccess (200) {String} city 市
//     * @apiSuccess (200) {String} area 区
//     * @apiSuccess (200) {String} regionCode 地区编号
//     * @apiSuccess (200) {BigDecimal} numMaxCapacity 件数最大容量(目前只对库位这一级)
//     * @apiSuccess (200) {BigDecimal} tonNumMaxCapacity 吨数最大容量(目前只对库位这一级)
//     * @apiSuccess (200) {Integer} levelOrder 层级顺序
//     *
//     * @apiSuccess (200) {Object} parentData 父级对象
//     * @apiSuccess (200) {String} parentData.id id
//     * @apiSuccess (200) {String} parentData.name 名称
//     * @apiSuccess (200) {List} child 子组织
//     *
//     * @apiSuccess (200) {Boolean} isUse 是否启用
//     * - true:启用
//     * - false:停用
//     * @apiSuccessExample {json} 成功:
//     * {}
//     */
//    @GetMapping(produces = "application/json", value = "/{id}")
//    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
//        HttpStatusContent status = null;
//        Warehouse entity = this.warehouseRepository.findOne(id);
//        if (entity == null) {
//            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
//            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(entity, HttpStatus.OK);
//    }
//
//    /**
//     * @api {POST} /warehouse 仓库添加
//     * @apiGroup warehouse
//     * @apiVersion 0.0.1
//     * @apiDescription 仓库添加
//     * @apiParam (200) {String} [parentId] 仓库父Id
//     * @apiParam (200) {String} name 名称
//     * @apiParam (200) {String} [province] 省(添加一级)
//     * @apiParam (200) {String} [city] 市(添加一级)
//     * @apiParam (200) {String} [area] 区(添加一级)
//     * @apiParam (200) {String} [regionCode] 地区编号(添加一级)
//     * @apiParam (200) {BigDecimal} [numMaxCapacity] 件数最大容量(目前只对库位这一级)
//     * @apiParam (200) {BigDecimal} [tonNumMaxCapacity] 吨数最大容量(目前只对库位这一级)
//     * @apiParam (200) {Boolean} [isUse=true] 是否启用
//     * - true:启用
//     * - false:停用
//     *
//     * @apiParamExample 请求明文：
//     * /warehouse
//     * @apiSuccess (200) {String} id 主键id
//     * @apiSuccessExample {json} 成功:
//     * {"message":"成功"}
//     * @apiError (500) {String} message 信息
//     * @apiErrorExample {json} 失败:
//     * {"message":"失败"}
//     */
//    @PostMapping(produces = "application/json")
//    public ResponseEntity<?> post(Warehouse entity, String parentId) {
//        HttpStatusContent status = null;
//        if(entity.getIsUse() == null){
//            //null时设为true
//            entity.setIsUse(true);
//        }
//        entity.setLockState(false);//默认未锁定
//
//        //验证实体必填字段
//        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
//        if (validate != null) {
//            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
//            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        if (EmptyUtil.isNotEmpty(parentId)) {
//            Warehouse warehouse = this.warehouseRepository.findOne(parentId);
//            if (warehouse == null) {
//                status = new HttpStatusContent(OutputState.FAIL, "未找到相关的仓库信息！");
//                return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            entity.setParent(warehouse);
//            int i = warehouse.getLevelOrder() + 1;
//            if(i>4){
//                status = new HttpStatusContent(OutputState.FAIL, "最多添加到四级！");
//                return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            entity.setLevelOrder(i);
//
//        }else {
//            if (EmptyUtil.isEmpty(entity.getProvince())
//                    || EmptyUtil.isEmpty(entity.getCity())
//                    || EmptyUtil.isEmpty(entity.getArea())) {
//                status = new HttpStatusContent(OutputState.FAIL, "一级仓库省市区必填！");
//                return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            entity.setLevelOrder(1);//第1级
//        }
//
//        entity = this.warehouseRepository.save(entity);
//        if (entity == null) {
//            status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
//            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        status = new HttpStatusContent(OutputState.SUCCESS);
//        return new ResponseEntity<>(status, HttpStatus.OK);
//    }
//
//    /**
//     * @api {PUT} /warehouse 仓库修改
//     * @apiGroup warehouse
//     * @apiVersion 0.0.1
//     * @apiDescription 仓库修改
//     * @apiParam (200) {String} id 仓库Id
//     * @apiParam (200) {String} [parentId] 仓库父Id
//     * @apiParam (200) {String} [name] 仓库名称
//     * @apiParam (200) {String} [province] 省
//     * @apiParam (200) {String} [city] 市
//     * @apiParam (200) {String} [area] 区
//     * @apiParam (200) {String} [regionCode] 地区编号
//     * @apiParam (200) {BigDecimal} [numMaxCapacity] 件数最大容量(目前只对库位这一级)
//     * @apiParam (200) {BigDecimal} [tonNumMaxCapacity] 吨数最大容量(目前只对库位这一级)
//     * @apiParam (200) {Boolean} [isUse] 是否启用
//     * - true:启用
//     * - false:停用
//     *
//     * @apiParamExample 请求明文：
//     * /warehouse
//     * @apiSuccess (200) {String} id 主键id
//     * @apiSuccessExample {json} 成功:
//     * {"message":"成功"}
//     * @apiError (500) {String} message 信息
//     * @apiErrorExample {json} 失败:
//     * {"message":"失败"}
//     */
//    @PutMapping(produces = "application/json", value = "/{id}")
//    public ResponseEntity<?> put(@PathVariable(value = "id") String id, String parentId, Warehouse entity) {
//
//        HttpStatusContent status = null;
//
//        Warehouse warehouse = this.warehouseRepository.getOne(id);
//        if (warehouse == null) {
//            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
//            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
//        }
//
//        if (EmptyUtil.isNotEmpty(parentId)) {
//            Warehouse pWarehouse = this.warehouseRepository.findOne(parentId);
//            if (pWarehouse == null) {
//                status = new HttpStatusContent(OutputState.FAIL, "未找到相关的仓库信息！");
//                return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            entity.setParent(pWarehouse);
//            int i = pWarehouse.getLevelOrder() + 1;
//            if(i>4){
//                status = new HttpStatusContent(OutputState.FAIL, "最多添加到四级！");
//                return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            entity.setLevelOrder(i);
//        }
//
//        ObjectCopyUtil<Warehouse> uUtil = new ObjectCopyUtil<>();
//        //同一类实体之间的属性复制
//        uUtil.copyProperties(entity, warehouse);
//        entity = this.warehouseRepository.save(warehouse);
//        if (entity == null) {
//            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
//            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        status = new HttpStatusContent(OutputState.SUCCESS);
//        return new ResponseEntity<>(status, HttpStatus.OK);
//    }
//
//    /**
//     * @api {POST} /warehouse/lock 库位锁定/解锁
//     * @apiGroup warehouse
//     * @apiVersion 0.0.1
//     * @apiDescription 库位锁定/解锁
//     * @apiParam {String} id 主键id
//     * @apiParam {Boolean} opType 操作类型
//     * - true:锁定
//     * - false:解锁
//     * @apiParam {String} remark 备注
//     * @apiParamExample 请求明文：
//     * /warehouse/lock
//     * @apiSuccess (200) {String} message 信息
//     * @apiSuccessExample {json} 成功:
//     * {"message":"成功"}
//     * @apiError (500) {String} message 信息
//     * @apiErrorExample {json} 失败:
//     * {"message":"失败"}
//     */
//    @PostMapping(produces = "application/json", value = "/lock")
//    public ResponseEntity<?> bind(String id,
//                                  String createMan,
//                                  String createManId,
//                                  Boolean opType,
//                                  String remark) {
//        HttpStatusContent status = null;
//
//        if (EmptyUtil.isEmpty(id)) {
//            status = new HttpStatusContent(OutputState.FAIL, "缺少参数id！");
//            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
//        }
//
//        if (EmptyUtil.isEmpty(opType)) {
//            status = new HttpStatusContent(OutputState.FAIL, "缺少操作类型！");
//            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
//        }
//
//        if (EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)) {
//            status = new HttpStatusContent(OutputState.FAIL, "操作人和操作人Id必传！");
//            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
//        }
//
//        Warehouse warehouse = this.warehouseRepository.findOne(id);
//        if (warehouse == null) {
//            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
//            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
//        }
//
//        //都要写日志
//        WarehouseLockLog log = new WarehouseLockLog();
//        log.setWarehouse(warehouse);
//        log.setCreateMan(createMan);
//        log.setCreateManId(createManId);
//        log.setOpType(opType);//操作类型
//        log.setRemark(remark);
//
//        if(opType){
//            //锁定
//            if(warehouse.getLockState()!=null
//                    && warehouse.getLockState()){
//                status = new HttpStatusContent(OutputState.FAIL, "再次锁定请先解锁！");
//                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            warehouse.setLockState(true);
//        }else {
//            //解锁
//            if(warehouse.getLockState()==null
//                    || !warehouse.getLockState()){
//                status = new HttpStatusContent(OutputState.FAIL, "未锁定无法解锁！");
//                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            if(EmptyUtil.isEmpty(remark)){
//                status = new HttpStatusContent(OutputState.FAIL, "解锁必须填写备注！");
//                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            warehouse.setLockState(false);
//        }
//        //修改时，通过关联添加日志
//        Set<WarehouseLockLog> logSet = new HashSet<>();
//        logSet.add(log);
//        warehouse.setWarehouseLockLogSet(logSet);
//
//        warehouse = this.warehouseRepository.save(warehouse);
//        if (warehouse == null) {
//            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
//            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        status = new HttpStatusContent(OutputState.SUCCESS);
//        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
//    }
//}

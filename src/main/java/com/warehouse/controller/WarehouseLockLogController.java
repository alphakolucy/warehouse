//package com.warehouse.controller;
//
//import com.warehouse.model.Warehouse;
//import com.warehouse.model.WarehouseLockLog;
//import com.warehouse.service.repository.WarehouseLockLogRepository;
//import com.warehouse.util.tools.EmptyUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 仓库锁定日志(针对库位)
// */
//@RestController
//@RequestMapping("/warehouseLockLog")
//public class WarehouseLockLogController {
//
//    @Autowired
//    private WarehouseLockLogRepository craneBindSteelyardLogRepository;
//
//    /**
//     * @api {GET} /warehouseLockLog 仓库锁定日志(针对库位)列表
//     * @apiGroup warehouseLockLog
//     * @apiVersion 0.0.1
//     * @apiDescription 仓库锁定日志(针对库位)列表
//     * @apiParam {String} [warehouseId] 库位Id
//     * @apiParam {Boolean} [opType] 操作类型
//     * - true:锁定
//     * - false:解锁
//     *
//     * @apiParam {Integer} [page=0] 当前页
//     * @apiParam {Integer} [size=10] 每页数量
//     * @apiParam {String} [sort=createTime,asc] 排序
//     * - 格式： sort=createTime,desc 表示在按createTime由高到低排列
//     * - 格式： sort=createTime,asc 表示在按createTime由低到高排列
//     * @apiParamExample 请求明文：
//     * /warehouseLockLog
//     * @apiSuccess (200) {String} id  id主键
//     * @apiSuccess (200) {String} createMan 操作人
//     * @apiSuccess (200) {String} createManId 操作人
//     * @apiSuccess (200) {DateTime} createTime 创建时间
//     * - 格式：yyyy-MM-dd HH:mm:ss
//     * @apiSuccess (200) {String} remark 备注
//     * @apiSuccess (200) {Boolean} opType 操作类型
//     * @apiSuccess (200) {String} opTypeTxt 操作类型Txt
//     *
//     * @apiSuccess (200) {Object} warehouseData 库位对象
//     * @apiSuccess (200) {String} warehouseData.id id
//     * @apiSuccess (200) {String} warehouseData.name 名称
//     *
//     * @apiSuccessExample {json} 成功:
//     * {}
//     */
//    @GetMapping(produces = "application/json")
//    public ResponseEntity<Page<WarehouseLockLog>> findAll(WarehouseLockLog entity,
//                                                        String warehouseId,
//                                                        @PageableDefault(value = 10, sort = {"createTime"},
//                                                                direction = Sort.Direction.DESC)
//                                                                Pageable pageable) {
//
//        //库位
//        if (EmptyUtil.isNotEmpty(warehouseId)) {
//            Warehouse warehouse = new Warehouse();
//            warehouse.setId(warehouseId);
//            entity.setWarehouse(warehouse);
//        }
//
//        Example<WarehouseLockLog> example = Example.of(entity);
//        Page<WarehouseLockLog> apply = this.craneBindSteelyardLogRepository.findAll(example, pageable);
//        return new ResponseEntity<Page<WarehouseLockLog>>(apply, HttpStatus.OK);
//    }
//}

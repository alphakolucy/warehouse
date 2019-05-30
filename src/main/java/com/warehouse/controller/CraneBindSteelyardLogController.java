package com.warehouse.controller;

import com.warehouse.model.Crane;
import com.warehouse.model.CraneBindSteelyardLog;
import com.warehouse.model.Steelyard;
import com.warehouse.service.repository.CraneBindSteelyardLogRepository;
import com.warehouse.util.tools.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 龙门吊绑秤日志
 */
@RestController
@RequestMapping("/craneBindSteelyardLog")
public class CraneBindSteelyardLogController {

    @Autowired
    private CraneBindSteelyardLogRepository craneBindSteelyardLogRepository;

    /**
     * @api {GET} /craneBindSteelyardLog 龙门吊绑秤日志列表
     * @apiGroup craneBindSteelyardLog
     * @apiVersion 0.0.1
     * @apiDescription 龙门吊绑秤日志列表
     * @apiParam {String} [craneId] 龙门吊Id
     * @apiParam {Boolean} [opType] 操作类型
     * - true:绑定
     * - false:解绑
     *
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=createTime,asc] 排序
     * - 格式： sort=createTime,desc 表示在按createTime由高到低排列
     * - 格式： sort=createTime,asc 表示在按createTime由低到高排列
     * @apiParamExample 请求明文：
     * /craneBindSteelyardLog
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} createMan 操作人
     * @apiSuccess (200) {String} createManId 操作人
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * - 格式：yyyy-MM-dd HH:mm:ss
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {Boolean} opType 操作类型
     * @apiSuccess (200) {String} opTypeTxt 操作类型Txt
     *
     * @apiSuccess (200) {Object} steelyardData 秤对象
     * @apiSuccess (200) {String} steelyardData.id id
     * @apiSuccess (200) {String} steelyardData.steelyardNo 编号
     *
     * @apiSuccess (200) {Object} craneData 龙门吊对象
     * @apiSuccess (200) {String} craneData.id id
     * @apiSuccess (200) {String} craneData.craneNo 编号
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<CraneBindSteelyardLog>> findAll(CraneBindSteelyardLog entity,
                                                        String craneId,
                                                        @PageableDefault(value = 10, sort = {"createTime"},
                                                                direction = Sort.Direction.DESC)
                                                                Pageable pageable) {

        //龙门吊
        if (EmptyUtil.isNotEmpty(craneId)) {
            Crane crane = new Crane();
            crane.setId(craneId);
            entity.setCrane(crane);
        }

        Example<CraneBindSteelyardLog> example = Example.of(entity);
        Page<CraneBindSteelyardLog> apply = this.craneBindSteelyardLogRepository.findAll(example, pageable);
        return new ResponseEntity<Page<CraneBindSteelyardLog>>(apply, HttpStatus.OK);
    }
}

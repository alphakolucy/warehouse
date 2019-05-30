package com.warehouse.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.warehouse.model.WarehouseStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 仓库库存 Service
 */
public interface WarehouseStockService {


    /**
     * 保存或更新库存
     *
     * @param warehouseStocks 库存实体 俱体操作数定义新实体  如果是减则传负数getNum().negate()
     * @param opOrderNo       操作单号 对应相应的单号
     * @param opOrderId       操作单号ID 对应相应的单号ID
     * @param opType          操作类型  收货 提货 调整 盘库 移库 过户
     * @return
     */
    Boolean saveOrUpdate(List<WarehouseStock> warehouseStocks, String opOrderNo, String opOrderId, Integer opType);

    /**
     * 统计，商品在委托单某种状态下明细里每中物资的占用件数和占用吨数。
     * @param orderStates 状态列表(默认查询委托单新建和确认)
     * @param productId 物资Id（null或者"",就查所有)非必填
     * @param productName 物资名称(支持模糊查询)非必填
     * @param customerId 客户Id（null或者"",就查所有)非必填
     * @param pageable 分页信息
     * @return Page<JSONObject>
     */
    Page<JSONObject> sumProductByOrderSates(List<Integer> orderStates,
                                                 String productId,
                                                 String productName,
                                                 String warehouseId,
                                                 String customerId,
                                                String spec,
                                                String model,
                                                String material,
                                                String maker,
                                                 Pageable pageable);

    /**
     * @param warehouseSiteId 仓库
     * @param warehouseAreaId 库区
     * @param warehouseId 库位
     * @param warehouseLevelId 库层
     * @param productId 物资
     * @param customerId 客户
     * @return map stockSumNum:件数合计 stockSumTonNum:吨数合计
     */
    Map<String,BigDecimal> getStockNumAndTon(String warehouseSiteId,
                                             String warehouseAreaId,
                                             String warehouseId,
                                             String warehouseLevelId,
                                             String productId,
                                             String customerId);

    /**
     * 合计数量
     * @param orderStates 状态列表
     * @param productId 物资Id（null或者"",就查所有)非必填
     * @param productName 物资名称(支持模糊查询)非必填
     * @param warehouseId 仓库id
     * @param customerId 客户id
     * @return map
     */
    Map<String,Object> totalNumByOrderSates(List<Integer> orderStates,
                                  String productId,
                                  String productName,
                                  String warehouseId,
                                  String customerId,
                                String spec,
                                String model,
                                String material,
                                String maker);

    /**
     * 合计数量(不要客户，按仓库、库区、库位、物资分组)
     * @param productId 物资Id（null或者"",就查所有)非必填
     * @param warehouseSiteId 仓库id
     * @param warehouseAreaId 库区id
     * @param warehouseId 库位id
     * @param warehouseLevelId 库层id
     */
    Page<JSONObject> sumByNoCustomer(String productId,
                                   String warehouseSiteId,
                                   String warehouseAreaId,
                                   String warehouseId,
                                   String warehouseLevelId,
                                   Pageable pageable
    );
}

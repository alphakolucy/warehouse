package com.warehouse.service.repository;

import com.warehouse.model.WarehouseStock;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseStockRepository extends WiselyRepository<WarehouseStock, String> {

    /**
     * 统计，商品在委托单某种状态下明细里每中物资的占用件数和占用吨数。
     * @param orderStates 状态列表
     * @param productId 物资Id（null或者"",就查所有)非必填
     * @param productName 物资名称(支持模糊查询)非必填
     * @param warehouseId 仓库id
     * @param customerId 客户id
     * @param spec 规格(支持模糊查询)非必填
     * @param model 型号(支持模糊查询)非必填
     * @param material 材料(支持模糊查询)非必填
     * @param maker 厂家(支持模糊查询)非必填
     * @param pageable 分页信息
     * @return Page<Object[]>
     */

    @Query(value = "SELECT t2.p_id as p_id,"+
            "t2.product_name as product_name,"+
            "ifnull(t.sum_num,0) as sum_num,"+
            "ifnull(t.sum_ton_num,0) as sum_ton_num,"+
            "t2.w_id as warehouse_id,"+
            "t2.w_name as warehouse_name,"+
            "t2.c_id as customer_id,"+
            "t2.c_name as customer_name,"+
            "t2.spec as spec,"+
            "t2.model as model,"+
            "t2.material as material,"+
            "t2.maker as maker,"+
            "t2.unit as unit,"+
            "t2.p_cate_id as cate_id,"+
            "t2.p_cate_name as product_cate_name,"+
            "ifnull(t2.stock_sum_num,0) as stock_sum_num,"+
            "ifnull(t2.stock_sum_ton_num,0) as stock_sum_ton_num,"+
            "ifnull(t3.lock_sum_num,0) as lock_sum_num,"+
            "ifnull(t3.lock_sum_ton_num,0) as lock_sum_ton_num,"+
            "t2.p_rationale as p_rationale,"+
            "ifnull(t2.stock_sum_num,0)-ifnull(t.sum_num,0)-ifnull(t3.lock_sum_num,0) as can_use_num,"+
            "ifnull(t2.stock_sum_ton_num,0)-ifnull(t.sum_ton_num,0)-ifnull(t3.lock_sum_ton_num,0) as can_use_ton_num "+
            "from "+
            "(select "+
            "tdeod.product_id as p_id,"+
            "tdp1.product_name as product_name,"+
            "tdp1.spec as spec,"+
            "tdp1.model as model,"+
            "tdp1.material as material,"+
            "tdp1.maker as maker,"+
            "tdp1.unit as unit,"+
            "tdpc.id as p_cate_id,"+
            "tdpc.product_cate_name as p_cate_name,"+
            "sum(tdeod.num) as sum_num,"+
            "sum(tdeod.ton_num) as sum_ton_num,"+
            "tdreod.warehouse_id as w_id,"+
            "tdware.`name` as w_name,"+
            "tdreod.customer_id as c_id," +
            "tdc.`name` as c_name,"+
            "tdp1.rationale as p_rationale "+
            "from td_product tdp1 "+
            "inner join td_entrust_order_detail tdeod "+
            "on tdp1.id = tdeod.product_id "+
            "INNER JOIN td_receipt_entrust_order tdreod "+
            "on tdreod.id = tdeod.entrust_order_id "+
            "INNER JOIN td_warehouse tdware "+
            "on tdware.id = tdreod.warehouse_id "+
            "INNER JOIN td_customer tdc " +
            "on tdc.id = tdreod.customer_id "+
            "INNER JOIN td_product_cates tdpc " +
            "on tdpc.id = tdp1.cate_id "+
            "WHERE tdreod.order_state in(?1) "+
            "and tdreod.order_type in(60020,60030) "+
            "GROUP BY tdreod.warehouse_id,tdreod.customer_id,tdeod.product_id)t "+
            "RIGHT JOIN "+
            "(select "+
            "tdws.product_id as p_id,"+
            "tdp2.product_name as product_name,"+
            "tdp2.spec as spec,"+
            "tdp2.model as model,"+
            "tdp2.material as material,"+
            "tdp2.maker as maker,"+
            "tdp2.unit as unit,"+
            "tdpc.id as p_cate_id,"+
            "tdpc.product_cate_name as p_cate_name,"+
            "sum(tdws.num) as stock_sum_num,"+
            "sum(tdws.ton_num) as stock_sum_ton_num,"+
            "tdws.warehouse_site_id as w_id,"+
            "tdware.`name` as w_name,"+
            "tdws.customer_id as c_id,"+
            "tdc.`name` as c_name,"+
            "tdp2.rationale as p_rationale "+
            "from td_product tdp2 "+
            "inner join td_warehouse_stock tdws "+
            "on tdp2.id = tdws.product_id "+
            "INNER JOIN td_warehouse tdware "+
            "on tdware.id = tdws.warehouse_site_id "+
            "INNER JOIN td_customer tdc "+
            "on tdc.id = tdws.customer_id "+
            "INNER JOIN td_product_cates tdpc "+
            "on tdpc.id = tdp2.cate_id "+
            "where "+
            "IF(?4 is not null,if (?4 != '', tdws.warehouse_site_id=?4, 1=1),1=1) " +
            "and IF(?5 is not null,if (?5 != '', tdws.customer_id=?5, 1=1),1=1) " +
            "and IF(?2 is not null,if (?2 != '', tdp2.id=?2, 1=1),1=1) " +
            "and IF(?3 is not null,if (?3 != '', tdp2.product_name like CONCAT('%',?3,'%'), 1=1),1=1) " +
            "and IF(?6 is not null,if (?6 != '', tdp2.spec like CONCAT('%',?6,'%'), 1=1),1=1) " +
            "and IF(?7 is not null,if (?7 != '', tdp2.model like CONCAT('%',?7,'%'), 1=1),1=1) " +
            "and IF(?8 is not null,if (?8 != '', tdp2.material like CONCAT('%',?8,'%'), 1=1),1=1) " +
            "and IF(?9 is not null,if (?9 != '', tdp2.maker like CONCAT('%',?9,'%'), 1=1),1=1) " +
            "GROUP BY tdws.product_id,tdws.warehouse_site_id,tdws.customer_id)t2 "+
            "on t.p_id=t2.p_id and t.w_id=t2.w_id and t.c_id=t2.c_id "+
            "LEFT JOIN "+
            "(select "+
            "tdlod.product_id as p_id,"+
            "tdp1.product_name as product_name,"+
            "tdpc.id as p_cate_id,"+
            "tdpc.product_cate_name as p_cate_name,"+
            "sum(tdlod.num) as lock_sum_num,"+
            "sum(tdlod.ton_num) as lock_sum_ton_num,"+
            "tdlso.warehouse_id as w_id,"+
            "tdware.`name` as w_name,"+
            "tdlso.customer_id as c_id,"+
            "tdc.`name` as c_name,"+
            "tdp1.rationale as p_rationale "+
            "from td_product tdp1 "+
            "inner join td_lock_order_detail tdlod "+
            "on tdp1.id = tdlod.product_id "+
            "INNER JOIN td_lock_stock_order tdlso "+
            "on tdlso.id = tdlod.lock_stock_order_id "+
            "INNER JOIN td_warehouse tdware "+
            "on tdware.id = tdlso.warehouse_id "+
            "INNER JOIN td_customer tdc "+
            "on tdc.id = tdlso.customer_id "+
            "INNER JOIN td_product_cates tdpc "+
            "on tdpc.id = tdp1.cate_id "+
            "where tdlso.order_state = 16020 "+
            "GROUP BY tdlod.product_id,tdlso.warehouse_id,tdlso.customer_id)t3 "+
            "on t3.p_id=t2.p_id and t3.w_id=t2.w_id and t3.c_id=t2.c_id "+
            "ORDER BY ?#{#pageable}",
            countQuery = "SELECT count(*) " +
                    "from "+
                    "(select "+
                    "tdeod.product_id as p_id,"+
                    "tdp1.product_name as product_name,"+
                    "tdp1.spec as spec,"+
                    "tdp1.model as model,"+
                    "tdp1.material as material,"+
                    "tdp1.maker as maker,"+
                    "tdp1.unit as unit,"+
                    "tdpc.id as p_cate_id,"+
                    "tdpc.product_cate_name as p_cate_name,"+
                    "sum(tdeod.num) as sum_num,"+
                    "sum(tdeod.ton_num) as sum_ton_num,"+
                    "tdreod.warehouse_id as w_id,"+
                    "tdware.`name` as w_name,"+
                    "tdreod.customer_id as c_id," +
                    "tdc.`name` as c_name,"+
                    "tdp1.rationale as p_rationale "+
                    "from td_product tdp1 "+
                    "inner join td_entrust_order_detail tdeod "+
                    "on tdp1.id = tdeod.product_id "+
                    "INNER JOIN td_receipt_entrust_order tdreod "+
                    "on tdreod.id = tdeod.entrust_order_id "+
                    "INNER JOIN td_warehouse tdware "+
                    "on tdware.id = tdreod.warehouse_id "+
                    "INNER JOIN td_customer tdc " +
                    "on tdc.id = tdreod.customer_id "+
                    "INNER JOIN td_product_cates tdpc " +
                    "on tdpc.id = tdp1.cate_id "+
                    "WHERE tdreod.order_state in(?1) "+
                    "and tdreod.order_type in(60020,60030) "+
                    "GROUP BY tdreod.warehouse_id,tdreod.customer_id,tdeod.product_id)t "+
                    "RIGHT JOIN "+
                    "(select "+
                    "tdws.product_id as p_id,"+
                    "tdp2.product_name as product_name,"+
                    "tdp2.spec as spec,"+
                    "tdp2.model as model,"+
                    "tdp2.material as material,"+
                    "tdp2.maker as maker,"+
                    "tdp2.unit as unit,"+
                    "tdpc.id as p_cate_id,"+
                    "tdpc.product_cate_name as p_cate_name,"+
                    "sum(tdws.num) as stock_sum_num,"+
                    "sum(tdws.ton_num) as stock_sum_ton_num,"+
                    "tdws.warehouse_site_id as w_id,"+
                    "tdware.`name` as w_name,"+
                    "tdws.customer_id as c_id,"+
                    "tdc.`name` as c_name,"+
                    "tdp2.rationale as p_rationale "+
                    "from td_product tdp2 "+
                    "inner join td_warehouse_stock tdws "+
                    "on tdp2.id = tdws.product_id "+
                    "INNER JOIN td_warehouse tdware "+
                    "on tdware.id = tdws.warehouse_site_id "+
                    "INNER JOIN td_customer tdc "+
                    "on tdc.id = tdws.customer_id "+
                    "INNER JOIN td_product_cates tdpc "+
                    "on tdpc.id = tdp2.cate_id "+
                    "where "+
                    "IF(?4 is not null,if (?4 != '', tdws.warehouse_site_id=?4, 1=1),1=1) " +
                    "and IF(?5 is not null,if (?5 != '', tdws.customer_id=?5, 1=1),1=1) " +
                    "and IF(?2 is not null,if (?2 != '', tdp2.id=?2, 1=1),1=1) " +
                    "and IF(?3 is not null,if (?3 != '', tdp2.product_name like CONCAT('%',?3,'%'), 1=1),1=1) " +
                    "and IF(?6 is not null,if (?6 != '', tdp2.spec like CONCAT('%',?6,'%'), 1=1),1=1) " +
                    "and IF(?7 is not null,if (?7 != '', tdp2.model like CONCAT('%',?7,'%'), 1=1),1=1) " +
                    "and IF(?8 is not null,if (?8 != '', tdp2.material like CONCAT('%',?8,'%'), 1=1),1=1) " +
                    "and IF(?9 is not null,if (?9 != '', tdp2.maker like CONCAT('%',?9,'%'), 1=1),1=1) " +
                    "GROUP BY tdws.product_id,tdws.warehouse_site_id,tdws.customer_id)t2 "+
                    "on t.p_id=t2.p_id and t.w_id=t2.w_id and t.c_id=t2.c_id "+
                    "LEFT JOIN "+
                    "(select "+
                    "tdlod.product_id as p_id,"+
                    "tdp1.product_name as product_name,"+
                    "tdpc.id as p_cate_id,"+
                    "tdpc.product_cate_name as p_cate_name,"+
                    "sum(tdlod.num) as lock_sum_num,"+
                    "sum(tdlod.ton_num) as lock_sum_ton_num,"+
                    "tdlso.warehouse_id as w_id,"+
                    "tdware.`name` as w_name,"+
                    "tdlso.customer_id as c_id,"+
                    "tdc.`name` as c_name,"+
                    "tdp1.rationale as p_rationale "+
                    "from td_product tdp1 "+
                    "inner join td_lock_order_detail tdlod "+
                    "on tdp1.id = tdlod.product_id "+
                    "INNER JOIN td_lock_stock_order tdlso "+
                    "on tdlso.id = tdlod.lock_stock_order_id "+
                    "INNER JOIN td_warehouse tdware "+
                    "on tdware.id = tdlso.warehouse_id "+
                    "INNER JOIN td_customer tdc "+
                    "on tdc.id = tdlso.customer_id "+
                    "INNER JOIN td_product_cates tdpc "+
                    "on tdpc.id = tdp1.cate_id "+
                    "where tdlso.order_state = 16020 "+
                    "GROUP BY tdlod.product_id,tdlso.warehouse_id,tdlso.customer_id)t3 "+
                    "on t3.p_id=t2.p_id and t3.w_id=t2.w_id and t3.c_id=t2.c_id "+
                    "ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<Object[]> sumProductByOrderSates(List<Integer> orderStates,
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
     * 合计数量(只有一行数据，也要用列表接受)
     * @param orderStates 状态列表
     * @param productId 物资Id（null或者"",就查所有)非必填
     * @param productName 物资名称(支持模糊查询)非必填
     * @param warehouseId 仓库id
     * @param customerId 客户id
     * @param spec 规格(支持模糊查询)非必填
     * @param model 型号(支持模糊查询)非必填
     * @param material 材料(支持模糊查询)非必填
     * @param maker 厂家(支持模糊查询)非必填
     * @return List<Object[]>
     */
    @Query(value =
            "SELECT sum(t4.sum_num) as occupy_num_total,"+
            "sum(t4.sum_ton_num) as occupy_ton_total,"+
            "sum(t4.stock_sum_num) as stock_num_total,"+
            "sum(t4.stock_sum_ton_num) as stock_ton_total,"+
            "sum(t4.lock_sum_num) as lock_num_total,"+
            "sum(t4.lock_sum_ton_num) as lock_ton_total,"+
            "sum(t4.can_use_num) as can_num_total,"+
            "sum(t4.can_use_ton_num) as can_ton_total "+
            "from "+
            "(SELECT "+
            "ifnull(t.sum_num,0) as sum_num,"+
            "ifnull(t.sum_ton_num,0) as sum_ton_num,"+
            "ifnull(t2.stock_sum_num,0) as stock_sum_num,"+
            "ifnull(t2.stock_sum_ton_num,0) as stock_sum_ton_num,"+
            "ifnull(t3.lock_sum_num,0) as lock_sum_num,"+
            "ifnull(t3.lock_sum_ton_num,0) as lock_sum_ton_num,"+
            "ifnull(t2.stock_sum_num,0)-ifnull(t.sum_num,0)-ifnull(t3.lock_sum_num,0) as can_use_num,"+
            "ifnull(t2.stock_sum_ton_num,0)-ifnull(t.sum_ton_num,0)-ifnull(t3.lock_sum_ton_num,0) as can_use_ton_num "+
            "from "+
            "(select "+
            "tdeod.product_id as p_id,"+
            "tdp1.product_name as product_name,"+
            "tdp1.spec as spec,"+
            "tdp1.model as model,"+
            "tdp1.material as material,"+
            "tdp1.maker as maker,"+
            "tdp1.unit as unit,"+
            "tdpc.id as p_cate_id,"+
            "tdpc.product_cate_name as p_cate_name,"+
            "sum(tdeod.num) as sum_num,"+
            "sum(tdeod.ton_num) as sum_ton_num,"+
            "tdreod.warehouse_id as w_id,"+
            "tdware.`name` as w_name,"+
            "tdreod.customer_id as c_id," +
            "tdc.`name` as c_name,"+
            "tdp1.rationale as p_rationale "+
            "from td_product tdp1 "+
            "inner join td_entrust_order_detail tdeod "+
            "on tdp1.id = tdeod.product_id "+
            "INNER JOIN td_receipt_entrust_order tdreod "+
            "on tdreod.id = tdeod.entrust_order_id "+
            "INNER JOIN td_warehouse tdware "+
            "on tdware.id = tdreod.warehouse_id "+
            "INNER JOIN td_customer tdc " +
            "on tdc.id = tdreod.customer_id "+
            "INNER JOIN td_product_cates tdpc " +
            "on tdpc.id = tdp1.cate_id "+
            "WHERE tdreod.order_state in(?1) "+
            "and tdreod.order_type in(60020,60030) "+
            "GROUP BY tdreod.warehouse_id,tdreod.customer_id,tdeod.product_id)t "+
            "RIGHT JOIN "+
            "(select "+
            "tdws.product_id as p_id,"+
            "tdp2.product_name as product_name,"+
            "tdp2.spec as spec,"+
            "tdp2.model as model,"+
            "tdp2.material as material,"+
            "tdp2.maker as maker,"+
            "tdp2.unit as unit,"+
            "tdpc.id as p_cate_id,"+
            "tdpc.product_cate_name as p_cate_name,"+
            "sum(tdws.num) as stock_sum_num,"+
            "sum(tdws.ton_num) as stock_sum_ton_num,"+
            "tdws.warehouse_site_id as w_id,"+
            "tdware.`name` as w_name,"+
            "tdws.customer_id as c_id,"+
            "tdc.`name` as c_name,"+
            "tdp2.rationale as p_rationale "+
            "from td_product tdp2 "+
            "inner join td_warehouse_stock tdws "+
            "on tdp2.id = tdws.product_id "+
            "INNER JOIN td_warehouse tdware "+
            "on tdware.id = tdws.warehouse_site_id "+
            "INNER JOIN td_customer tdc "+
            "on tdc.id = tdws.customer_id "+
            "INNER JOIN td_product_cates tdpc "+
            "on tdpc.id = tdp2.cate_id "+
            "where "+
            "IF(?4 is not null,if (?4 != '', tdws.warehouse_site_id=?4, 1=1),1=1) " +
            "and IF(?5 is not null,if (?5 != '', tdws.customer_id=?5, 1=1),1=1) " +
            "and IF(?2 is not null,if (?2 != '', tdp2.id=?2, 1=1),1=1) " +
            "and IF(?3 is not null,if (?3 != '', tdp2.product_name like CONCAT('%',?3,'%'), 1=1),1=1) " +
            "and IF(?6 is not null,if (?6 != '', tdp2.spec like CONCAT('%',?6,'%'), 1=1),1=1) " +
            "and IF(?7 is not null,if (?7 != '', tdp2.model like CONCAT('%',?7,'%'), 1=1),1=1) " +
            "and IF(?8 is not null,if (?8 != '', tdp2.material like CONCAT('%',?8,'%'), 1=1),1=1) " +
            "and IF(?9 is not null,if (?9 != '', tdp2.maker like CONCAT('%',?9,'%'), 1=1),1=1) " +
            "GROUP BY tdws.product_id,tdws.warehouse_site_id,tdws.customer_id)t2 "+
            "on t.p_id=t2.p_id and t.w_id=t2.w_id and t.c_id=t2.c_id "+
            "LEFT JOIN "+
            "(select "+
            "tdlod.product_id as p_id,"+
            "tdp1.product_name as product_name,"+
            "tdpc.id as p_cate_id,"+
            "tdpc.product_cate_name as p_cate_name,"+
            "sum(tdlod.num) as lock_sum_num,"+
            "sum(tdlod.ton_num) as lock_sum_ton_num,"+
            "tdlso.warehouse_id as w_id,"+
            "tdware.`name` as w_name,"+
            "tdlso.customer_id as c_id,"+
            "tdc.`name` as c_name,"+
            "tdp1.rationale as p_rationale "+
            "from td_product tdp1 "+
            "inner join td_lock_order_detail tdlod "+
            "on tdp1.id = tdlod.product_id "+
            "INNER JOIN td_lock_stock_order tdlso "+
            "on tdlso.id = tdlod.lock_stock_order_id "+
            "INNER JOIN td_warehouse tdware "+
            "on tdware.id = tdlso.warehouse_id "+
            "INNER JOIN td_customer tdc "+
            "on tdc.id = tdlso.customer_id "+
            "INNER JOIN td_product_cates tdpc "+
            "on tdpc.id = tdp1.cate_id "+
            "where tdlso.order_state = 16020 "+
            "GROUP BY tdlod.product_id,tdlso.warehouse_id,tdlso.customer_id)t3 "+
            "on t3.p_id=t2.p_id and t3.w_id=t2.w_id and t3.c_id=t2.c_id)t4",
            nativeQuery = true)
    List<Object[]> totalNumByOrderSates(List<Integer> orderStates,
                                          String productId,
                                          String productName,
                                          String warehouseId,
                                          String customerId,
                                            String spec,
                                            String model,
                                            String material,
                                            String maker
                                        );

    /**
     * 合计数量(不要客户，按仓库、库区、库位、物资分组)
     * @param productId 物资Id（null或者"",就查所有)非必填
     * @param warehouseSiteId 仓库id
     * @param warehouseAreaId 库区id
     * @param warehouseId 库位id
     * @param warehouseLevelId 库层id
     * @param pageable 分页
     * @return List<Object[]>
     */
    @Query(value =
            "SELECT "+
            "tw1.id as warehouse_site_id,"+
            "tw1.`name` as warehouse_site_name,"+
            "tw2.id as warehouse_area_id,"+
            "tw2.`name` as warehouse_area_name,"+
            "tw3.id as warehouse_id,"+
            "tw3.`name` as warehouse_name,"+
            "tw4.id as warehouse_level_id,"+
            "tw4.`name` as warehouse_level_name,"+
            "t.num_total as num_total,"+
            "t.ton_total as ton_total,"+
            "t.p_id as p_id,"+
            "t.product_name as product_name,"+
            "t.spec as spec,"+
            "t.model as model,"+
            "t.material as material,"+
            "t.maker as maker,"+
            "t.unit as unit,"+
            "t.rationale as rationale,"+
            "t.p_cate_id as p_cate_id,"+
            "t.p_cate_name as p_cate_name "+
            "from "+
            "(SELECT "+
            "tws.warehouse_site_id as ws_id,"+
            "tws.warehouse_area_id as wa_id,"+
            "tws.warehouse_id as w_id,"+
            "tws.warehouse_level_id as wl_id,"+
            "tws.product_id as p_id,"+
            "sum(tws.num) as num_total,"+
            "sum(tws.ton_num) as ton_total,"+
            "tdp1.product_name as product_name,"+
            "tdp1.spec as spec,"+
            "tdp1.model as model,"+
            "tdp1.material as material,"+
            "tdp1.maker as maker,"+
            "tdp1.unit as unit,"+
            "tdp1.rationale as rationale,"+
            "tdpc.id as p_cate_id,"+
            "tdpc.product_cate_name as p_cate_name "+
            "from td_warehouse_stock tws "+
            "INNER JOIN td_product tdp1 "+
            "on tdp1.id = tws.product_id "+
            "INNER JOIN td_product_cates tdpc "+
            "on tdpc.id = tdp1.cate_id "+
            "and IF(?1 is not null,if (?1 != '', tws.product_id=?1, 1=1),1=1) " +
            "and IF(?2 is not null,if (?2 != '', tws.warehouse_site_id=?2, 1=1),1=1) " +
            "and IF(?3 is not null,if (?3 != '', tws.warehouse_area_id=?3, 1=1),1=1) " +
            "and IF(?4 is not null,if (?4 != '', tws.warehouse_id=?4, 1=1),1=1) " +
            "and IF(?5 is not null,if (?5 != '', tws.warehouse_level_id=?5, 1=1),1=1) " +
            "GROUP BY "+
            "tws.warehouse_site_id,"+
            "tws.warehouse_area_id,"+
            "tws.warehouse_id,"+
            "tws.warehouse_level_id,"+
            "tws.product_id)t "+
            "left JOIN td_warehouse tw1 "+
            "on tw1.id=t.ws_id "+
            "left JOIN td_warehouse tw2 "+
            "on tw2.id=t.wa_id "+
            "left JOIN td_warehouse tw3 "+
            "on tw3.id=t.w_id "+
            "left JOIN td_warehouse tw4 "+
            "on tw4.id=t.wl_id "+
            "ORDER BY ?#{#pageable}",
            countQuery = "SELECT count(*) "+
                    "from "+
                    "(SELECT "+
                    "tws.warehouse_site_id as ws_id,"+
                    "tws.warehouse_area_id as wa_id,"+
                    "tws.warehouse_id as w_id,"+
                    "tws.warehouse_level_id as wl_id,"+
                    "tws.product_id as p_id,"+
                    "sum(tws.num) as num_total,"+
                    "sum(tws.ton_num) as ton_total,"+
                    "tdp1.product_name as product_name,"+
                    "tdp1.spec as spec,"+
                    "tdp1.model as model,"+
                    "tdp1.material as material,"+
                    "tdp1.maker as maker,"+
                    "tdp1.unit as unit,"+
                    "tdp1.rationale as rationale,"+
                    "tdpc.id as p_cate_id,"+
                    "tdpc.product_cate_name as p_cate_name "+
                    "from td_warehouse_stock tws "+
                    "INNER JOIN td_product tdp1 "+
                    "on tdp1.id = tws.product_id "+
                    "INNER JOIN td_product_cates tdpc "+
                    "on tdpc.id = tdp1.cate_id "+
                    "and IF(?1 is not null,if (?1 != '', tws.product_id=?1, 1=1),1=1) " +
                    "and IF(?2 is not null,if (?2 != '', tws.warehouse_site_id=?2, 1=1),1=1) " +
                    "and IF(?3 is not null,if (?3 != '', tws.warehouse_area_id=?3, 1=1),1=1) " +
                    "and IF(?4 is not null,if (?4 != '', tws.warehouse_id=?4, 1=1),1=1) " +
                    "and IF(?5 is not null,if (?5 != '', tws.warehouse_level_id=?5, 1=1),1=1) " +
                    "GROUP BY "+
                    "tws.warehouse_site_id,"+
                    "tws.warehouse_area_id,"+
                    "tws.warehouse_id,"+
                    "tws.warehouse_level_id,"+
                    "tws.product_id)t "+
                    "left JOIN td_warehouse tw1 "+
                    "on tw1.id=t.ws_id "+
                    "left JOIN td_warehouse tw2 "+
                    "on tw2.id=t.wa_id "+
                    "left JOIN td_warehouse tw3 "+
                    "on tw3.id=t.w_id "+
                    "left JOIN td_warehouse tw4 "+
                    "on tw4.id=t.wl_id "+
                    "ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<Object[]> sumByNoCustomer(String productId,
                                   String warehouseSiteId,
                                   String warehouseAreaId,
                                   String warehouseId,
                                   String warehouseLevelId,
                                   Pageable pageable
                                   );
}

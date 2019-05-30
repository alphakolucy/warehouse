package com.warehouse.service.repository;

import com.warehouse.model.EntrustOrder;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrustOrderRepository extends WiselyRepository<EntrustOrder, String>  {
    /**
     * 统计，委托单某种状态下明细里每中物资的总件数和总吨数。
     * @param orderState 状态
     * @param productId 非必填（null或者"",就查所有）
     * @param pageable 分页信息
     * @return Page<Object[]>
     */

    @Query(value = "select eDetail.product_id as product_id," +
            "sum(eDetail.num) as sum_num," +
            "sum(eDetail.ton_num) as sum_ton_num " +
            "from td_receipt_entrust_order eOrder " +
            "INNER JOIN td_entrust_order_detail eDetail " +
            "ON eOrder.id = eDetail.entrust_order_id " +
            "where eOrder.order_state = ?1 " +
            "and IF(?2 is not null,if (?2 != '', eDetail.product_id=?2, 1=1),1=1) " +
            "GROUP BY eDetail.product_id " +
            "ORDER BY ?#{#pageable}",
            countQuery = "select count(*) " +
                    "from td_receipt_entrust_order eOrder " +
                    "INNER JOIN td_entrust_order_detail eDetail " +
                    "ON eOrder.id = eDetail.entrust_order_id " +
                    "where eOrder.order_state = ?1 " +
                    "and IF(?2 is not null,if (?2 != '', eDetail.product_id=?2, 1=1),1=1) " +
                    "GROUP BY eDetail.product_id " +
                    "ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<Object[]> sumDetailProductByOrderSate(Integer orderState,
                                                String productId,
                                                Pageable pageable);


    /**
     * 获取数据库中某年某周最大周序号的一条数据
     */
    @Query(value = "select * from td_receipt_entrust_order " +
            "where DATE_FORMAT(create_time,'%Y') = ?1 and year_week = ?2 and order_type = ?3"
            +" order by create_time desc limit 1",nativeQuery = true)
    EntrustOrder findEveryYearWeekMaxWeekNo(String strYear, String strWeek, Integer orderType);
}

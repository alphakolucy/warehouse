package com.warehouse.service.repository;

import com.warehouse.model.Receipt;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends WiselyRepository<Receipt, String> {
    /**
     * 获取数据库中某天最大单号的一条数据
     */
    @Query(value = "select * from td_receipt " +
            "where DATE_FORMAT(create_time,'%Y%m%d') = ?1 and order_type = ?2"
            +" order by order_no desc limit 1",nativeQuery = true)
    Receipt findEveryDayMaxOrderNo(String strDate, Integer orderType);

    /**
     * 获取数据库中某年某周最大周序号的一条数据
     */
    @Query(value = "select * from td_receipt " +
            "where DATE_FORMAT(create_time,'%Y') = ?1 and year_week = ?2 and order_type = ?3"
            +" order by create_time desc limit 1",nativeQuery = true)
    Receipt findEveryYearWeekMaxWeekNo(String strYear, String strWeek, Integer orderType);
}

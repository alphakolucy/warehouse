package com.warehouse.service.repository;

import com.warehouse.model.MeteringReceiptOrder;
import com.warehouse.model.MeteringReceiptOrder;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MeteringReceiptOrderRepository extends WiselyRepository<MeteringReceiptOrder, String> {
    /**
     * 获取数据库中某天最大单号的一条数据
     */
    @Query(value = "select * from td_metering_receipt_order " +
            "where DATE_FORMAT(create_time,'%Y%m%d') = ?1"
            +" order by order_no desc limit 1",nativeQuery = true)
    MeteringReceiptOrder findEveryDayMaxOrderNo(String strDate);
}

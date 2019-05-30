package com.warehouse.service.repository;

import org.springframework.stereotype.Repository;
 
import com.warehouse.model.CustomerWarehouseStockReportForMonth;
import com.warehouse.service.repository.wisely.WiselyRepository;

@Repository
public interface CustomerWarehouseStockReportForMonthRepository extends WiselyRepository<CustomerWarehouseStockReportForMonth, String>   {

}

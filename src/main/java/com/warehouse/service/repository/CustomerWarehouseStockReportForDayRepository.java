package com.warehouse.service.repository;

import org.springframework.stereotype.Repository;

import com.warehouse.model.CustomerWarehouseStockReportForDay;
import com.warehouse.service.repository.wisely.WiselyRepository;

@Repository
public interface CustomerWarehouseStockReportForDayRepository extends WiselyRepository<CustomerWarehouseStockReportForDay, String>   {

}

package com.warehouse.service.repository;

import com.warehouse.model.WarehouseStockLog;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WarehouseStockLogRepository extends WiselyRepository<WarehouseStockLog, String> {
}

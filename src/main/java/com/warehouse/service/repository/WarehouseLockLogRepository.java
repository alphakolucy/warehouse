package com.warehouse.service.repository;

import com.warehouse.model.WarehouseLockLog;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseLockLogRepository extends WiselyRepository<WarehouseLockLog, String> {

}

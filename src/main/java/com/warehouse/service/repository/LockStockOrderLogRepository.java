package com.warehouse.service.repository;

import com.warehouse.model.LockStockOrderLog;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LockStockOrderLogRepository extends WiselyRepository<LockStockOrderLog, String> {

}

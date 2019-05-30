package com.warehouse.service.repository;

import com.warehouse.model.MoveStockOrderLog;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MoveStockOrderLogRepository extends WiselyRepository<MoveStockOrderLog, String> {

}

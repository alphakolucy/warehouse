package com.warehouse.service.repository;

import com.warehouse.model.InventoryLog;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryLogRepository extends WiselyRepository<InventoryLog, String> {

}

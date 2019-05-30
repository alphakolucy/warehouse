package com.warehouse.service.repository;

import com.warehouse.model.ReceiptLog;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptLogRepository extends WiselyRepository<ReceiptLog, String> {

}

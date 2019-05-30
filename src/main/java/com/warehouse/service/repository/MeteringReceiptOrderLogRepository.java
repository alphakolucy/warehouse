package com.warehouse.service.repository;

import com.warehouse.model.MeteringReceiptOrderLog;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeteringReceiptOrderLogRepository extends WiselyRepository<MeteringReceiptOrderLog, String> {
    
}

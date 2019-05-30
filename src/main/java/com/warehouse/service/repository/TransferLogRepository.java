package com.warehouse.service.repository;

import com.warehouse.model.TransferLog;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferLogRepository extends WiselyRepository<TransferLog, String>  {

}

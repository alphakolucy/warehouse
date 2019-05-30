package com.warehouse.service.repository;

import com.warehouse.model.EntrustOrderLog;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrustOrderLogRepository extends WiselyRepository<EntrustOrderLog, String>  {

}

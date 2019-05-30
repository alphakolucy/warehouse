package com.warehouse.service.repository;

import com.warehouse.model.CraneBindSteelyardLog;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CraneBindSteelyardLogRepository extends WiselyRepository<CraneBindSteelyardLog, String> {

}

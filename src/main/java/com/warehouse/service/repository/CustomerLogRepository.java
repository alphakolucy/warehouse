package com.warehouse.service.repository;

import org.springframework.stereotype.Repository;

import com.warehouse.model.CustomerLog;
import com.warehouse.service.repository.wisely.WiselyRepository;

@Repository
public interface CustomerLogRepository extends WiselyRepository<CustomerLog, String> {

}

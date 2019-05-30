package com.warehouse.service.repository;

import com.warehouse.model.DeviceMaintainLedger;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceMaintainLedgerRepository extends WiselyRepository<DeviceMaintainLedger, String>  {

}

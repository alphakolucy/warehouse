package com.warehouse.service.repository;

import com.warehouse.model.TransferSettlement;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferSettlementRepository extends WiselyRepository<TransferSettlement, String>  {

}

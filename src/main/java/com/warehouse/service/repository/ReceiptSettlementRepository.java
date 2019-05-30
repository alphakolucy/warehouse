package com.warehouse.service.repository;

import com.warehouse.model.ReceiptSettlement;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptSettlementRepository extends WiselyRepository<ReceiptSettlement, String>  {

}

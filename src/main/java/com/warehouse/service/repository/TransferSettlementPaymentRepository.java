package com.warehouse.service.repository;

import com.warehouse.model.TransferSettlementPayment;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferSettlementPaymentRepository extends WiselyRepository<TransferSettlementPayment, String>  {

}

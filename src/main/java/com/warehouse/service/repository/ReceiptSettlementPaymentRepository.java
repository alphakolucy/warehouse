package com.warehouse.service.repository;

import com.warehouse.model.ReceiptSettlementPayment;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptSettlementPaymentRepository extends WiselyRepository<ReceiptSettlementPayment, String>  {

}

package com.warehouse.service.repository;

import com.warehouse.model.TransferDetail;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferDetailRepository extends WiselyRepository<TransferDetail, String> {

}

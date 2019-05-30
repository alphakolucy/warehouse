package com.warehouse.service.repository;

import com.warehouse.model.LockStockOrderDetail;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockStockOrderDetailRepository extends WiselyRepository<LockStockOrderDetail, String>  {

}

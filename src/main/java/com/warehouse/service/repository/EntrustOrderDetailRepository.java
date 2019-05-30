package com.warehouse.service.repository;

import com.warehouse.model.EntrustOrderDetail;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrustOrderDetailRepository extends WiselyRepository<EntrustOrderDetail, String>  {

}

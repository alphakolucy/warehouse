package com.warehouse.service.repository;

import com.warehouse.model.InventoryDetail;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDetailRepository extends WiselyRepository<InventoryDetail, String> {

}

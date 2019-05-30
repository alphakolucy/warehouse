package com.warehouse.service.repository;

import com.warehouse.model.Inventory;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends WiselyRepository<Inventory, String> {

}

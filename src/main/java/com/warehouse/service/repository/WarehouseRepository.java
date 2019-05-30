package com.warehouse.service.repository;

import com.warehouse.model.Warehouse;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends WiselyRepository<Warehouse, String>,JpaSpecificationExecutor<Warehouse> {

}

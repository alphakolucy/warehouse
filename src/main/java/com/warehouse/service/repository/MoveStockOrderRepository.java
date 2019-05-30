package com.warehouse.service.repository;

import com.warehouse.model.MoveStockOrder;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MoveStockOrderRepository extends WiselyRepository<MoveStockOrder, String> {

}

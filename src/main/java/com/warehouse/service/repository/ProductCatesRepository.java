package com.warehouse.service.repository;

import com.warehouse.model.ProductCates;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCatesRepository extends WiselyRepository<ProductCates, String>  {

}

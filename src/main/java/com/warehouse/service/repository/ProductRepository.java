package com.warehouse.service.repository;

import com.warehouse.model.Product;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends WiselyRepository<Product, String> {

}

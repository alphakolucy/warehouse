package com.warehouse.service.repository;

import com.warehouse.model.Customer;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends WiselyRepository<Customer, String>  {

}

package com.warehouse.service.repository;

import com.warehouse.model.BaseCostSettins;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseCostSettinsRepository extends WiselyRepository<BaseCostSettins, String>  {

}

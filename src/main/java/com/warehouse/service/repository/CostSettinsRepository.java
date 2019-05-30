package com.warehouse.service.repository;

import com.warehouse.model.CostSettins;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostSettinsRepository extends WiselyRepository<CostSettins, String>  {

}

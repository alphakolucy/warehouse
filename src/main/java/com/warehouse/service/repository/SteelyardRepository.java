package com.warehouse.service.repository;

import com.warehouse.model.Steelyard;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SteelyardRepository extends WiselyRepository<Steelyard, String>  {

}

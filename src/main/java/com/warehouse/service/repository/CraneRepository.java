package com.warehouse.service.repository;

import com.warehouse.model.Crane;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CraneRepository extends WiselyRepository<Crane, String>  {

}

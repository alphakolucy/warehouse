package com.warehouse.service.repository;

import com.warehouse.model.Scheduling;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingRepository extends WiselyRepository<Scheduling, String>  {

}

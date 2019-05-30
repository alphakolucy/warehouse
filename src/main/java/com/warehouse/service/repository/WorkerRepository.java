package com.warehouse.service.repository;

import com.warehouse.model.Worker;
import com.warehouse.service.repository.wisely.WiselyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends WiselyRepository<Worker, String>  {

}

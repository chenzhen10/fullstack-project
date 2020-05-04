package com.example.task.repository;

import com.example.task.model.SensorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<SensorModel, Integer>, JpaSpecificationExecutor<SensorModel> {
}

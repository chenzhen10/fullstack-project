package com.example.task.service;

import com.example.task.model.SensorModel;
import com.example.task.ui.SensorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SensorService {

    Page<SensorDto> findAll(Pageable pageable);

    SensorDto findById(Integer id);

    Page<SensorDto> search(String value, Pageable pageable);

    void update(Integer id, SensorModel updatedSensor);

    SensorDto create(SensorModel sensorModel);

    void deleteById(Integer id);
}

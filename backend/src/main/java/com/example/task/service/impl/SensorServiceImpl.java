package com.example.task.service.impl;

import com.example.task.common.FieldAccessor;
import com.example.task.common.QueryBuilder;
import com.example.task.mapper.SensorMapper;
import com.example.task.model.SensorModel;
import com.example.task.repository.SensorRepository;
import com.example.task.service.SensorService;
import com.example.task.service.search.SensorSpecification;
import com.example.task.ui.SensorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    private final SensorMapper sensorMapper;

    @Override
    public Page<SensorDto> findAll(Pageable pageable) {
        Page<SensorModel> sensorModels = sensorRepository.findAll(pageable);
        return sensorModels.map(sensorMapper::sensorModelListToSensorDtoList);
    }

    @Override
    public SensorDto findById(Integer id) {
        return sensorMapper.sensorModelToSensorDto(sensorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sensor model was not found")));
    }

    @Override
    public Page<SensorDto> search(String value, Pageable pageable) {
        QueryBuilder<SensorModel> sensorModelQueryBuilder = new QueryBuilder<>();

        FieldAccessor.getAllFields(SensorModel.class).forEach(fieldName ->
                sensorModelQueryBuilder.append(SensorSpecification.fuzzySearchByField(fieldName, value))
        );

        Specification<SensorModel> spec = sensorModelQueryBuilder.getQuery();
        Page<SensorModel> sensorModels = sensorRepository.findAll(spec, pageable);
        return sensorModels.map(sensorMapper::sensorModelListToSensorDtoList);
    }

    @Override
    public void update(Integer id, SensorModel updatedSensor) {
        updatedSensor.setId(id);
        sensorRepository.save(updatedSensor);
    }

    @Override
    public SensorDto create(SensorModel sensorModel) {
        return sensorMapper.sensorModelToSensorDto(sensorRepository.save(sensorModel));
    }

    @Override
    public void deleteById(Integer id) {
        sensorRepository.deleteById(id);
    }
}

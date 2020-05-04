package com.example.task.mapper;

import com.example.task.model.SensorModel;
import com.example.task.ui.SensorDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SensorMapper {

    SensorDto sensorModelToSensorDto(SensorModel sensorModel);

    SensorDto sensorModelListToSensorDtoList(SensorModel sensorModel);

}

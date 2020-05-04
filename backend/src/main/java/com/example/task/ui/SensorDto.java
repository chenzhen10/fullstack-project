package com.example.task.ui;

import com.example.task.model.type.SensorType;
import com.example.task.model.type.Unit;
import lombok.Data;

@Data
public class SensorDto {

    private Integer id;

    private String name;

    private String model;

    private Integer from;

    private Integer to;

    private SensorType type;

    private Unit unit;

    private String location;

    private String description;
}

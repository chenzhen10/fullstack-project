package com.example.task.model;

import com.example.task.model.type.SensorType;
import com.example.task.model.type.Unit;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sensor")
public class SensorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sensor_name")
    private String name;

    private String model;

    @Column(name = "start")
    private Integer from;

    @Column(name = "end")
    private Integer to;

    @Enumerated(EnumType.STRING)
    private SensorType type;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private String location;

    @Column(name = "sensor_description")
    private String description;
}

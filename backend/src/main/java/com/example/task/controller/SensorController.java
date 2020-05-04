package com.example.task.controller;

import com.example.task.model.SensorModel;
import com.example.task.model.type.SensorType;
import com.example.task.model.type.Unit;
import com.example.task.ui.SensorDto;
import com.example.task.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping(value = "/api/v1/sensors", produces = "application/json; utf-8")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class SensorController {

    private final SensorService sensorService;

    @GetMapping
    public Page<SensorDto> findAll(Pageable pageable) {
        return sensorService.findAll(pageable);
    }

    @GetMapping("/search")
    public Page<SensorDto> search(@RequestParam String searchCriteria, Pageable pageable) {
        return sensorService.search(searchCriteria, pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody SensorModel sensorDto) {
        sensorService.update(id, sensorDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<SensorDto> create(@RequestBody SensorModel newSensorDto) {
        SensorDto sensorDto = sensorService.create(newSensorDto);
        return ResponseEntity.created(URI.create(sensorDto.getId().toString())).body(sensorDto);
    }

    @GetMapping("/{id}")
    public SensorDto findById(@PathVariable Integer id){
        return sensorService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        sensorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/units")
    public Unit[] getAllUnits(){
        return Unit.values();
    }

    @GetMapping("/types")
    public SensorType[] getAllTypes(){
        return SensorType.values();
    }
}

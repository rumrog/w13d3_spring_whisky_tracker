package com.codeclan.example.WhiskyTracker.controllers;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getAllDistilleries(){
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/{id}")
    public ResponseEntity<Optional<Distillery>> getDistillery(@PathVariable Long id) {
        return new ResponseEntity<>(distilleryRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "distilleries")
    public ResponseEntity<Distillery> postDistillery(@RequestBody Distillery distillery){
        Distillery newDistillery = distilleryRepository.save(distillery);
        return new ResponseEntity<>(newDistillery, HttpStatus.CREATED);
    }

    @GetMapping(value = "/distilleries/region")
    public ResponseEntity<List<Distillery>> findByRegion(@RequestParam(name = "region") String region) {
        return new ResponseEntity<>(distilleryRepository.findByRegion(region), HttpStatus.OK);
    }

}

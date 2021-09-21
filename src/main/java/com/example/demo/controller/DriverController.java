package com.example.demo.controller;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.servie.ModelService;
import com.example.demo.filter.AgeCriteria;
import com.example.demo.model.DriverModel;
import com.example.demo.model.IModel;
import com.example.demo.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {

    @Autowired
    @Qualifier("driverServiceImpl")
    ModelService<DriverModel> driverService;

    @GetMapping
    public ResponseEntity<List<DriverModel>> getAllDrivers() {
        List<DriverModel> drivers = driverService.getAllModels().stream().map(model -> (DriverModel) model).collect(Collectors.toList());
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverModel> getDriverById(@PathVariable Long id) throws NotFoundException {
        DriverModel persistedDriver = (DriverModel) driverService.getModelById(id);
        return new ResponseEntity<>(persistedDriver, HttpStatus.OK);
    }

    @GetMapping("/adults")
    public ResponseEntity<List<DriverModel>> getAdultDrivers() {
        List<DriverModel> drivers = new AgeCriteria()
                .meetCriteria(driverService.getAllModels().stream().map(model -> (DriverModel) model).collect(Collectors.toList()));
        return new ResponseEntity<>(drivers, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<DriverModel> saveDriver(@RequestBody DriverModel driver) {
        DriverModel persistedDriver = (DriverModel) driverService.saveModel(driver);
        return new ResponseEntity<>(persistedDriver, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverModel>  updateDriverById(@RequestBody DriverModel driver, @PathVariable Long id) throws NotFoundException {
        DriverModel persistedDriver = (DriverModel) driverService.updateModelById(driver, id);
        return new ResponseEntity<>(persistedDriver, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDriverById (@PathVariable Long id) {
        try {
            driverService.deleteModelById(id);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

package com.example.demo.controller;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.servie.ModelService;
import com.example.demo.model.CarModel;
import com.example.demo.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {

    @Autowired
    @Qualifier("carServiceImpl")
    ModelService<CarModel> carService;

    @GetMapping
    public ResponseEntity<List<IModel<CarModel>>> getCars() {
        List<IModel<CarModel>> cars = carService.getAllModels();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarModel> getCarById(@PathVariable Long id) throws NotFoundException {
        CarModel persistedCar = (CarModel) carService.getModelById(id);
        return new ResponseEntity<>(persistedCar, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarModel> saveCar(@RequestBody CarModel car) {
        CarModel persistedCar = (CarModel) carService.saveModel(car);
        return new ResponseEntity<>(persistedCar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModel> updateCarById (@RequestBody CarModel car, @PathVariable Long id) throws NotFoundException {
        CarModel persistedCar = (CarModel) carService.updateModelById(car, id);
        return new ResponseEntity<>(persistedCar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCarById(@PathVariable Long id) throws NotFoundException {
        try {
            carService.deleteModelById(id);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

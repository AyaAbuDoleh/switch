package com.example.demo.controller;

import com.example.demo.exceptions.CarAlreadyInUseException;
import com.example.demo.exceptions.DriverHasACarException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.resourcse.DriverDo;
import com.example.demo.servie.DriverServiceImpl;
import com.example.demo.servie.ModelService;
import com.example.demo.model.CarModel;
import com.example.demo.model.DriverModel;
import com.example.demo.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/driverdo")
public class DriverDoController {

    @Autowired
    @Qualifier("driverServiceImpl")
    ModelService<DriverModel> driverService;

    @Autowired
    @Qualifier("carServiceImpl")
    ModelService<CarModel> carService;

    @GetMapping
    public ResponseEntity<List<DriverModel>> getAllReservations() {
        List<DriverModel> reservations = driverService.getAllModels().stream()
                .filter(driverModel -> ((DriverModel)driverModel).getCar_model().isPresent())
                .map(driverModelIModel -> (DriverModel)driverModelIModel)
                .collect(Collectors.toList());

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping("/{carId}")
    public ResponseEntity<DriverModel> reserveCar(@PathVariable Long carId, @RequestBody DriverModel driverModel) throws DriverHasACarException, NotFoundException, CarAlreadyInUseException {
        Optional<IModel<DriverModel>> refreshedDriverModel = driverService.refresh(driverModel);
        if (!refreshedDriverModel.isPresent()) {
            throw new NotFoundException("Driver is not found!");
        }
        DriverModel persistedDriver = (DriverModel) refreshedDriverModel.get();
        if (persistedDriver.getCar_model().isPresent()) {
            throw new DriverHasACarException("Driver already own a car. They should return it before owning other one!");
        }
        DriverModel existingDriver = (DriverModel) ((DriverServiceImpl) driverService)
                .findDriverWithReservedCar(carId)
                .orElseGet(driverModel);
        if (existingDriver.getId() != driverModel.getId()) {
            throw new CarAlreadyInUseException("Car Already reserved!");
        }
        CarModel carModel = (CarModel) carService.getModelById(carId);
        DriverDo driverDo = new DriverDo(carModel, persistedDriver);
        driverModel = (DriverModel) driverService.saveModel(driverDo.getDriverModel());
        carModel = (CarModel) carService.saveModel(driverDo.getCarModel());
        return new ResponseEntity<>(driverDo.getDriverModel(), HttpStatus.OK);
    }



}

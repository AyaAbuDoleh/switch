package com.example.demo.servie;

import com.example.demo.entity.Car;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.SwitchException;
import com.example.demo.repository.CarRepository;
import com.example.demo.model.CarModel;
import com.example.demo.model.DriverModel;
import com.example.demo.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarServiceImpl implements ModelService<CarModel> {

    @Autowired
    @Qualifier("driverServiceImpl")
    ModelService<DriverModel> driverService;

    final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Optional<IModel<CarModel>> refresh(IModel<CarModel> model) {
        CarModel carModel = (CarModel) model;
        return Optional.ofNullable(transferEntityToModel(carRepository.findById(carModel.getId())
                .orElseGet(() -> carRepository.findByLicensePlate(carModel.getLicense_plate()))));
    }

    public IModel<CarModel> saveModel(IModel<CarModel> carModel) {
        Car persistedCar = carRepository.save(transferModelToEntity((CarModel) carModel));
        return transferEntityToModel(persistedCar);
    }

    public List<IModel<CarModel>> getAllModels() {
        List<IModel<CarModel>> carModels = new ArrayList<>();
        carRepository.findAll().forEach(car -> carModels.add(transferEntityToModel(car)));
        return carModels;
    }

    public IModel<CarModel> getModelById(Long id) throws NotFoundException {
        Car persistedCar = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Requested com.example.demo.model of id ("+ id +") is not found"));
        return transferEntityToModel(persistedCar);
    }

    public IModel<CarModel> updateModelById(IModel<CarModel> carModel, Long id)  {
       CarModel newCarModel = (CarModel) carModel;
        Car persistedCar = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Requested com.example.demo.model of id ("+ id +") is not found"));

        if (!newCarModel.getConvertible().equals(persistedCar.getConvertible())) {
            persistedCar.setConvertible(newCarModel.getConvertible());
        }
        if (!newCarModel.getSeat_count().equals(persistedCar.getSeatCount())) {
            persistedCar.setSeatCount(newCarModel.getSeat_count());
        }
        if (!newCarModel.getEngine_type().equals(persistedCar.getEngineType())) {
            persistedCar.setEngineType(newCarModel.getEngine_type());
        }
        if (!newCarModel.getManufacture().equals(persistedCar.getManufacture())) {
            persistedCar.setManufacture(newCarModel.getManufacture());
        }
        if (!newCarModel.getRating().equals(persistedCar.getRating())) {
            persistedCar.setRating(newCarModel.getRating());
        }
        if (!newCarModel.getLicense_plate().equals(persistedCar.getLicensePlate())) {
            persistedCar.setLicensePlate(newCarModel.getLicense_plate());
        }
        if (!newCarModel.getReserved().equals(persistedCar.getReserved())) {
            if (newCarModel.getReserved() == false) {
                //clear driver
                DriverModel driverModel = (DriverModel) ((DriverServiceImpl) driverService).findDriverWithReservedCar(persistedCar.getId())
                        .orElseThrow(() -> new NotFoundException("Found inconsistency in DB \n" +
                                "Car ("+ persistedCar.getLicensePlate() + ") is marked reserved without a driver!"));
                driverModel.setCar_model(null);
                driverService.updateModelById(driverModel, driverModel.getId());
            }
            persistedCar.setReserved(newCarModel.getReserved());
        }

        return transferEntityToModel(carRepository.save(persistedCar));
    }

    public void deleteModelById(Long id) throws NotFoundException {
        Car persistedCar = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Requested com.example.demo.model of id ("+ id +") is not found"));
        carRepository.deleteById(id);
    }

    protected Car transferModelToEntity(CarModel carModel) {
       return Car.builder().convertible(carModel.getConvertible())
                .dateCreated(carModel.getDate_created())
                .licensePlate(carModel.getLicense_plate())
                .engineType(carModel.getEngine_type())
                .seatCount(carModel.getSeat_count())
                .manufacture(carModel.getManufacture())
                .rating(carModel.getRating())
               .reserved(carModel.getReserved())
                .id(carModel.getId())
        .build();
    }

    protected CarModel transferEntityToModel(Car car) {
        return new CarModel(car);
    }
}

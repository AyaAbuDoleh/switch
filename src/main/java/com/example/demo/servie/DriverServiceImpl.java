package com.example.demo.servie;

import com.example.demo.entity.Driver;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.DriverRepository;
import com.example.demo.model.DriverModel;
import com.example.demo.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class DriverServiceImpl implements ModelService<DriverModel> {

    @Autowired
    CarServiceImpl carService;

    final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Optional<IModel<DriverModel>> refresh (IModel<DriverModel> model) {
        DriverModel driverModel = (DriverModel) model;
        return Optional.ofNullable(transferEntityToModel(driverRepository.findById(driverModel.getId())
                .orElseGet(() -> driverRepository.findByLicenseNumber(driverModel.getLicense_number()))));
    }

    public IModel<DriverModel> saveModel(IModel<DriverModel> driverModel) {
        Driver persistedDriver = driverRepository.save(transferModelToEntity((DriverModel) driverModel));
        return transferEntityToModel(persistedDriver);
    }

    public IModel<DriverModel> updateModelById(IModel<DriverModel> model, Long id) throws NotFoundException {
        DriverModel driverModel = (DriverModel) model;
        Driver persistedDriver = driverRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Requested com.example.demo.model of id ("+ id +") is not found"));
        if (!driverModel.getBirthday().equals(persistedDriver.getBirthday())) {
            persistedDriver.setBirthday(driverModel.getBirthday());
        }
        if (!driverModel.getFirst_name().equals(persistedDriver.getFirstName())) {
            persistedDriver.setFirstName(driverModel.getFirst_name());
        }
        if (!driverModel.getLast_name().equals(persistedDriver.getLastName())) {
            persistedDriver.setLastName(driverModel.getLast_name());
        }
        if (!driverModel.getLicense_number().equals(persistedDriver.getLicenseNumber())) {
            persistedDriver.setLicenseNumber(persistedDriver.getLicenseNumber());
        }
        return transferEntityToModel(driverRepository.save(persistedDriver));
    }

    public IModel<DriverModel> getModelById(Long id) throws NotFoundException {
       Driver persistedDriver = driverRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Requested com.example.demo.model of id ("+ id +") is not found"));
       return transferEntityToModel(persistedDriver);
    }

    public Optional<IModel<DriverModel>> findDriverWithReservedCar(Long carId) {
        Driver persistedDriver =  driverRepository.findByCarId(carId);
        return persistedDriver != null
                ? Optional.of(transferEntityToModel(persistedDriver))
                : Optional.empty();
    }

    public List<IModel<DriverModel>> getAllModels() {
        List<IModel<DriverModel>> drivers = new ArrayList<>();
        driverRepository.findAll().forEach(driver -> drivers.add(transferEntityToModel(driver)));
        return drivers;
    }

    public void deleteModelById(Long id) throws NotFoundException {
        Driver persistedDriver = driverRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Requested com.example.demo.model of id ("+ id +") is not found"));
        driverRepository.deleteById(id);
    }

    protected Driver transferModelToEntity(DriverModel driverModel) {
         Driver driver = Driver.builder().birthday(driverModel.getBirthday())
                .dateCreated(driverModel.getDate_created())
                .firstName(driverModel.getFirst_name())
                .lastName(driverModel.getLast_name())
                .licenseNumber(driverModel.getLicense_number())
                 .licenseType(driverModel.getLicense_type())
                .id(driverModel.getId())
                .build();
         if (driverModel.getCar_model() != null && driverModel.getCar_model().isPresent()) {
             driver.setCar(carService.transferModelToEntity(driverModel.getCar_model().get()));
         }
         return driver;
    }

    protected DriverModel transferEntityToModel(Driver driver) {
        return new DriverModel(driver);
    }
}

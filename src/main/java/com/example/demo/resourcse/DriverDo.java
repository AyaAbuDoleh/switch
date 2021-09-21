package com.example.demo.resourcse;

import com.example.demo.exceptions.CarAlreadyInUseException;
import com.example.demo.exceptions.DriverHasACarException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.CarModel;
import com.example.demo.model.DriverModel;
import java.io.Serializable;


public class DriverDo implements Serializable {

    private final DriverModel driverModel;
    private final CarModel carModel;

    public DriverDo(CarModel carModel, DriverModel driverModel) throws NotFoundException, DriverHasACarException, CarAlreadyInUseException {
        this.carModel = carModel;
        this.driverModel = driverModel;
        this.driverModel.setCar_model(carModel);
        this.carModel.setReserved(true);
        this.driverModel.setCar_model(this.carModel);

    }

    public DriverModel getDriverModel() {
        return driverModel;
    }

    public CarModel getCarModel() {
        return carModel;
    }
}

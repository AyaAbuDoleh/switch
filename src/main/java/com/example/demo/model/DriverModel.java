package com.example.demo.model;

import com.example.demo.entity.Driver;
import com.example.demo.entity.enums.LicenseType;
import com.example.demo.exceptions.NotFoundException;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

public class DriverModel implements IModel<DriverModel>, Serializable, Supplier<IModel<DriverModel>> {

    private Long id;

    private String first_name;

    private String last_name;

    private Date birthday;

    private String license_number;

    private ZonedDateTime date_created;

    @Enumerated(EnumType.STRING)
    private LicenseType license_type;

    private Optional<CarModel> car_model;

    public DriverModel() {

    }

    public DriverModel(Driver driver) {
        this.id = driver.getId();
        this.birthday = driver.getBirthday();
        this.first_name = driver.getFirstName();
        this.last_name = driver.getLastName();
        this.license_number = driver.getLicenseNumber();
        this.date_created = driver.getDateCreated();
        this.license_type = driver.getLicenseType();
        this.car_model = driver.getCar() == null
                ? Optional.ofNullable(null)
                : Optional.ofNullable(new CarModel(driver.getCar()));
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getLicense_number() {
        return license_number;
    }

    public ZonedDateTime getDate_created() {
        return date_created;
    }

    public LicenseType getLicense_type() {
        return license_type;
    }

    public Optional<CarModel> getCar_model() {
        return car_model;
    }

    public void setCar_model(CarModel car_model) throws  NotFoundException {
        this.car_model = Optional.ofNullable(car_model);
    }

    @Override
    public IModel<DriverModel> get() {
        return this;
    }
}

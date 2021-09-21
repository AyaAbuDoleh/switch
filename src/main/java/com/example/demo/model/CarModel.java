package com.example.demo.model;

import com.example.demo.entity.Car;
import com.example.demo.entity.enums.EngineType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.ZonedDateTime;

public class CarModel implements IModel<CarModel>, Serializable {

    private Long id;

    private ZonedDateTime date_created;

    private String license_plate;

    private Integer seat_count;

    private Boolean convertible;

    private Integer rating;

    @Enumerated(EnumType.STRING)
    private EngineType engine_type;

    private String manufacture;

    private Boolean reserved;

    public CarModel(){

    }

    public CarModel(Car car) {
        this.id = car.getId();
        this.license_plate = car.getLicensePlate();
        this.seat_count = car.getSeatCount();
        this.rating = car.getRating();
        this.convertible = car.getConvertible();
        this.manufacture = car.getManufacture();
        this.date_created = car.getDateCreated();
        this.engine_type = car.getEngineType();
        this.reserved = car.getReserved();
    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getDate_created() {
        return date_created;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public Integer getSeat_count() {
        return seat_count;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public Integer getRating() {
        return rating;
    }

    public EngineType getEngine_type() {
        return engine_type;
    }

    public String getManufacture() {
        return manufacture;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }
}

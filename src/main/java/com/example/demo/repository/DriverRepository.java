package com.example.demo.repository;

import com.example.demo.entity.Driver;
import org.springframework.data.repository.CrudRepository;


public interface DriverRepository extends CrudRepository<Driver, Long> {

    Driver findByCarId(Long carId);

    Driver findByLicenseNumber(String licenseNumber);
}

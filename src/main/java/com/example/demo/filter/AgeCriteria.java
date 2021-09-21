package com.example.demo.filter;

import com.example.demo.model.DriverModel;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AgeCriteria implements Criteria {

    @Override
    public List<DriverModel> meetCriteria(List<DriverModel> persons) {
        final long now = new Date().getTime();
        return persons.stream().filter(driverModel ->
                driverModel.getBirthday().getTime() - now >= 18 * 365 * 24 * 60 * 60 * 1000)
                .collect(Collectors.toList());
    }
}

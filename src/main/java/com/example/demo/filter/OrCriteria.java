package com.example.demo.filter;

import com.example.demo.model.DriverModel;

import java.util.List;
import java.util.stream.Collectors;

public class OrCriteria implements Criteria {

    private final Criteria criteria;
    private final Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }


    @Override
    public List<DriverModel> meetCriteria(List<DriverModel> driverModels) {
        List<DriverModel> criteriaDrivers = criteria.meetCriteria(driverModels);
        List<DriverModel> otherCriteriaDrivers = otherCriteria.meetCriteria(driverModels);
        criteriaDrivers.addAll(otherCriteriaDrivers);
        return criteriaDrivers.stream().collect(Collectors.toSet())
                .stream().collect(Collectors.toList());
    }
}

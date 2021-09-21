package com.example.demo.filter;

import com.example.demo.model.DriverModel;

import java.util.List;

public class AndCriteria implements Criteria {

    private final Criteria criteria;
    private final Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<DriverModel> meetCriteria(List<DriverModel> driverModels) {
        List<DriverModel> criteriaDrivers = criteria.meetCriteria(driverModels);
        List<DriverModel> otherCriteriaDriversFromCriteria = otherCriteria.meetCriteria(criteriaDrivers);
        return otherCriteriaDriversFromCriteria;
    }
}

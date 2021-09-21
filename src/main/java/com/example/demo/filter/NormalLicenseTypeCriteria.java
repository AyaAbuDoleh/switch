package com.example.demo.filter;

import com.example.demo.entity.enums.LicenseType;
import com.example.demo.model.DriverModel;

import java.util.List;
import java.util.stream.Collectors;

public class NormalLicenseTypeCriteria implements Criteria {

    @Override
    public List<DriverModel> meetCriteria(List<DriverModel> driverModels) {
        return driverModels.stream()
                .filter(model -> model.getLicense_type().equals(LicenseType.NORMAL))
                .collect(Collectors.toList());
    }
}

package com.example.demo.filter;

import com.example.demo.model.DriverModel;

import java.util.*;

public interface Criteria {
    public List<DriverModel> meetCriteria(List<DriverModel> persons);
}

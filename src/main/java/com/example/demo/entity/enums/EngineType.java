package com.example.demo.entity.enums;

public enum EngineType {
    ELECTRICAL(1), GAS(2);
    long engineId;
    EngineType(long id) {
        this.engineId = id;
    }
    public long getEngineId() {
        return engineId;
    }
}

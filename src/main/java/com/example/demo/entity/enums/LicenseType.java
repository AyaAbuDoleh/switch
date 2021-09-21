package com.example.demo.entity.enums;

public enum LicenseType {
    AUTOMATIC(1), NORMAL(2);
    long licenseId;
    LicenseType(long id) {
        this.licenseId = id;
    }

    public long getLicenseId() {
        return licenseId;
    }
}

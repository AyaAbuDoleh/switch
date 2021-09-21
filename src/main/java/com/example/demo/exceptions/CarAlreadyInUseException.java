package com.example.demo.exceptions;

public class CarAlreadyInUseException extends SwitchException {

    public CarAlreadyInUseException(String msg) {
        super(msg);
    }

}

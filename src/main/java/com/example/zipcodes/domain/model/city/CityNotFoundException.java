package com.example.zipcodes.domain.model.city;

public class CityNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CityNotFoundException(final String message) {
        super(message);
    }
}

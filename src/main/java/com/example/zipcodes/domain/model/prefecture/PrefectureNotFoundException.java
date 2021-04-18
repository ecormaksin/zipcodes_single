package com.example.zipcodes.domain.model.prefecture;

public class PrefectureNotFoundException extends Exception {

    private static final long serialVersionUID = 7838051985114368634L;

    public PrefectureNotFoundException(final String message) {
        super(message);
    }
}

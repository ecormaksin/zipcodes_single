package com.example.zipcodes.domain.model.city;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CityKatakanaHalfwidthName extends CityKanaName {

    private static final long serialVersionUID = 1L;

    public CityKatakanaHalfwidthName(final String value) {
        super(value);
    }
}

package com.example.zipcodes.domain.model.city;

import com.example.zipcodes.domain.model.ValidationConcern;
import com.example.zipcodes.domain.validation.notallblank.NotAllBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
abstract class CityKanaName extends ValidationConcern {

    public static final int LENGTH_MAX = 30;

    private static final long serialVersionUID = 1L;

    @NotAllBlank(max = LENGTH_MAX)
    protected final String value;

    public CityKanaName(final String value) {
        this.value = value;
        this.validate(this);
    }
}

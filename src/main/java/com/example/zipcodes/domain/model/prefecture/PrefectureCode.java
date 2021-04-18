package com.example.zipcodes.domain.model.prefecture;

import com.example.zipcodes.domain.model.ValidationConcern;
import com.example.zipcodes.domain.validation.notallblank.NotAllBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class PrefectureCode extends ValidationConcern {

    public static final int LENGTH = 2;

    private static final long serialVersionUID = 1L;

    @NotAllBlank(min = LENGTH, max = LENGTH)
    private final String value;

    public PrefectureCode(final String value) {
        this.value = value;
        this.validate(this);
    }
}

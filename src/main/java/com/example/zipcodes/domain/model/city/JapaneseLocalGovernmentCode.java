package com.example.zipcodes.domain.model.city;

import com.example.zipcodes.domain.model.ValidationConcern;
import com.example.zipcodes.domain.validation.notallblank.NotAllBlankFixedLength;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class JapaneseLocalGovernmentCode extends ValidationConcern {

    public static final int LENGTH = 5;

    private static final long serialVersionUID = 1L;

    @NotAllBlankFixedLength(length = LENGTH)
    private final String value;

    public JapaneseLocalGovernmentCode(final String value) {
        this.value = value;
        this.validate(this);
    }
}

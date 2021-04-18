package com.example.zipcodes.domain.model.prefecture;

import com.example.zipcodes.domain.model.ValidationConcern;
import com.example.zipcodes.domain.validation.notallblank.NotAllBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class PrefectureKanjiName extends ValidationConcern {

    public static final int LENGTH_MAX = 5;

    private static final long serialVersionUID = 1L;

    @NotAllBlank(max = LENGTH_MAX)
    private final String value;

    public PrefectureKanjiName(final String value) {
        this.value = value;
        this.validate(this);
    }
}

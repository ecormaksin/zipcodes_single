package com.example.zipcodes.domain.validation.notallblank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotAllBlankValidator implements ConstraintValidator<NotAllBlank, String> {

    private NotAllBlank constraintAnnotation;

    @Override
    public void initialize(NotAllBlank constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        final int length = ValidValueLengthGetter.length(value);
        return constraintAnnotation.min() <= length && length <= constraintAnnotation.max();
    }
}

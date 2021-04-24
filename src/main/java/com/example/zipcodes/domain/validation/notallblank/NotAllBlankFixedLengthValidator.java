package com.example.zipcodes.domain.validation.notallblank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotAllBlankFixedLengthValidator implements ConstraintValidator<NotAllBlankFixedLength, String> {

    private NotAllBlankFixedLength constraintAnnotation;

    @Override
    public void initialize(NotAllBlankFixedLength constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return ValidValueLengthGetter.length(value) == constraintAnnotation.length();
    }
}

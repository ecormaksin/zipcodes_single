package com.example.zipcodes.domain.validation.notallblank;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ FIELD, ANNOTATION_TYPE, PARAMETER })
@Retention(RUNTIME)
@Repeatable(NotAllBlankFixedLength.List.class)
@Constraint(validatedBy = { NotAllBlankFixedLengthValidator.class })
public @interface NotAllBlankFixedLength {

    String message() default "{custom.validation.NotAllBlankFixedLength.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int length() default 1;

    @Target({ FIELD, ANNOTATION_TYPE, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NotAllBlankFixedLength[] value();
    }
}

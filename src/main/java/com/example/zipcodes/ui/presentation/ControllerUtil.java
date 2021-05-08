package com.example.zipcodes.ui.presentation;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public final class ControllerUtil {

    private final MessageSource messageSource;

    public static HttpHeaders jsonHttpHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));
        return httpHeaders;
    }

    public ResponseEntity<?> ok(Object object) {

        return new ResponseEntity<>(object, jsonHttpHeaders(), HttpStatus.OK);
    }

    public GeneralErrorObject generalErrorObject(final String messageCode, @Nullable Object[] messageArgs) {

        final String errorMessage = messageSource.getMessage(messageCode, messageArgs, Locale.getDefault());
        // @formatter:off
        return GeneralErrorObject.builder()
            .errorMessage(errorMessage)
            .build();
        // @formatter:on
    }

    private ResponseEntity<GeneralErrorObject> generalErrorResponseEntity(final String messageCode,
            @Nullable Object[] messageArgs, HttpStatus httpStatus) {

        GeneralErrorObject errorResponse = generalErrorObject(messageCode, messageArgs);

        return new ResponseEntity<>(errorResponse, jsonHttpHeaders(), httpStatus);
    }

    public ResponseEntity<GeneralErrorObject> notFound(final String messageCode) {

        return generalErrorResponseEntity(messageCode, null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<GeneralErrorObject> notFound(final String messageCode, @Nullable Object[] messageArgs) {

        return generalErrorResponseEntity(messageCode, messageArgs, HttpStatus.NOT_FOUND);
    }
}

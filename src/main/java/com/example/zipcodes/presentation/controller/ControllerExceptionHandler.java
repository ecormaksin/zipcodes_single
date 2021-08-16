package com.example.zipcodes.presentation.controller;

import static com.example.zipcodes.presentation.controller.ControllerUtil.jsonHttpHeaders;
import static com.example.zipcodes.presentation.controller.KeyNames.KEYWORDS;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final ControllerUtil controllerUtil;

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        String messageCode = "message.bad.request";
        Object[] messageArgs = null;
        if (ex.getParameterName().equals(KEYWORDS)) {
            messageCode = "message.request.params.not.specified";
            messageArgs = new Object[] { KEYWORDS };
        }
        GeneralErrorObject generalErrorObject = controllerUtil.generalErrorObject(messageCode, messageArgs);

        return new ResponseEntity<>(generalErrorObject, jsonHttpHeaders(), status);
    }
}

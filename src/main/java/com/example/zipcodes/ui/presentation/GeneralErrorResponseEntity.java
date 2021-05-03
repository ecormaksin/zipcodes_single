package com.example.zipcodes.ui.presentation;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
// @formatter:off
@JsonPropertyOrder({ 
    "errorMessage"
    })
// @formatter:on
@Data
@Builder
public class GeneralErrorResponseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("errorMessage")
    private String errorMessage;
}

package com.example.zipcodes.ui.presentation.city;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
// @formatter:off
@JsonPropertyOrder({ 
    "japaneseLocalGovernmentCode"
    , "kanjiName"
    , "hiraganaName"
    , "katakanaFullwidthName"
    , "katakanaHalfwidthName" })
// @formatter:on
@Data
@Builder
public class CityDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("japaneseLocalGovernmentCode")
    private String japaneseLocalGovernmentCode;

    @JsonProperty("kanjiName")
    private String kanjiName;

    @JsonProperty("hiraganaName")
    private String hiraganaName;

    @JsonProperty("katakanaFullwidthName")
    private String katakanaFullwidthName;

    @JsonProperty("katakanaHalfwidthName")
    private String katakanaHalfwidthName;
}

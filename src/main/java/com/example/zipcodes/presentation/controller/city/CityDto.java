package com.example.zipcodes.presentation.controller.city;

import static com.example.zipcodes.presentation.controller.KeyNames.HIRAGANA_NAME;
import static com.example.zipcodes.presentation.controller.KeyNames.JAPANESE_LOCAL_GOVERNMENT_CODE;
import static com.example.zipcodes.presentation.controller.KeyNames.KANJI_NAME;
import static com.example.zipcodes.presentation.controller.KeyNames.KATAKANA_FULLWIDTH_NAME;
import static com.example.zipcodes.presentation.controller.KeyNames.KATAKANA_HALFWIDTH_NAME;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
// @formatter:off
@JsonPropertyOrder({ 
    JAPANESE_LOCAL_GOVERNMENT_CODE
    , KANJI_NAME
    , HIRAGANA_NAME
    , KATAKANA_FULLWIDTH_NAME
    , KATAKANA_HALFWIDTH_NAME })
// @formatter:on
@Data
@Builder
public class CityDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(JAPANESE_LOCAL_GOVERNMENT_CODE)
    private String japaneseLocalGovernmentCode;

    @JsonProperty(KANJI_NAME)
    private String kanjiName;

    @JsonProperty(HIRAGANA_NAME)
    private String hiraganaName;

    @JsonProperty(KATAKANA_FULLWIDTH_NAME)
    private String katakanaFullwidthName;

    @JsonProperty(KATAKANA_HALFWIDTH_NAME)
    private String katakanaHalfwidthName;
}

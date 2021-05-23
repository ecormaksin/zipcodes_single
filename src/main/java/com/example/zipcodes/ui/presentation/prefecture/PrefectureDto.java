package com.example.zipcodes.ui.presentation.prefecture;

import static com.example.zipcodes.ui.presentation.KeyNames.HIRAGANA_NAME;
import static com.example.zipcodes.ui.presentation.KeyNames.KANJI_NAME;
import static com.example.zipcodes.ui.presentation.KeyNames.KATAKANA_FULLWIDTH_NAME;
import static com.example.zipcodes.ui.presentation.KeyNames.KATAKANA_HALFWIDTH_NAME;
import static com.example.zipcodes.ui.presentation.KeyNames.PREFECTURE_CODE;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
// @formatter:off
@JsonPropertyOrder({ 
    PREFECTURE_CODE
    , KANJI_NAME
    , HIRAGANA_NAME
    , KATAKANA_FULLWIDTH_NAME
    , KATAKANA_HALFWIDTH_NAME })
// @formatter:on
@Data
@Builder
public class PrefectureDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(PREFECTURE_CODE)
    private String code;

    @JsonProperty(KANJI_NAME)
    private String kanjiName;

    @JsonProperty(HIRAGANA_NAME)
    private String hiraganaName;

    @JsonProperty(KATAKANA_FULLWIDTH_NAME)
    private String katakanaFullwidthName;

    @JsonProperty(KATAKANA_HALFWIDTH_NAME)
    private String katakanaHalfwidthName;
}

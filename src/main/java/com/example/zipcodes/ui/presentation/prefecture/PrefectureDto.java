package com.example.zipcodes.ui.presentation.prefecture;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "prefectureCode", "prefectureKanjiName", "prefectureHiraganaName",
        "prefectureKatakanaFullwidthName", "prefectureKatakanaHalfwidthName" })
@Data
@Builder
public class PrefectureDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("prefectureCode")
    private String code;

    @JsonProperty("prefectureKanjiName")
    private String kanjiName;

    @JsonProperty("prefectureHiraganaName")
    private String hiraganaName;

    @JsonProperty("prefectureKatakanaFullwidthName")
    private String katakanaFullwidthName;

    @JsonProperty("prefectureKatakanaHalfwidthName")
    private String katakanaHalfwidthName;
}

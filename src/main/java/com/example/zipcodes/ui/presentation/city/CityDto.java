package com.example.zipcodes.ui.presentation.city;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "prefectureCode", "prefectureKanjiName", "prefectureHiraganaName",
        "prefectureKatakanaFullwidthName", "prefectureKatakanaHalfwidthName", "japaneseLocalGovernmentCode",
        "cityKanjiName", "cityHiraganaName", "cityKatakanaFullwidthName",
        "cityKatakanaHalfwidthName" })
@Data
@Builder
public class CityDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("prefectureCode")
    private String prefectureCode;

    @JsonProperty("prefectureKanjiName")
    private String prefectureKanjiName;

    @JsonProperty("prefectureHiraganaName")
    private String prefectureHiraganaName;

    @JsonProperty("prefectureKatakanaFullwidthName")
    private String prefectureKatakanaFullwidthName;

    @JsonProperty("prefectureKatakanaHalfwidthName")
    private String prefectureKatakanaHalfwidthName;

    @JsonProperty("japaneseLocalGovernmentCode")
    private String japaneseLocalGovernmentCode;

    @JsonProperty("cityKanjiName")
    private String cityKanjiName;

    @JsonProperty("cityHiraganaName")
    private String cityHiraganaName;

    @JsonProperty("cityKatakanaFullwidthName")
    private String cityKatakanaFullwidthName;

    @JsonProperty("cityKatakanaHalfwidthName")
    private String cityKatakanaHalfwidthName;
}

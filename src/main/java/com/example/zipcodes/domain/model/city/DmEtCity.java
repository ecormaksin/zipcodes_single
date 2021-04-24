package com.example.zipcodes.domain.model.city;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureHiraganaName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKanjiName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKatakanaFullwidthName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKatakanaHalfwidthName;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
public class DmEtCity {

    @Valid
    @NotNull
    private PrefectureCode prefectureCode;

    @Setter
    @Valid
    @NotNull
    private PrefectureKanjiName prefectureKanjiName;

    @Setter
    @Valid
    @NotNull
    private PrefectureHiraganaName prefectureHiraganaName;

    @Setter
    @Valid
    @NotNull
    private PrefectureKatakanaFullwidthName prefectureKatakanaFullwidthName;

    @Setter
    @Valid
    @NotNull
    private PrefectureKatakanaHalfwidthName prefectureKatakanaHalfwidthName;

    @Valid
    @NotNull
    private JapaneseLocalGovernmentCode japaneseLocalGovernmentCode;

    @Setter
    @Valid
    @NotNull
    private CityKanjiName cityKanjiName;

    @Setter
    @Valid
    @NotNull
    private CityHiraganaName cityHiraganaName;

    @Setter
    @Valid
    @NotNull
    private CityKatakanaFullwidthName cityKatakanaFullwidthName;

    @Setter
    @Valid
    @NotNull
    private CityKatakanaHalfwidthName cityKatakanaHalfwidthName;

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return "\"dmEtCity\": " + objectMapper.writeValueAsString(this);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean equals(Object other) {

        if (null == other) {
            return false;
        }
        if (!(other instanceof DmEtCity)) {
            return false;
        }

        DmEtCity otherCity = (DmEtCity) other;
        return this.japaneseLocalGovernmentCode.getValue().equals(otherCity.japaneseLocalGovernmentCode.getValue());
    }

    @Override
    public int hashCode() {
        return this.japaneseLocalGovernmentCode.getValue().hashCode();
    }
}

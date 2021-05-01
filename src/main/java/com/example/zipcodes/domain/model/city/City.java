package com.example.zipcodes.domain.model.city;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
public class City {

    @Valid
    @NotNull
    private PrefectureCode prefectureCode;

    @Valid
    @NotNull
    private JapaneseLocalGovernmentCode japaneseLocalGovernmentCode;

    @Setter
    @Valid
    @NotNull
    private CityKanjiName kanjiName;

    @Setter
    @Valid
    @NotNull
    private CityHiraganaName hiraganaName;

    @Setter
    @Valid
    @NotNull
    private CityKatakanaFullwidthName katakanaFullwidthName;

    @Setter
    @Valid
    @NotNull
    private CityKatakanaHalfwidthName katakanaHalfwidthName;

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return "\"city\": " + objectMapper.writeValueAsString(this);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean equals(Object other) {

        if (null == other) {
            return false;
        }
        if (!(other instanceof City)) {
            return false;
        }

        City otherCity = (City) other;
        return this.japaneseLocalGovernmentCode.getValue().equals(otherCity.japaneseLocalGovernmentCode.getValue());
    }

    @Override
    public int hashCode() {
        return this.japaneseLocalGovernmentCode.getValue().hashCode();
    }
}

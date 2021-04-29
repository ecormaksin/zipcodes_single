package com.example.zipcodes.domain.model.prefecture;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
public class Prefecture {

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

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return "\"dmEtPrefecture\": " + objectMapper.writeValueAsString(this);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean equals(Object other) {

        if (null == other) {
            return false;
        }
        if (!(other instanceof Prefecture)) {
            return false;
        }

        Prefecture otherPrefecture = (Prefecture) other;
        return this.prefectureCode.getValue().equals(otherPrefecture.prefectureCode.getValue());
    }

    @Override
    public int hashCode() {
        return this.prefectureCode.getValue().hashCode();
    }
}

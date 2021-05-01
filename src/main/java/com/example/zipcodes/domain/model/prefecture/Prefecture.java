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
    private PrefectureCode code;

    @Setter
    @Valid
    @NotNull
    private PrefectureKanjiName kanjiName;

    @Setter
    @Valid
    @NotNull
    private PrefectureHiraganaName hiraganaName;

    @Setter
    @Valid
    @NotNull
    private PrefectureKatakanaFullwidthName katakanaFullwidthName;

    @Setter
    @Valid
    @NotNull
    private PrefectureKatakanaHalfwidthName katakanaHalfwidthName;

    @Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return "\"prefecture\": " + objectMapper.writeValueAsString(this);
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
        return this.code.getValue().equals(otherPrefecture.code.getValue());
    }

    @Override
    public int hashCode() {
        return this.code.getValue().hashCode();
    }
}

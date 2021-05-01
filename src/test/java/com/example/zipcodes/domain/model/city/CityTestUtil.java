package com.example.zipcodes.domain.model.city;

import com.example.zipcodes.domain.model.prefecture.PrefectureTestUtil;

public final class CityTestUtil {

    // @formatter:off
    public static final JapaneseLocalGovernmentCode JP_LOCAL_GOV_CODE_SHINJUKUKU
        = new JapaneseLocalGovernmentCode("13104");
    public static final JapaneseLocalGovernmentCode JP_LOCAL_GOV_CODE_NOT_EXIST
        = new JapaneseLocalGovernmentCode("99999");
    // @formatter:on

    public static City shinjukuku() {
        // @formatter:off
        return City.builder()
            .prefectureCode(PrefectureTestUtil.PREFECTURE_CODE_TOKYO)
            .japaneseLocalGovernmentCode(JP_LOCAL_GOV_CODE_SHINJUKUKU)
            .kanjiName(new CityKanjiName("新宿区"))
            .hiraganaName(new CityHiraganaName("しんじゅくく"))
            .katakanaFullwidthName(new CityKatakanaFullwidthName("シンジュクク"))
            .katakanaHalfwidthName(new CityKatakanaHalfwidthName("ｼﾝｼﾞｭｸｸ"))
            .build();
        // @formatter:on
    }
}

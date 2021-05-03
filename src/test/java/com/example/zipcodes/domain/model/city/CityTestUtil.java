package com.example.zipcodes.domain.model.city;

import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureTestUtil;

public final class CityTestUtil {

    // @formatter:off
    private static final PrefectureCode PREFECTURE_CODE_TOKYO = PrefectureTestUtil.PREFECTURE_CODE_TOKYO;
    public static final JapaneseLocalGovernmentCode JP_LOCAL_GOV_CODE_SHINJUKUKU
        = new JapaneseLocalGovernmentCode("13104");
    public static final JapaneseLocalGovernmentCode JP_LOCAL_GOV_CODE_NOT_EXIST
        = new JapaneseLocalGovernmentCode("99999");
    // @formatter:on

    public static City chiyodaku() {
        // @formatter:off
        return City.builder()
            .prefectureCode(PREFECTURE_CODE_TOKYO)
            .japaneseLocalGovernmentCode(new JapaneseLocalGovernmentCode("13101"))
            .kanjiName(new CityKanjiName("千代田区"))
            .hiraganaName(new CityHiraganaName("ちよだく"))
            .katakanaFullwidthName(new CityKatakanaFullwidthName("チヨダク"))
            .katakanaHalfwidthName(new CityKatakanaHalfwidthName("ﾁﾖﾀﾞｸ"))
            .build();
        // @formatter:on
    }

    public static City chuoku() {
        // @formatter:off
        return City.builder()
            .prefectureCode(PREFECTURE_CODE_TOKYO)
            .japaneseLocalGovernmentCode(new JapaneseLocalGovernmentCode("13102"))
            .kanjiName(new CityKanjiName("中央区"))
            .hiraganaName(new CityHiraganaName("ちゅうおうく"))
            .katakanaFullwidthName(new CityKatakanaFullwidthName("チュウオウク"))
            .katakanaHalfwidthName(new CityKatakanaHalfwidthName("ﾁｭｳｵｳｸ"))
            .build();
        // @formatter:on
    }

    public static City minatoku() {
        // @formatter:off
        return City.builder()
            .prefectureCode(PREFECTURE_CODE_TOKYO)
            .japaneseLocalGovernmentCode(new JapaneseLocalGovernmentCode("13103"))
            .kanjiName(new CityKanjiName("港区"))
            .hiraganaName(new CityHiraganaName("みなとく"))
            .katakanaFullwidthName(new CityKatakanaFullwidthName("ミナトク"))
            .katakanaHalfwidthName(new CityKatakanaHalfwidthName("ﾐﾅﾄｸ"))
            .build();
        // @formatter:on
    }

    public static City shinjukuku() {
        // @formatter:off
        return City.builder()
            .prefectureCode(PREFECTURE_CODE_TOKYO)
            .japaneseLocalGovernmentCode(JP_LOCAL_GOV_CODE_SHINJUKUKU)
            .kanjiName(new CityKanjiName("新宿区"))
            .hiraganaName(new CityHiraganaName("しんじゅくく"))
            .katakanaFullwidthName(new CityKatakanaFullwidthName("シンジュクク"))
            .katakanaHalfwidthName(new CityKatakanaHalfwidthName("ｼﾝｼﾞｭｸｸ"))
            .build();
        // @formatter:on
    }
}

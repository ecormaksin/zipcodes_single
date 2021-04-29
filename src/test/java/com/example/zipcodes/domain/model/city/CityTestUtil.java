package com.example.zipcodes.domain.model.city;

import com.example.zipcodes.domain.model.prefecture.PrefectureHiraganaName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKanjiName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKatakanaFullwidthName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKatakanaHalfwidthName;
import com.example.zipcodes.domain.model.prefecture.PrefectureTestUtil;

public final class CityTestUtil {

    // @formatter:off
    public static final JapaneseLocalGovernmentCode JP_LOCAL_GOV_CODE_TOKYOTO_SHINJUKUKU
        = new JapaneseLocalGovernmentCode("13104");
    public static final JapaneseLocalGovernmentCode JP_LOCAL_GOV_CODE_UNKNOWN
    = new JapaneseLocalGovernmentCode("99999");
    // @formatter:on

    public static City tokyotoShinjukuku() {
        // @formatter:off
        return City.builder()
            .prefectureCode(PrefectureTestUtil.PREFECTURE_CODE_TOKYO)
            .prefectureKanjiName(new PrefectureKanjiName("東京都"))
            .prefectureHiraganaName(new PrefectureHiraganaName("とうきょうと"))
            .prefectureKatakanaFullwidthName(new PrefectureKatakanaFullwidthName("トウキョウト"))
            .prefectureKatakanaHalfwidthName(new PrefectureKatakanaHalfwidthName("ﾄｳｷｮｳﾄ"))
            .japaneseLocalGovernmentCode(JP_LOCAL_GOV_CODE_TOKYOTO_SHINJUKUKU)
            .cityKanjiName(new CityKanjiName("新宿区"))
            .cityHiraganaName(new CityHiraganaName("しんじゅくく"))
            .cityKatakanaFullwidthName(new CityKatakanaFullwidthName("シンジュクク"))
            .cityKatakanaHalfwidthName(new CityKatakanaHalfwidthName("ｼﾝｼﾞｭｸｸ"))
            .build();
        // @formatter:on
    }

    public static City unknown() {
        // @formatter:off
        return City.builder()
            .prefectureCode(PrefectureTestUtil.PREFECTURE_CODE_UNKNOWN)
            .prefectureKanjiName(new PrefectureKanjiName("不明"))
            .prefectureHiraganaName(new PrefectureHiraganaName("ふめい"))
            .prefectureKatakanaFullwidthName(new PrefectureKatakanaFullwidthName("フメイ"))
            .prefectureKatakanaHalfwidthName(new PrefectureKatakanaHalfwidthName("ﾌﾒｲ"))
            .japaneseLocalGovernmentCode(JP_LOCAL_GOV_CODE_UNKNOWN)
            .cityKanjiName(new CityKanjiName("不明"))
            .cityHiraganaName(new CityHiraganaName("ふめい"))
            .cityKatakanaFullwidthName(new CityKatakanaFullwidthName("フメイ"))
            .cityKatakanaHalfwidthName(new CityKatakanaHalfwidthName("ﾌﾒｲ"))
            .build();
        // @formatter:on
    }
}

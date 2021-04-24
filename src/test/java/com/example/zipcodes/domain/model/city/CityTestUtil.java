package com.example.zipcodes.domain.model.city;

import com.example.zipcodes.domain.model.prefecture.PrefectureHiraganaName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKanjiName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKatakanaFullwidthName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKatakanaHalfwidthName;
import com.example.zipcodes.domain.model.prefecture.PrefectureTestUtil;

public final class CityTestUtil {

    public static final JapaneseLocalGovernmentCode JP_LOCAL_GOV_CODE_TOKYOTO_SHINJUKUKU = new JapaneseLocalGovernmentCode(
            "13104");

    public static DmEtCity tokyotoShinjukuku() {
        // @formatter:off
        return DmEtCity.builder()
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
}

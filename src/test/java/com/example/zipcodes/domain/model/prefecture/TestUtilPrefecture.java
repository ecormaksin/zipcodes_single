package com.example.zipcodes.domain.model.prefecture;

public class TestUtilPrefecture {

    public static final String PREFECTURE_CODE_TOKYO = "13";
    public static final String PREFECTURE_CODE_KYOTO = "26";

    public static DmEtPrefecture domainEntityTokyo() {

        // @formatter:off
    	return DmEtPrefecture.builder()
    	    .code(new PrefectureCode(PREFECTURE_CODE_TOKYO))
	        .kanjiName(new PrefectureKanjiName("東京都"))
	        .hiraganaName(new PrefectureHiraganaName("とうきょうと"))
	        .katakanaFullwidthName(new PrefectureKatakanaFullwidthName("トウキョウト"))
	        .katakanaHalfwidthName(new PrefectureKatakanaHalfwidthName("ﾄｳｷｮｳﾄ"))
	        .build();
    	// @formatter:on
    }

    public static DmEtPrefecture domainEntityKyoto() {

        // @formatter:off
        return DmEtPrefecture.builder()
            .code( new PrefectureCode(PREFECTURE_CODE_KYOTO) )
            .kanjiName( new PrefectureKanjiName("京都府") )
            .hiraganaName( new PrefectureHiraganaName("きょうとふ") )
            .katakanaFullwidthName( new PrefectureKatakanaFullwidthName("キョウトフ") )
            .katakanaHalfwidthName( new PrefectureKatakanaHalfwidthName("ｷｮｳﾄﾌ") )
            .build();
        // @formatter:on
    }
}

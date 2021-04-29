package com.example.zipcodes.domain.model.prefecture;

public final class PrefectureTestUtil {

    public static final PrefectureCode PREFECTURE_CODE_TOKYO = new PrefectureCode("13");
    public static final PrefectureCode PREFECTURE_CODE_KYOTO = new PrefectureCode("26");
    public static final PrefectureCode PREFECTURE_CODE_NOT_EXIST = new PrefectureCode("99");

    public static Prefecture tokyoto() {

        // @formatter:off
    	return Prefecture.builder()
    	    .prefectureCode(PREFECTURE_CODE_TOKYO)
	        .prefectureKanjiName(new PrefectureKanjiName("東京都"))
	        .prefectureHiraganaName(new PrefectureHiraganaName("とうきょうと"))
	        .prefectureKatakanaFullwidthName(new PrefectureKatakanaFullwidthName("トウキョウト"))
	        .prefectureKatakanaHalfwidthName(new PrefectureKatakanaHalfwidthName("ﾄｳｷｮｳﾄ"))
	        .build();
    	// @formatter:on
    }

    public static Prefecture kyotofu() {

        // @formatter:off
        return Prefecture.builder()
            .prefectureCode( PREFECTURE_CODE_KYOTO )
            .prefectureKanjiName( new PrefectureKanjiName("京都府") )
            .prefectureHiraganaName( new PrefectureHiraganaName("きょうとふ") )
            .prefectureKatakanaFullwidthName( new PrefectureKatakanaFullwidthName("キョウトフ") )
            .prefectureKatakanaHalfwidthName( new PrefectureKatakanaHalfwidthName("ｷｮｳﾄﾌ") )
            .build();
        // @formatter:on
    }
}

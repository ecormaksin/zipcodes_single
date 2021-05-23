package com.example.zipcodes.domain.model.prefecture;

public final class PrefectureTestUtil {

    public static final PrefectureCode PREFECTURE_CODE_TOKYO = new PrefectureCode("13");
    public static final String PREFECTURE_CODE_TOKYO_STR = PREFECTURE_CODE_TOKYO.getValue();
    public static final PrefectureCode PREFECTURE_CODE_KYOTO = new PrefectureCode("26");
    public static final PrefectureCode PREFECTURE_CODE_NOT_EXIST = new PrefectureCode("99");
    public static final String PREFECTURE_CODE_NOT_EXIST_STR = PREFECTURE_CODE_NOT_EXIST.getValue();

    public static Prefecture tokyoto() {

        // @formatter:off
    	return Prefecture.builder()
    	    .code(PREFECTURE_CODE_TOKYO)
	        .kanjiName(new PrefectureKanjiName("東京都"))
	        .hiraganaName(new PrefectureHiraganaName("とうきょうと"))
	        .katakanaFullwidthName(new PrefectureKatakanaFullwidthName("トウキョウト"))
	        .katakanaHalfwidthName(new PrefectureKatakanaHalfwidthName("ﾄｳｷｮｳﾄ"))
	        .build();
    	// @formatter:on
    }

    public static Prefecture kyotofu() {

        // @formatter:off
        return Prefecture.builder()
            .code( PREFECTURE_CODE_KYOTO )
            .kanjiName( new PrefectureKanjiName("京都府") )
            .hiraganaName( new PrefectureHiraganaName("きょうとふ") )
            .katakanaFullwidthName( new PrefectureKatakanaFullwidthName("キョウトフ") )
            .katakanaHalfwidthName( new PrefectureKatakanaHalfwidthName("ｷｮｳﾄﾌ") )
            .build();
        // @formatter:on
    }
}

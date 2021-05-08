package com.example.zipcodes.infra.repository;

import com.ibm.icu.text.Transliterator;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;

class KeywordsConditionBuilder {

    public static BooleanBuilder build(final StringPath kanjiNameStringPath, final StringPath kanaNameStringPath,
            final String keywords) {

        final Transliterator hiraganaTransliterator = Transliterator.getInstance("Hiragana-Katakana");
        final Transliterator katakanaTransliterator = Transliterator.getInstance("Fullwidth-Halfwidth");

        final String[] keywordArray = keywords.split(" ");

        BooleanBuilder kanjiNameCondition = new BooleanBuilder();
        BooleanBuilder kanaNameCondition = new BooleanBuilder();
        for (String keyword : keywordArray) {
            kanjiNameCondition.and(kanjiNameStringPath.like(keywordLikeString(keyword)));

            final String katakanaKeyword = katakanaTransliterator
                    .transliterate(hiraganaTransliterator.transliterate(keyword));
            kanaNameCondition.and(kanaNameStringPath.like(keywordLikeString(katakanaKeyword)));
        }

        return kanjiNameCondition.or(kanaNameCondition);
    }

    private static String keywordLikeString(final String keyword) {

        return "%" + keyword + "%";
    }
}

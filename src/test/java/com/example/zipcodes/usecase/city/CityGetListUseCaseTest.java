package com.example.zipcodes.usecase.city;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.city.CityTestUtil;
import com.example.zipcodes.domain.model.prefecture.PrefectureTestUtil;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@AutoConfigureTestEntityManager
@Slf4j
class CityGetListUseCaseTest {

    @Autowired
    private CityGetListUseCase cityGetListUseCase;

    @Test
    void 東京都の市区町村は62件() {

        List<City> cities = cityGetListUseCase.findByPrefectureCode(PrefectureTestUtil.PREFECTURE_CODE_TOKYO);

        assertEquals(62, cities.size());

        // @formatter:off
        cities.stream()
            .forEach(city -> {
                log.info(city.toString());
            });
        // @formatter:on
    }

    @Test
    @DisplayName("都道府県コード指定なし、かつ、検索キーワード指定ありの時はキーワードに該当する市区町村が返ってくる(漢字で検索)")
    void findByKanjiKeywords() throws Exception {

        testShinjukuku("新 宿");
    }

    @Test
    @DisplayName("都道府県コード指定なし、かつ、検索キーワード指定ありの時はキーワードに該当する市区町村が返ってくる(ひらがなで検索)")
    void findByHiraganaKeywords() throws Exception {

        testShinjukuku("しん じゅく");
    }

    @Test
    @DisplayName("都道府県コード指定なし、かつ、検索キーワード指定ありの時はキーワードに該当する市区町村が返ってくる(全角カタカナで検索)")
    void findByFullwidthKatakanaKeywords() throws Exception {

        testShinjukuku("シン ジュク");
    }

    @Test
    @DisplayName("都道府県コード指定なし、かつ、検索キーワード指定ありの時はキーワードに該当する市区町村が返ってくる(半角ｶﾀｶﾅで検索)")
    void findByHalfwidthKatakanaKeywords() throws Exception {

        testShinjukuku("ｼﾝ ｼﾞｭｸ");
    }

    private void testShinjukuku(final String keywords) throws Exception {

        List<City> cities = cityGetListUseCase.findByKeywords(keywords);

        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertTrue(CityTestUtil.shinjukuku().equals(city));
    }

    @Test
    @DisplayName("都道府県コード指定なし、かつ、検索キーワードに該当する市区町村が存在しない時リストは0件")
    void findByKeywordsNotFound() throws Exception {

        List<City> cities = cityGetListUseCase.findByKeywords("あいうえお");

        assertEquals(0, cities.size());
    }
}

package com.example.zipcodes.usecase.prefecture;

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

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureTestUtil;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@AutoConfigureTestEntityManager
@Slf4j
class PrefectureGetListUseCaseTest {

    @Autowired
    private PrefectureGetListUseCase prefectureGetListUseCase;

    @Test
    void 都道府県リストは47件() {

        List<Prefecture> prefectures = prefectureGetListUseCase.findAll();

        assertEquals(47, prefectures.size());

        // @formatter:off
        prefectures.stream()
            .forEach(prefecture -> {
                log.info(prefecture.toString());
            });
        // @formatter:on
    }

    @Test
    @DisplayName("検索キーワード指定ありの時はキーワードに該当する都道府県が返ってくる(漢字で検索)")
    void findByKanjiKeywords() throws Exception {

        testTokyoto("東 京");
    }

    private void testTokyoto(final String keywords) throws Exception {

        List<Prefecture> prefectures = prefectureGetListUseCase.findByKeywords(keywords);

        assertEquals(1, prefectures.size());

        Prefecture prefecture = prefectures.get(0);
        assertTrue(PrefectureTestUtil.tokyoto().equals(prefecture));
    }

}

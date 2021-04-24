package com.example.zipcodes.usecase.prefecture;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;

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

        List<DmEtPrefecture> list = prefectureGetListUseCase.findAll();

        assertEquals(47, list.size());

        for (DmEtPrefecture obj : list) {
            log.info(obj.toString());
        }
    }
}

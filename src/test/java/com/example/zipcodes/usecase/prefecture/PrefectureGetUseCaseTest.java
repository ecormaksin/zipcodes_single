package com.example.zipcodes.usecase.prefecture;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureTestUtil;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@AutoConfigureTestEntityManager
class PrefectureGetUseCaseTest {

    @Autowired
    private PrefectureGetUseCase prefectureGetUseCase;

    @Test
    void 都道府県コードに13を指定した場合は東京都が返ってくる() throws Exception {

        Optional<Prefecture> actual = prefectureGetUseCase.get(PrefectureTestUtil.PREFECTURE_CODE_TOKYO);

        assertTrue(actual.get().equals(PrefectureTestUtil.tokyoto()));
    }

    @Test
    void 存在しない都道府県コードを指定した場合は空が返ってくる() throws Exception {

        Optional<Prefecture> actual = prefectureGetUseCase.get(PrefectureTestUtil.PREFECTURE_CODE_NOT_EXIST);

        assertFalse(actual.isPresent());
    }

}

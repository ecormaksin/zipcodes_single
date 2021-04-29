package com.example.zipcodes.usecase.prefecture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureNotFoundException;
import com.example.zipcodes.domain.model.prefecture.PrefectureTestUtil;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@AutoConfigureTestEntityManager
class PrefectureGetUseCaseTest {

    private static final PrefectureCode PREFECTURE_CODE_TOKYO = PrefectureTestUtil.PREFECTURE_CODE_TOKYO;
    private static final PrefectureCode PREFECTURE_CODE_NOT_EXIST = PrefectureTestUtil.PREFECTURE_CODE_NOT_EXIST;

    @Autowired
    private PrefectureGetUseCase prefectureGetUseCase;

    @Test
    void 都道府県コードに13を指定した場合は東京都が返ってくる() throws Exception {

        Prefecture expected = PrefectureTestUtil.tokyoto();
        Prefecture actual = prefectureGetUseCase.get(PREFECTURE_CODE_TOKYO);

        assertTrue(actual.equals(expected));
    }

    @Test
    void 存在しない都道府県コードを指定した場合は例外が発生する() {

        // @formatter:off
        PrefectureNotFoundException exception = assertThrows(PrefectureNotFoundException.class, () -> {
            prefectureGetUseCase.get(PREFECTURE_CODE_NOT_EXIST);
        });
        // @formatter:on
        assertEquals("都道府県コード: 99 に対応する情報はありません。", exception.getMessage());
    }

}

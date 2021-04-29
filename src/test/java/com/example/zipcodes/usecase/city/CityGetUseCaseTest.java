package com.example.zipcodes.usecase.city;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.city.CityNotFoundException;
import com.example.zipcodes.domain.model.city.CityTestUtil;
import com.example.zipcodes.domain.model.city.JapaneseLocalGovernmentCode;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@AutoConfigureTestEntityManager
class CityGetUseCaseTest {

    // @formatter:off
    private static final JapaneseLocalGovernmentCode JP_LOCAL_GOV_CODE_TOKYOTO_SHINJUKUKU = CityTestUtil.JP_LOCAL_GOV_CODE_TOKYOTO_SHINJUKUKU;
    private static final JapaneseLocalGovernmentCode JP_LOCAL_GOV_CODE_NOT_EXIST = CityTestUtil.JP_LOCAL_GOV_CODE_NOT_EXIST;
    // @formatter:on

    @Autowired
    private CityGetUseCase cityGetUseCase;

    @Test
    void 地方自治体コード13104を指定した場合は東京都新宿区が返ってくる() throws Exception {

        City expected = CityTestUtil.tokyotoShinjukuku();
        City actual = cityGetUseCase.get(JP_LOCAL_GOV_CODE_TOKYOTO_SHINJUKUKU);

        assertTrue(actual.equals(expected));
    }

    @Test
    void 存在しない地方自治体コードを指定した場合は例外が発生する() {

        // @formatter:off
        CityNotFoundException exception = assertThrows(CityNotFoundException.class, () -> {
            cityGetUseCase.get(JP_LOCAL_GOV_CODE_NOT_EXIST);
        });
        // @formatter:on
        assertEquals(String.format("地方自治体コード: %s に対応する情報はありません。", JP_LOCAL_GOV_CODE_NOT_EXIST.getValue()),
                exception.getMessage());
    }
}

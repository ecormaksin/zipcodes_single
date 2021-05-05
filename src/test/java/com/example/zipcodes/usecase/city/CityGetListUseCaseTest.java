package com.example.zipcodes.usecase.city;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.example.zipcodes.domain.model.city.City;
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

}

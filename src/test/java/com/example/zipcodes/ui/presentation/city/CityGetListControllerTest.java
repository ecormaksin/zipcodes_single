package com.example.zipcodes.ui.presentation.city;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

@WebMvcTest(CityGetListController.class)
@Import(CityDtoMapperImpl.class)
class CityGetListControllerTest {

    @Test
    void 都道府県コード指定あり_検索キーワード指定なしの時は都道府県内のすべての市区町村が返ってくる() {

        List<CityDto> dtos = new ArrayList<>();

        // @formatter:off
        dtos.add(CityDto.builder()
                .build());
        // @formatter:on
    }

}

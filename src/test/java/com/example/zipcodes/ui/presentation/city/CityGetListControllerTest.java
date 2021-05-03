package com.example.zipcodes.ui.presentation.city;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.city.CityTestUtil;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureTestUtil;
import com.example.zipcodes.ui.presentation.ControllerUtil;
import com.example.zipcodes.ui.presentation.EndpointUrls;
import com.example.zipcodes.usecase.city.CityGetListUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CityGetListController.class)
@Import(value = { CityDtoMapperImpl.class, ControllerUtil.class })
class CityGetListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityGetListUseCase cityGetListUseCase;

    @Autowired
    private CityDtoMapper cityDtoMapper;

    @Test
    void 都道府県コード指定あり_検索キーワード指定なしの時は都道府県内のすべての市区町村が返ってくる() throws Exception {

        // 便宜的に東京都の地方自治体コード昇順上位4件をサンプルとして返す
        // @formatter:off
        List<City> cities = Arrays.asList(
                CityTestUtil.chiyodaku()
                , CityTestUtil.chuoku()
                , CityTestUtil.minatoku()
                , CityTestUtil.shinjukuku()
                );
        // @formatter:on

        List<CityDto> dtos = new ArrayList<>();
        // @formatter:off
        cities.stream()
            .forEach(city -> {
                dtos.add(cityDtoMapper.fromDomainObjectToDto(city));
            });
        // @formatter:on

        final PrefectureCode PREFECTURE_CODE_TOKYO = PrefectureTestUtil.PREFECTURE_CODE_TOKYO;
        when(cityGetListUseCase.findByPrefectureCode(PREFECTURE_CODE_TOKYO)).thenReturn(cities);

        ObjectMapper objectMapper = new ObjectMapper();
        // @formatter:off
        mockMvc.perform(get(EndpointUrls.PREFECTURES_GET_LIST + "/" + PREFECTURE_CODE_TOKYO.getValue() + EndpointUrls.CITIES_GET_LIST))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(dtos)));
        // @formatter:on
    }

}

package com.example.zipcodes.ui.presentation.city;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.city.CityNotFoundException;
import com.example.zipcodes.domain.model.city.CityTestUtil;
import com.example.zipcodes.domain.model.city.JapaneseLocalGovernmentCode;
import com.example.zipcodes.ui.presentation.EndpointUrls;
import com.example.zipcodes.usecase.city.CityGetUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CityGetController.class)
@Import(CityDtoMapperImpl.class)
class CityGetControllerTest {

    // @formatter:off
    private static final JapaneseLocalGovernmentCode JP_LOCAL_GOV_CODE_TOKYOTO_SHINJUKUKU = CityTestUtil.JP_LOCAL_GOV_CODE_TOKYOTO_SHINJUKUKU;
    private static final String JP_LOCAL_GOV_CODE_TOKYOTO_SHINJUKUKU_STR
        = JP_LOCAL_GOV_CODE_TOKYOTO_SHINJUKUKU.getValue();

    private static final JapaneseLocalGovernmentCode JP_LOCAL_GOV_CODE_NOT_EXIST = CityTestUtil.JP_LOCAL_GOV_CODE_NOT_EXIST;
    private static final String JP_LOCAL_GOV_CODE_NOT_EXIST_STR
        = JP_LOCAL_GOV_CODE_NOT_EXIST.getValue();
    // @formatter:on

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityGetUseCase cityGetUseCase;

    @Autowired
    private CityDtoMapper cityDtoMapper;

    @Test
    void 地方自治体コード13104を指定した場合は東京都新宿区が返ってくる() throws Exception {

        City tokyotoShinjukuku = CityTestUtil.tokyotoShinjukuku();
        CityDto tokyotoShinjukukuDto = cityDtoMapper.fromDomainObjectToDto(tokyotoShinjukuku);

        ObjectMapper objectMapper = new ObjectMapper();
        final String expectedString = objectMapper.writeValueAsString(tokyotoShinjukukuDto);

        when(cityGetUseCase.get(JP_LOCAL_GOV_CODE_TOKYOTO_SHINJUKUKU)).thenReturn(tokyotoShinjukuku);

        // @formatter:off
        mockMvc.perform(get(EndpointUrls.CITIES_GET_LIST + "/" + JP_LOCAL_GOV_CODE_TOKYOTO_SHINJUKUKU_STR))
            .andExpect(status().isOk())
            .andExpect(content().string(expectedString));
        // @formatter:on
    }

    @Test
    void 存在しない地方自治体コード99999を指定した場合は例外が発生する() throws Exception {

        // @formatter:off
        when(cityGetUseCase.get(JP_LOCAL_GOV_CODE_NOT_EXIST))
            .thenThrow(new CityNotFoundException(String.format("地方自治体コード: %s に対応する情報はありません。", JP_LOCAL_GOV_CODE_NOT_EXIST_STR)));

        mockMvc.perform(get(EndpointUrls.CITIES_GET_LIST + "/" + JP_LOCAL_GOV_CODE_NOT_EXIST_STR))
            .andExpect(status().isNotFound())
            .andDo(print());
        // @formatter:on
    }

}

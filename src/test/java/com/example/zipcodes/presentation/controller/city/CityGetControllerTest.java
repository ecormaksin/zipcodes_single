package com.example.zipcodes.presentation.controller.city;

import static com.example.zipcodes.presentation.controller.EndpointUrls.CITIES;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.example.zipcodes.domain.model.city.CityTestUtil;
import com.example.zipcodes.domain.model.city.JapaneseLocalGovernmentCode;
import com.example.zipcodes.presentation.controller.ControllerUtil;
import com.example.zipcodes.presentation.controller.city.CityGetController;
import com.example.zipcodes.ui.presentation.city.CityDtoMapperImpl;
import com.example.zipcodes.usecase.city.CityGetUseCase;

@WebMvcTest(CityGetController.class)
@Import(value = { CityDtoMapperImpl.class, ControllerUtil.class })
class CityGetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityGetUseCase cityGetUseCase;

    @Test
    void 地方自治体コード13104を指定した場合は東京都新宿区が返ってくる() throws Exception {

        // 便宜的に都庁所在地1件をサンプルとして返す
        // @formatter:off
        final String expectedString = "{"
                + "\"japaneseLocalGovernmentCode\":\"13104\"" 
                + ",\"kanjiName\":\"新宿区\""
                + ",\"hiraganaName\":\"しんじゅくく\""
                + ",\"katakanaFullwidthName\":\"シンジュクク\""
                + ",\"katakanaHalfwidthName\":\"ｼﾝｼﾞｭｸｸ\""
                + "}";
        // @formatter:on

        final JapaneseLocalGovernmentCode JP_LOCAL_GOV_CODE_SHINJUKUKU = CityTestUtil.JP_LOCAL_GOV_CODE_SHINJUKUKU;
        when(cityGetUseCase.get(JP_LOCAL_GOV_CODE_SHINJUKUKU)).thenReturn(Optional.of(CityTestUtil.shinjukuku()));

        // @formatter:off
        mockMvc.perform(get(CITIES + "/" + JP_LOCAL_GOV_CODE_SHINJUKUKU.getValue()))
            .andExpect(status().isOk())
            .andExpect(content().string(expectedString))
            .andDo(print());
        // @formatter:on
    }

    @Test
    void 存在しない地方自治体コード99999を指定した場合は404エラーが返ってくる() throws Exception {

        // @formatter:off
        final String expectedString = "{"
                + "\"errorMessage\":\"地方自治体コード: 99999 に該当する市区町村はありません。\"" 
                + "}";
        // @formatter:on

        final JapaneseLocalGovernmentCode JP_LOCAL_GOV_CODE_NOT_EXIST = CityTestUtil.JP_LOCAL_GOV_CODE_NOT_EXIST;
        when(cityGetUseCase.get(JP_LOCAL_GOV_CODE_NOT_EXIST)).thenReturn(Optional.empty());

        // @formatter:off
        mockMvc.perform(get(CITIES + "/" + JP_LOCAL_GOV_CODE_NOT_EXIST.getValue()))
            .andExpect(status().isNotFound())
            .andExpect(content().string(expectedString))
            .andDo(print());
        // @formatter:on
    }

}

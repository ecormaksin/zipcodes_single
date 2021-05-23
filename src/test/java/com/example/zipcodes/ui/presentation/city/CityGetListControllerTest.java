package com.example.zipcodes.ui.presentation.city;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
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
import com.example.zipcodes.usecase.city.CityGetListUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CityGetListController.class)
@Import(value = { CityDtoMapperImpl.class, ControllerUtil.class })
class CityGetListControllerTest {

    private static final PrefectureCode PREFECTURE_CODE_TOKYO = PrefectureTestUtil.PREFECTURE_CODE_TOKYO;
    private static final PrefectureCode PREFECTURE_CODE_NOT_EXIST = PrefectureTestUtil.PREFECTURE_CODE_NOT_EXIST;

    // 便宜的に東京都の地方自治体コード昇順上位4件をサンプルとして返す
    // @formatter:off
    private static final List<City> resCities = Arrays.asList(
            CityTestUtil.chiyodaku()
            , CityTestUtil.chuoku()
            , CityTestUtil.minatoku()
            , CityTestUtil.shinjukuku()
            );
    // @formatter:on

    // 便宜的に東京都新宿1件をサンプルとして返す
    // @formatter:off
    private static final List<City> resCity = Arrays.asList(
            CityTestUtil.shinjukuku()
            );
    // @formatter:on

    // @formatter:off
    private static final String resNotFound = "{"
            + "\"errorMessage\":\"該当する市区町村はありません。\"" 
            + "}";
    // @formatter:on

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityGetListUseCase cityGetListUseCase;

    @Autowired
    private CityDtoMapper cityDtoMapper;

    @Test
    @DisplayName("都道府県コード指定あり、かつ、検索キーワード指定なしの時は都道府県内のすべての市区町村が返ってくる")
    void findByPrefectureCode() throws Exception {

        when(cityGetListUseCase.findByPrefectureCode(PREFECTURE_CODE_TOKYO)).thenReturn(resCities);

        ObjectMapper objectMapper = new ObjectMapper();
        List<CityDto> dtos = cityDtoMapper.fromDomainObjectsToDtos(resCities);

        // @formatter:off
        mockMvc.perform(get("/prefectures/13/cities"))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(dtos)));
        // @formatter:on
    }

    @Test
    @DisplayName("存在しない都道府県コードを指定した時は404エラーが返ってくる")
    void findByPrefectureCodeNotFound() throws Exception {

        when(cityGetListUseCase.findByPrefectureCode(PREFECTURE_CODE_NOT_EXIST)).thenReturn(Collections.emptyList());

        // @formatter:off
        mockMvc.perform(get("/prefectures/99/cities"))
            .andExpect(status().isNotFound())
            .andExpect(content().string(resNotFound))
            .andDo(print());
        // @formatter:on
    }

    @Test
    @DisplayName("都道府県コード指定あり、かつ、検索キーワード指定ありの時はキーワードに該当する市区町村が返ってくる")
    void findByPrefectureCodeKeywords() throws Exception {

        final String keywords = "新 宿";
        when(cityGetListUseCase.findByPrefectureCodeKeywords(PREFECTURE_CODE_TOKYO, keywords)).thenReturn(resCity);

        ObjectMapper objectMapper = new ObjectMapper();
        List<CityDto> dtos = cityDtoMapper.fromDomainObjectsToDtos(resCity);

        // @formatter:off
        mockMvc.perform(get("/prefectures/13/cities?keywords=" + keywords))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(dtos)));
        // @formatter:on
    }

    @Test
    @DisplayName("都道府県コード指定あり、かつ、検索キーワードに該当する市区町村が存在しない時は404エラーが返ってくる")
    void findByPrefectureCodeKeywordsNotFound() throws Exception {

        final String keywords = "あいうえお";
        when(cityGetListUseCase.findByPrefectureCodeKeywords(PREFECTURE_CODE_TOKYO, keywords))
                .thenReturn(Collections.emptyList());

        // @formatter:off
        mockMvc.perform(get("/prefectures/33/cities?keywords=" + keywords))
            .andExpect(status().isNotFound())
            .andExpect(content().string(resNotFound))
            .andDo(print());
        // @formatter:on
    }

    @Test
    @DisplayName("都道府県コード指定なし、かつ、検索キーワード指定ありの時はキーワードに該当する市区町村が返ってくる")
    void findByKeywords() throws Exception {

        final String keywords = "新 宿";
        when(cityGetListUseCase.findByKeywords(keywords)).thenReturn(resCity);

        ObjectMapper objectMapper = new ObjectMapper();
        List<CityDto> dtos = cityDtoMapper.fromDomainObjectsToDtos(resCity);

        // @formatter:off
        mockMvc.perform(get("/cities?keywords=" + keywords))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(dtos)));
        // @formatter:on
    }

    @Test
    @DisplayName("都道府県コード指定なし、かつ、検索キーワードに該当する市区町村が存在しない時は404エラーが返ってくる")
    void findByKeywordsNotFound() throws Exception {

        final String keywords = "あいうえお";
        when(cityGetListUseCase.findByKeywords(keywords)).thenReturn(Collections.emptyList());

        // @formatter:off
        mockMvc.perform(get("/cities?keywords=" + keywords))
            .andExpect(status().isNotFound())
            .andExpect(content().string(resNotFound))
            .andDo(print());
        // @formatter:on
    }

    @Test
    @DisplayName("都道府県コード指定なし、かつ、検索キーワード指定なしの時は400エラーが返ってくる")
    void findByNoKeywords() throws Exception {

        // @formatter:off
        final String expectedString = "{"
                + "\"errorMessage\":\"リクエスト パラメーター 'keywords' を指定してください。\"" 
                + "}";
        // @formatter:on

        // @formatter:off
        mockMvc.perform(get("/cities"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(expectedString))
            .andDo(print());
        // @formatter:on
    }

}

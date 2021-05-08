package com.example.zipcodes.ui.presentation.prefecture;

import static com.example.zipcodes.ui.presentation.EndpointUrls.PREFECTURES_GET_LIST;
import static com.example.zipcodes.ui.presentation.Names.KEYWORDS;
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

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureTestUtil;
import com.example.zipcodes.ui.presentation.ControllerUtil;
import com.example.zipcodes.usecase.prefecture.PrefectureGetListUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PrefectureGetListController.class)
@Import(value = { PrefectureDtoMapperImpl.class, ControllerUtil.class })
class PrefectureGetListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrefectureGetListUseCase prefectureGetListUseCase;

    @Autowired
    private PrefectureDtoMapper prefectureDtoMapper;

    @Test
    void 都道府県コード指定なしの時はリストが返ってくる() throws Exception {

        // 便宜的に東西の都を2件サンプルとして返す
        // @formatter:off
        List<Prefecture> prefectures = Arrays.asList(
                PrefectureTestUtil.tokyoto()
                , PrefectureTestUtil.kyotofu()
                );
        // @formatter:on

        List<PrefectureDto> dtos = prefectureDtoMapper.fromDomainObjectListToDtoList(prefectures);

        when(prefectureGetListUseCase.findAll()).thenReturn(prefectures);

        ObjectMapper objectMapper = new ObjectMapper();
        // @formatter:off
        mockMvc.perform(get(PREFECTURES_GET_LIST))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(dtos)));
        // @formatter:on
    }

    @Test
    @DisplayName("検索キーワード指定ありの時はキーワードに該当する都道府県が返ってくる")
    void findByKeywords() throws Exception {

        // 便宜的に東京都を1件サンプルとして返す
        // @formatter:off
        List<Prefecture> prefectures = Arrays.asList(
                PrefectureTestUtil.tokyoto()
                );
        // @formatter:on

        List<PrefectureDto> dtos = prefectureDtoMapper.fromDomainObjectListToDtoList(prefectures);

        final String keywords = "東 京";
        when(prefectureGetListUseCase.findByKeywords(keywords)).thenReturn(prefectures);

        ObjectMapper objectMapper = new ObjectMapper();
        // @formatter:off
        mockMvc.perform(get(PREFECTURES_GET_LIST + String.format("?%s=%s", KEYWORDS, keywords)))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(dtos)));
        // @formatter:on
    }

    @Test
    @DisplayName("検索キーワードに該当する都道府県が存在しない時は404エラーが返ってくる")
    void findByKeywordsNotFound() throws Exception {

        // @formatter:off
        final String expectedString = "{"
                + "\"errorMessage\":\"該当する都道府県はありません。\"" 
                + "}";
        // @formatter:on

        final String keywords = "あいうえお";
        when(prefectureGetListUseCase.findByKeywords(keywords)).thenReturn(Collections.emptyList());

        // @formatter:off
        mockMvc.perform(get(PREFECTURES_GET_LIST + String.format("?%s=%s", KEYWORDS, keywords)))
            .andExpect(status().isNotFound())
            .andExpect(content().string(expectedString))
            .andDo(print());
        // @formatter:on
    }
}

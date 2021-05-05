package com.example.zipcodes.ui.presentation.prefecture;

import static com.example.zipcodes.ui.presentation.EndpointUrls.PREFECTURES_GET_LIST;
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

import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureTestUtil;
import com.example.zipcodes.ui.presentation.ControllerUtil;
import com.example.zipcodes.usecase.prefecture.PrefectureGetUseCase;

@WebMvcTest(PrefectureGetController.class)
@Import(value = { PrefectureDtoMapperImpl.class, ControllerUtil.class })
class PrefectureGetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrefectureGetUseCase prefectureGetUseCase;

    @Test
    void 都道府県コード13を指定した場合は東京都1件が返ってくる() throws Exception {

        // 便宜的に首都1件をサンプルとして返す
        // @formatter:off
        final String expectedString = "{"
                + "\"prefectureCode\":\"13\"" 
                + ",\"kanjiName\":\"東京都\""
                + ",\"hiraganaName\":\"とうきょうと\""
                + ",\"katakanaFullwidthName\":\"トウキョウト\""
                + ",\"katakanaHalfwidthName\":\"ﾄｳｷｮｳﾄ\""
                + "}";
        // @formatter:on

        final PrefectureCode PREFECTURE_CODE_TOKYO = PrefectureTestUtil.PREFECTURE_CODE_TOKYO;
        when(prefectureGetUseCase.get(PREFECTURE_CODE_TOKYO)).thenReturn(Optional.of(PrefectureTestUtil.tokyoto()));

        // @formatter:off
		mockMvc.perform(get(PREFECTURES_GET_LIST + "/" + PREFECTURE_CODE_TOKYO.getValue()))
			.andExpect(status().isOk())
			.andExpect(content().string(expectedString))
			.andDo(print());
		// @formatter:on
    }

    @Test
    void 存在しない都道府県コードを指定した場合は404エラーが返ってくる() throws Exception {

        // @formatter:off
        final String expectedString = "{"
                + "\"errorMessage\":\"都道府県コード: 99 に対応する情報はありません。\"" 
                + "}";
        // @formatter:on

        final PrefectureCode PREFECTURE_CODE_NOT_EXIST = PrefectureTestUtil.PREFECTURE_CODE_NOT_EXIST;
        when(prefectureGetUseCase.get(PREFECTURE_CODE_NOT_EXIST)).thenReturn(Optional.empty());

        // @formatter:off
        mockMvc.perform(get(PREFECTURES_GET_LIST + "/" + PREFECTURE_CODE_NOT_EXIST.getValue()))
            .andExpect(status().isNotFound())
            .andExpect(content().string(expectedString))
            .andDo(print());
        // @formatter:on
    }
}

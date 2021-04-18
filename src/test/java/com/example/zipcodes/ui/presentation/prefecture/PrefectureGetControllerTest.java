package com.example.zipcodes.ui.presentation.prefecture;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureNotFoundException;
import com.example.zipcodes.domain.model.prefecture.TestUtilPrefecture;
import com.example.zipcodes.domain.usecase.prefecture.PrefectureGetUseCase;
import com.example.zipcodes.ui.presentation.EndpointUrls;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PrefectureGetController.class)
@Import(PrefectureDtoMapperImpl.class)
class PrefectureGetControllerTest {

    private static final PrefectureCode PREFECTURE_CODE_TOKYO = TestUtilPrefecture.PREFECTURE_CODE_TOKYO;
    private static final String PREFECTURE_CODE_TOKYO_STR = PREFECTURE_CODE_TOKYO.getValue();

    private static final PrefectureCode PREFECTURE_CODE_UNKNOWN = TestUtilPrefecture.PREFECTURE_CODE_NOT_EXIST;
    private static final String PREFECTURE_CODE_UNKNOWN_STR = PREFECTURE_CODE_UNKNOWN.getValue();
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrefectureGetUseCase prefectureGetUseCase;

    @Autowired
    private PrefectureDtoMapper prefectureMapper;

    private DmEtPrefecture tokyoto;
    private PrefectureDto tokyotoDto;

    @BeforeEach
    void beforeEach() {

        tokyoto = TestUtilPrefecture.domainEntityTokyo();
		tokyotoDto = prefectureMapper.fromDomainObjectToDto(tokyoto);
    }

    @Test
	void 都道府県コード13を指定した場合は東京都1件が返ってくる() throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		final String expectedString = objectMapper.writeValueAsString(tokyotoDto);
		
        when(prefectureGetUseCase.get(PREFECTURE_CODE_TOKYO)).thenReturn(tokyoto);
		
		// @formatter:off
		mockMvc.perform(get(EndpointUrls.PREFECTURES_GET_LIST + "/" + PREFECTURE_CODE_TOKYO_STR))
			.andExpect(status().isOk())
			.andExpect(content().string(expectedString));
		// @formatter:on
    }

    @Test
    void 存在しない都道府県コードを指定した場合は例外が発生する() throws Exception {

        when(prefectureGetUseCase.get(PREFECTURE_CODE_UNKNOWN))
                .thenThrow(
                        new PrefectureNotFoundException(
                                String.format("都道府県コード: %s に対応する情報はありません。", PREFECTURE_CODE_UNKNOWN_STR)));
        
        // @formatter:off
        mockMvc.perform(get(EndpointUrls.PREFECTURES_GET_LIST + "/" + PREFECTURE_CODE_UNKNOWN_STR))
            .andExpect(status().isNotFound())
            .andDo(print());
        // @formatter:on
    }
}

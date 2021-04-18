package com.example.zipcodes.ui.presentation.prefecture;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.example.zipcodes.domain.model.prefecture.TestUtilPrefecture;
import com.example.zipcodes.domain.usecase.prefecture.PrefectureGetUseCase;
import com.example.zipcodes.ui.presentation.RequestPaths;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PrefectureGetController.class)
@Import(PrefectureDtoMapperImpl.class)
class PrefectureGetControllerTest {

    private static final String PREFECTURE_CODE_TOKYO = TestUtilPrefecture.PREFECTURE_CODE_TOKYO;
    
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
		mockMvc.perform(get(RequestPaths.PREFECTURES_GET_LIST + "/" + PREFECTURE_CODE_TOKYO))
			.andExpect(status().isOk())
			.andExpect(content().string(expectedString));
		// @formatter:on
    }
}

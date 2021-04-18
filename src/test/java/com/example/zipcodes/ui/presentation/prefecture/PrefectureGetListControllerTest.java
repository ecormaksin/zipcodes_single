package com.example.zipcodes.ui.presentation.prefecture;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;
import com.example.zipcodes.domain.model.prefecture.TestUtilPrefecture;
import com.example.zipcodes.domain.usecase.prefecture.PrefectureGetListUseCase;
import com.example.zipcodes.ui.presentation.EndpointUrls;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PrefectureGetListController.class)
@Import(PrefectureDtoMapperImpl.class)
class PrefectureGetListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrefectureGetListUseCase prefectureGetListUseCase;

    @Autowired
    private PrefectureDtoMapper prefectureMapper;

    private DmEtPrefecture tokyoto;
    private PrefectureDto tokyotoDto;

    private DmEtPrefecture kyotofu;
    private PrefectureDto kyotofuDto;

    @BeforeEach
    void beforeEach() {

		tokyoto = TestUtilPrefecture.domainEntityTokyo();
		tokyotoDto = prefectureMapper.fromDomainObjectToDto(tokyoto);

		kyotofu = TestUtilPrefecture.domainEntityKyoto();
		kyotofuDto = prefectureMapper.fromDomainObjectToDto(kyotofu);
    }

    @Test
    void 都道府県コード指定なしの時はリストが返ってくる() throws Exception {

        List<DmEtPrefecture> dmEtList = new ArrayList<>();
        dmEtList.add(tokyoto);
        dmEtList.add(kyotofu);

        List<PrefectureDto> expected = new ArrayList<>();
        expected.add(tokyotoDto);
        expected.add(kyotofuDto);

        ObjectMapper objectMapper = new ObjectMapper();
        final String expectedString = objectMapper.writeValueAsString(expected);

        when(prefectureGetListUseCase.findAll()).thenReturn(dmEtList);

        // @formatter:off
        mockMvc.perform(get(EndpointUrls.PREFECTURES_GET_LIST))
            .andExpect(status().isOk())
            .andExpect(content().string(expectedString));
        // @formatter:on
    }
}

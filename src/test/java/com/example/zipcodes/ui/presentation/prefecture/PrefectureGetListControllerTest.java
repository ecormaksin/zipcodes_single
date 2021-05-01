package com.example.zipcodes.ui.presentation.prefecture;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureTestUtil;
import com.example.zipcodes.ui.presentation.EndpointUrls;
import com.example.zipcodes.usecase.prefecture.PrefectureGetListUseCase;
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

    @Test
    void 都道府県コード指定なしの時はリストが返ってくる() throws Exception {

        Prefecture tokyoto = PrefectureTestUtil.tokyoto();
        Prefecture kyotofu = PrefectureTestUtil.kyotofu();
        // @formatter:off
        List<Prefecture> prefectures = Arrays.asList(
                tokyoto
                , kyotofu
                );
        // @formatter:on

        PrefectureDto tokyotoDto = prefectureMapper.fromDomainObjectToDto(tokyoto);
        PrefectureDto kyotofuDto = prefectureMapper.fromDomainObjectToDto(kyotofu);
        // @formatter:off
        List<PrefectureDto> dtos = Arrays.asList(
                tokyotoDto
                , kyotofuDto
            );
        // @formatter:on

        ObjectMapper objectMapper = new ObjectMapper();
        final String expectedString = objectMapper.writeValueAsString(dtos);

        when(prefectureGetListUseCase.findAll()).thenReturn(prefectures);

        // @formatter:off
        mockMvc.perform(get(EndpointUrls.PREFECTURES_GET_LIST))
            .andExpect(status().isOk())
            .andExpect(content().string(expectedString));
        // @formatter:on
    }
}

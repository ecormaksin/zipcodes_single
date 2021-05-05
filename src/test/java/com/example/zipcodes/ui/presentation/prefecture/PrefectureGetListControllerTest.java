package com.example.zipcodes.ui.presentation.prefecture;

import static com.example.zipcodes.ui.presentation.EndpointUrls.PREFECTURES_GET_LIST;
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

        List<PrefectureDto> dtos = new ArrayList<>();
        // @formatter:off
        prefectures.stream()
            .forEach(prefecture -> {
                dtos.add(prefectureDtoMapper.fromDomainObjectToDto(prefecture));
            });
        // @formatter:on

        when(prefectureGetListUseCase.findAll()).thenReturn(prefectures);

        ObjectMapper objectMapper = new ObjectMapper();
        // @formatter:off
        mockMvc.perform(get(PREFECTURES_GET_LIST))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(dtos)));
        // @formatter:on
    }
}

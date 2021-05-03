package com.example.zipcodes.ui.presentation.prefecture;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.ui.presentation.ControllerUtil;
import com.example.zipcodes.ui.presentation.EndpointUrls;
import com.example.zipcodes.usecase.prefecture.PrefectureGetListUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PrefectureGetListController {

    private final PrefectureGetListUseCase prefectureGetListUseCase;
    private final PrefectureDtoMapper prefectureDtoMapper;
    private final ControllerUtil controllerUtil;

    @SuppressWarnings("unchecked")
    @GetMapping(EndpointUrls.PREFECTURES_GET_LIST)
    public ResponseEntity<List<PrefectureDto>> getList() {

        List<Prefecture> entities = prefectureGetListUseCase.findAll();

        List<PrefectureDto> dtos = prefectureDtoMapper.fromDomainObjectListToDtoList(entities);

        return (ResponseEntity<List<PrefectureDto>>) controllerUtil.ok(dtos);
    }
}

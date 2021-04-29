package com.example.zipcodes.ui.presentation.prefecture;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.ui.presentation.EndpointUrls;
import com.example.zipcodes.usecase.prefecture.PrefectureGetListUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PrefectureGetListController {

    private final PrefectureGetListUseCase prefectureGetListUseCase;
    private final PrefectureDtoMapper prefectureMapper;

    @GetMapping(EndpointUrls.PREFECTURES_GET_LIST)
    public ResponseEntity<List<PrefectureDto>> getList() {

        List<Prefecture> domainEntities = prefectureGetListUseCase.findAll();

        List<PrefectureDto> list = prefectureMapper.fromDomainObjectListToDtoList(domainEntities);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));

        return new ResponseEntity<>(list, httpHeaders, HttpStatus.OK);
    }
}

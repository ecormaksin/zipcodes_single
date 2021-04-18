package com.example.zipcodes.ui.presentation.prefecture;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;
import com.example.zipcodes.domain.usecase.prefecture.PrefectureGetUseCase;
import com.example.zipcodes.ui.presentation.RequestPaths;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PrefectureGetController {

    private final PrefectureGetUseCase prefectureGetUseCase;
    private final PrefectureDtoMapper prefectureMapper;

    @GetMapping(RequestPaths.PREFECTURE_GET)
    public ResponseEntity<PrefectureDto> get(@PathVariable String prefectureCode) {

        DmEtPrefecture domainEntity = prefectureGetUseCase.get(prefectureCode);

        PrefectureDto dto = prefectureMapper.fromDomainObjectToDto(domainEntity);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));

        return new ResponseEntity<>(dto, httpHeaders, HttpStatus.OK);
    }
}

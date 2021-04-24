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
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureNotFoundException;
import com.example.zipcodes.ui.presentation.EndpointUrls;
import com.example.zipcodes.usecase.prefecture.PrefectureGetUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PrefectureGetController {

    private final PrefectureGetUseCase prefectureGetUseCase;
    private final PrefectureDtoMapper prefectureMapper;

    @GetMapping(EndpointUrls.PREFECTURE_GET)
    public ResponseEntity<?> get(@PathVariable String prefectureCode) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));

        try {
            DmEtPrefecture domainEntity = prefectureGetUseCase.get(new PrefectureCode(prefectureCode));

            PrefectureDto dto = prefectureMapper.fromDomainObjectToDto(domainEntity);

            return new ResponseEntity<>(dto, httpHeaders, HttpStatus.OK);

        } catch (PrefectureNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), httpHeaders, HttpStatus.NOT_FOUND);
        }
    }
}

package com.example.zipcodes.ui.presentation.city;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.ui.presentation.EndpointUrls;
import com.example.zipcodes.usecase.city.CityGetListUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CityGetListController {

    private final CityGetListUseCase cityGetListUseCase;
    private final CityDtoMapper cityDtoMapper;

    @GetMapping(EndpointUrls.PREFECTURE_GET + EndpointUrls.CITIES_GET_LIST)
    public ResponseEntity<List<CityDto>> getList(
            @PathVariable(name = "prefectureCode") final String prefectureCodeStr) {

        List<City> entities = cityGetListUseCase.findByPrefectureCode(new PrefectureCode(prefectureCodeStr));

        List<CityDto> dtos = cityDtoMapper.fromDomainObjectListToDtoList(entities);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));

        return new ResponseEntity<>(dtos, httpHeaders, HttpStatus.OK);
    }
}

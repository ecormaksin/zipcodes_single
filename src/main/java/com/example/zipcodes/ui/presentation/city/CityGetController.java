package com.example.zipcodes.ui.presentation.city;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.city.CityNotFoundException;
import com.example.zipcodes.domain.model.city.JapaneseLocalGovernmentCode;
import com.example.zipcodes.ui.presentation.EndpointUrls;
import com.example.zipcodes.usecase.city.CityGetUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CityGetController {

    private final CityGetUseCase cityGetUseCase;
    private final CityDtoMapper cityDtoMapper;

    @GetMapping(EndpointUrls.CITY_GET)
    public ResponseEntity<?> get(@PathVariable String japaneseLocalGovernmentCode) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));

        try {
            City city = cityGetUseCase.get(new JapaneseLocalGovernmentCode(japaneseLocalGovernmentCode));

            CityDto cityDto = cityDtoMapper.fromDomainObjectToDto(city);

            return new ResponseEntity<>(cityDto, httpHeaders, HttpStatus.OK);
        } catch (CityNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), httpHeaders, HttpStatus.NOT_FOUND);
        }
    }
}

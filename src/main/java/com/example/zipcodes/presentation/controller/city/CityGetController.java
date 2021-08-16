package com.example.zipcodes.presentation.controller.city;

import static com.example.zipcodes.presentation.controller.EndpointUrls.CITY;
import static com.example.zipcodes.presentation.controller.KeyNames.JAPANESE_LOCAL_GOVERNMENT_CODE;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.city.JapaneseLocalGovernmentCode;
import com.example.zipcodes.presentation.controller.ControllerUtil;
import com.example.zipcodes.usecase.city.CityGetUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CityGetController {

    private final CityGetUseCase cityGetUseCase;
    private final CityDtoMapper cityDtoMapper;
    private final ControllerUtil controllerUtil;

    @GetMapping(CITY)
    public ResponseEntity<?> get(@PathVariable(name = JAPANESE_LOCAL_GOVERNMENT_CODE) final String jpLocalGovCodeStr) {

        Optional<City> optionalCity = cityGetUseCase.get(new JapaneseLocalGovernmentCode(jpLocalGovCodeStr));

        // @formatter:off
        return optionalCity.isPresent() ? 
                controllerUtil.ok(cityDtoMapper.fromDomainObjectToDto(optionalCity.get()))
                : controllerUtil.notFound("message.city.not.found", new Object[] { jpLocalGovCodeStr });
        // @formatter:on
    }
}

package com.example.zipcodes.ui.presentation.city;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.city.JapaneseLocalGovernmentCode;
import com.example.zipcodes.ui.presentation.ControllerUtil;
import com.example.zipcodes.ui.presentation.EndpointUrls;
import com.example.zipcodes.usecase.city.CityGetUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CityGetController {

    private final CityGetUseCase cityGetUseCase;
    private final CityDtoMapper cityDtoMapper;
    private final ControllerUtil controllerUtil;

    @GetMapping(EndpointUrls.CITY_GET)
    public ResponseEntity<?> get(@PathVariable String japaneseLocalGovernmentCode) {

        Optional<City> optionalCity = cityGetUseCase.get(new JapaneseLocalGovernmentCode(japaneseLocalGovernmentCode));

        if (optionalCity.isPresent()) {
            return controllerUtil.ok(cityDtoMapper.fromDomainObjectToDto(optionalCity.get()));
        }

        return controllerUtil.notFoundErrorResponse("message.city.not.found",
                new Object[] { japaneseLocalGovernmentCode });
    }
}

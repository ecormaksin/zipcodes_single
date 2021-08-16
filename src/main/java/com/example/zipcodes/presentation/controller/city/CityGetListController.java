package com.example.zipcodes.presentation.controller.city;

import static com.example.zipcodes.presentation.controller.EndpointUrls.CITIES;
import static com.example.zipcodes.presentation.controller.EndpointUrls.CITIES_WITH_PREFECTURES;
import static com.example.zipcodes.presentation.controller.KeyNames.KEYWORDS;
import static com.example.zipcodes.presentation.controller.KeyNames.PREFECTURE_CODE;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.presentation.controller.ControllerUtil;
import com.example.zipcodes.usecase.city.CityGetListUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CityGetListController {

    private final CityGetListUseCase cityGetListUseCase;
    private final CityDtoMapper cityDtoMapper;
    private final ControllerUtil controllerUtil;

    @GetMapping(CITIES_WITH_PREFECTURES)
    public ResponseEntity<?> findByPrefectureCode(@PathVariable(name = PREFECTURE_CODE) final String prefectureCodeStr,
            @RequestParam(name = KEYWORDS) final Optional<String> optKeywords) {

        final PrefectureCode prefectureCode = new PrefectureCode(prefectureCodeStr);

        // @formatter:off
        List<City> entities = optKeywords.isPresent() ?
                cityGetListUseCase.findByPrefectureCodeKeywords(prefectureCode, optKeywords.get())
                : cityGetListUseCase.findByPrefectureCode(prefectureCode);
        // @formatter:on

        return responseEntity(entities);
    }

    @GetMapping(CITIES)
    public ResponseEntity<?> findByKeywords(@RequestParam(name = KEYWORDS) final String keywords) {

        List<City> entities = cityGetListUseCase.findByKeywords(keywords);

        return responseEntity(entities);
    }

    private ResponseEntity<?> responseEntity(List<City> entities) {

        List<CityDto> dtos = cityDtoMapper.fromDomainObjectsToDtos(entities);

        // @formatter:off
        return dtos.isEmpty() ? 
                controllerUtil.notFound("message.cities.not.found")
                : controllerUtil.ok(dtos);
        // @formatter:on
    }
}

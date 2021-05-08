package com.example.zipcodes.ui.presentation.city;

import static com.example.zipcodes.ui.presentation.EndpointUrls.CITIES_GET_LIST;
import static com.example.zipcodes.ui.presentation.EndpointUrls.PREFECTURE_GET;
import static com.example.zipcodes.ui.presentation.Names.KEYWORDS;
import static com.example.zipcodes.ui.presentation.Names.PREFECTURE_CODE;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.ui.presentation.ControllerUtil;
import com.example.zipcodes.usecase.city.CityGetListUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CityGetListController {

    private final CityGetListUseCase cityGetListUseCase;
    private final CityDtoMapper cityDtoMapper;
    private final ControllerUtil controllerUtil;

    @SuppressWarnings("unchecked")
    @GetMapping(PREFECTURE_GET + CITIES_GET_LIST)
    public ResponseEntity<List<CityDto>> findByPrefectureCode(
            @PathVariable(name = PREFECTURE_CODE) final String prefectureCodeStr) {

        List<City> entities = cityGetListUseCase.findByPrefectureCode(new PrefectureCode(prefectureCodeStr));

        List<CityDto> dtos = cityDtoMapper.fromDomainObjectListToDtoList(entities);

        return (ResponseEntity<List<CityDto>>) controllerUtil.ok(dtos);
    }

    @GetMapping(CITIES_GET_LIST)
    public ResponseEntity<?> findByKeywords(@RequestParam(name = KEYWORDS) final String keywords) {

        List<City> entities = cityGetListUseCase.findByKeywords(keywords);

        List<CityDto> dtos = cityDtoMapper.fromDomainObjectListToDtoList(entities);

        // @formatter:off
        return dtos.isEmpty() ? 
                controllerUtil.notFound("message.cities.not.found")
                : controllerUtil.ok(dtos);
        // @formatter:on
    }
}

package com.example.zipcodes.ui.presentation.city;

import static com.example.zipcodes.ui.presentation.Names.PREFECTURE_CODE;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.ui.presentation.ControllerUtil;
import com.example.zipcodes.ui.presentation.EndpointUrls;
import com.example.zipcodes.usecase.city.CityGetListUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CityGetListController {

    private final CityGetListUseCase cityGetListUseCase;
    private final CityDtoMapper cityDtoMapper;
    private final ControllerUtil controllerUtil;

    @SuppressWarnings("unchecked")
    @GetMapping(EndpointUrls.PREFECTURE_GET + EndpointUrls.CITIES_GET_LIST)
    public ResponseEntity<List<CityDto>> getList(@PathVariable(name = PREFECTURE_CODE) final String prefectureCodeStr) {

        List<City> entities = cityGetListUseCase.findByPrefectureCode(new PrefectureCode(prefectureCodeStr));

        List<CityDto> dtos = cityDtoMapper.fromDomainObjectListToDtoList(entities);

        return (ResponseEntity<List<CityDto>>) controllerUtil.ok(dtos);
    }
}

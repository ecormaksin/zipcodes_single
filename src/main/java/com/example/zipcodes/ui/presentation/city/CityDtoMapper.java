package com.example.zipcodes.ui.presentation.city;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.city.City;

@Component
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CityDtoMapper {

    default CityDto fromDomainObjectToDto(City entity) {

        // @formatter:off
        return CityDto.builder()
                .japaneseLocalGovernmentCode( entity.getJapaneseLocalGovernmentCode().getValue() )
                .cityKanjiName( entity.getCityKanjiName().getValue() )
                .cityHiraganaName( entity.getCityHiraganaName().getValue() )
                .cityKatakanaFullwidthName( entity.getCityKatakanaFullwidthName().getValue() )
                .cityKatakanaHalfwidthName( entity.getCityKatakanaHalfwidthName().getValue() )
                .build();
        // @formatter:on
    }

    List<CityDto> fromDomainObjectListToDtoList(List<City> entityList);
}

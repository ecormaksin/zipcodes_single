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
                .kanjiName( entity.getKanjiName().getValue() )
                .hiraganaName( entity.getHiraganaName().getValue() )
                .katakanaFullwidthName( entity.getKatakanaFullwidthName().getValue() )
                .katakanaHalfwidthName( entity.getKatakanaHalfwidthName().getValue() )
                .build();
        // @formatter:on
    }

    List<CityDto> fromDomainObjectListToDtoList(List<City> entities);
}

package com.example.zipcodes.ui.presentation.prefecture;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;

@Component
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PrefectureDtoMapper {

    default PrefectureDto fromDomainObjectToDto(DmEtPrefecture entity) {

        // @formatter:off
		return PrefectureDto.builder()
				.code( entity.getPrefectureCode().getValue() )
				.kanjiName( entity.getPrefectureKanjiName().getValue() )
				.hiraganaName( entity.getPrefectureHiraganaName().getValue() )
				.katakanaFullwidthName( entity.getPrefectureKatakanaFullwidthName().getValue() )
				.katakanaHalfwidthName( entity.getPrefectureKatakanaHalfwidthName().getValue() )
				.build();
		// @formatter:on
    }

    List<PrefectureDto> fromDomainObjectListToDtoList(List<DmEtPrefecture> entityList);
}

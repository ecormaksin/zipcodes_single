package com.example.zipcodes.infra.db.jpa.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.city.CityHiraganaName;
import com.example.zipcodes.domain.model.city.CityKanjiName;
import com.example.zipcodes.domain.model.city.CityKatakanaFullwidthName;
import com.example.zipcodes.domain.model.city.CityKatakanaHalfwidthName;
import com.example.zipcodes.domain.model.city.JapaneseLocalGovernmentCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.infra.db.jpa.view.CityResource;
import com.ibm.icu.text.Transliterator;

@Component
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CityResourceMapper {

    default City fromEntityToDomainObject(CityResource entity) {

        final Transliterator hiraganaTransliterator = Transliterator.getInstance("Katakana-Hiragana");
        final Transliterator katakanaTransliterator = Transliterator.getInstance("Halfwidth-Fullwidth");

        final String kanaHalfwidth = entity.getCityNameKana();
        final String kanaFullwidth = katakanaTransliterator.transliterate(kanaHalfwidth);
        final String hiragana = hiraganaTransliterator.transliterate(kanaFullwidth);

        // @formatter:off
		return City.builder()
				.prefectureCode(new PrefectureCode(entity.getPrefectureCode()))
	            .japaneseLocalGovernmentCode(new JapaneseLocalGovernmentCode(entity.getJapaneseLocalGovermentCode()))
	            .kanjiName(new CityKanjiName(entity.getCityName()))
	            .hiraganaName(new CityHiraganaName(hiragana))
	            .katakanaFullwidthName(new CityKatakanaFullwidthName(kanaFullwidth))
	            .katakanaHalfwidthName(new CityKatakanaHalfwidthName(kanaHalfwidth))
				.build();
		// @formatter:on
    }

    List<City> fromEntityListToDomainObjectList(List<CityResource> entities);
}

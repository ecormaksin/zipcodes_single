package com.example.zipcodes.domain.model.city;

import java.util.List;
import java.util.Optional;

import com.example.zipcodes.domain.model.prefecture.PrefectureCode;

public interface CityRepository {

    List<City> findByPrefectureCode(PrefectureCode prefectureCode);

    Optional<City> findByJapaneseLocalGovernmentCode(JapaneseLocalGovernmentCode japaneseLocalGovernmentCode);
}

package com.example.zipcodes.domain.model.city;

import java.util.List;

import com.example.zipcodes.domain.model.prefecture.PrefectureCode;

public interface CityRepository {

    List<City> findByPrefectureCode(PrefectureCode prefectureCode);

    City findByJapaneseLocalGovernmentCode(JapaneseLocalGovernmentCode japaneseLocalGovernmentCode)
            throws CityNotFoundException;
}

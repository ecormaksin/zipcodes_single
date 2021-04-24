package com.example.zipcodes.domain.model.city;

import java.util.List;

import com.example.zipcodes.domain.model.prefecture.PrefectureCode;

public interface CityRepository {

    List<DmEtCity> findByPrefectureCode(PrefectureCode prefectureCode);

    DmEtCity findByJapaneseLocalGovernmentCode(JapaneseLocalGovernmentCode japaneseLocalGovernmentCode)
            throws CityNotFoundException;
}

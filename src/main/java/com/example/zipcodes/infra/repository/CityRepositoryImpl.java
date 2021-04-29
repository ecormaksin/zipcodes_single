package com.example.zipcodes.infra.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.city.CityNotFoundException;
import com.example.zipcodes.domain.model.city.CityRepository;
import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.city.JapaneseLocalGovernmentCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CityRepositoryImpl implements CityRepository {

    @Override
    public List<City> findByPrefectureCode(PrefectureCode prefectureCode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public City findByJapaneseLocalGovernmentCode(JapaneseLocalGovernmentCode japaneseLocalGovernmentCode)
            throws CityNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

}

package com.example.zipcodes.usecase.city;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.city.CityRepository;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityGetListUseCase {

    private final CityRepository cityRepository;

    public List<City> findByPrefectureCode(final PrefectureCode prefectureCode) {

        return cityRepository.findByPrefectureCode(prefectureCode);
    }

    public List<City> findByKeywords(final String keywords) {

        return cityRepository.findByKeywords(keywords);
    }

}

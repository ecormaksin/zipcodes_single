package com.example.zipcodes.usecase.city;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityGetListUseCase {

    public List<City> findByPrefectureCode(PrefectureCode prefectureCodeTokyo) {
        // TODO Auto-generated method stub
        return null;
    }

}

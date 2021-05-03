package com.example.zipcodes.usecase.city;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.city.CityRepository;
import com.example.zipcodes.domain.model.city.JapaneseLocalGovernmentCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityGetUseCase {

    private final CityRepository cityRepository;

    public Optional<City> get(final JapaneseLocalGovernmentCode japaneseLocalGovernmentCode) {

        return cityRepository.findByJapaneseLocalGovernmentCode(japaneseLocalGovernmentCode);
    }
}

package com.example.zipcodes.usecase.city;

import org.springframework.stereotype.Service;

import com.example.zipcodes.domain.model.city.CityNotFoundException;
import com.example.zipcodes.domain.model.city.CityRepository;
import com.example.zipcodes.domain.model.city.DmEtCity;
import com.example.zipcodes.domain.model.city.JapaneseLocalGovernmentCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityGetUseCase {

    private final CityRepository cityRepository;

    public DmEtCity get(final JapaneseLocalGovernmentCode japaneseLocalGovernmentCode)
            throws CityNotFoundException {

        return cityRepository.findByJapaneseLocalGovernmentCode(japaneseLocalGovernmentCode);
    }
}
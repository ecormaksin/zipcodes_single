package com.example.zipcodes.domain.usecase.prefecture;

import org.springframework.stereotype.Service;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureNotFoundException;
import com.example.zipcodes.domain.model.prefecture.PrefectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrefectureGetUseCase {

    private final PrefectureRepository prefectureRepository;

    public DmEtPrefecture get(final PrefectureCode prefectureCode) throws PrefectureNotFoundException {

        return prefectureRepository.findByPrefectureCode(prefectureCode);
    }
}

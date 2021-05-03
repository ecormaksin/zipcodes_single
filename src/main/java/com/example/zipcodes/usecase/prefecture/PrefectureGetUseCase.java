package com.example.zipcodes.usecase.prefecture;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrefectureGetUseCase {

    private final PrefectureRepository prefectureRepository;

    public Optional<Prefecture> get(final PrefectureCode prefectureCode) {

        return prefectureRepository.findByPrefectureCode(prefectureCode);
    }
}

package com.example.zipcodes.usecase.prefecture;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrefectureGetListUseCase {

    private final PrefectureRepository prefectureRepository;

    public List<Prefecture> findAll() {

        return prefectureRepository.findAll();
    }
}

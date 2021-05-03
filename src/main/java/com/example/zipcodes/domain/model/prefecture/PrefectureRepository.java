package com.example.zipcodes.domain.model.prefecture;

import java.util.List;
import java.util.Optional;

public interface PrefectureRepository {

    List<Prefecture> findAll();

    Optional<Prefecture> findByPrefectureCode(PrefectureCode prefectureCode);
}
package com.example.zipcodes.domain.model.prefecture;

import java.util.List;

public interface PrefectureRepository {

    List<DmEtPrefecture> findAll();

    DmEtPrefecture findByPrefectureCode(PrefectureCode prefectureCode) throws PrefectureNotFoundException;
}
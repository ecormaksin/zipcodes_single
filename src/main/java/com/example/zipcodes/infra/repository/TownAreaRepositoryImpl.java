package com.example.zipcodes.infra.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.zipcodes.infra.db.jpa.entity.TownAreaResource;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TownAreaRepositoryImpl {

    public List<TownAreaResource> findAll() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}

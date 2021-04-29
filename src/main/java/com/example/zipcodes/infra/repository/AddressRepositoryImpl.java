package com.example.zipcodes.infra.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.zipcodes.infra.db.jpa.view.AddressResource;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddressRepositoryImpl {

    public List<AddressResource> findAll() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}

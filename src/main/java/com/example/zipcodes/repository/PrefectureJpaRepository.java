package com.example.zipcodes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zipcodes.entity.Prefecture;

public interface PrefectureJpaRepository extends JpaRepository<Prefecture, String> {

}

package com.example.zipcodes.infra.db.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zipcodes.infra.db.jpa.mappedsuperclass.PrefectureResource;

public interface PrefectureJpaRepository extends JpaRepository<PrefectureResource, String> {

}

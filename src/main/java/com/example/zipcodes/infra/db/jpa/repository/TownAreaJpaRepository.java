package com.example.zipcodes.infra.db.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zipcodes.infra.db.jpa.view.TownAreaResource;

public interface TownAreaJpaRepository extends JpaRepository<TownAreaResource, Long> {

}

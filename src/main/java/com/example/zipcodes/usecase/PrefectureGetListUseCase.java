package com.example.zipcodes.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zipcodes.entity.Prefecture;
import com.example.zipcodes.repository.PrefectureJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrefectureGetListUseCase {

	private final PrefectureJpaRepository prefectureRepository;

	public List<Prefecture> findAll() {

		return prefectureRepository.findAll();
	}
}

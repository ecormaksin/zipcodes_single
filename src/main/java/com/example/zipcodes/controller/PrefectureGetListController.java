package com.example.zipcodes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.entity.Prefecture;
import com.example.zipcodes.usecase.PrefectureGetListUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PrefectureGetListController {

	private final PrefectureGetListUseCase prefectureGetListUseCase;

	@GetMapping("/prefectures")
	List<Prefecture> getList() {

		return prefectureGetListUseCase.findAll();
	}
}

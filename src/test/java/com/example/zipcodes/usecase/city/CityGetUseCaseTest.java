package com.example.zipcodes.usecase.city;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.city.CityTestUtil;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@AutoConfigureTestEntityManager
class CityGetUseCaseTest {

	@Autowired
	private CityGetUseCase cityGetUseCase;

	@Test
	void 地方自治体コード13104を指定した場合は東京都新宿区が返ってくる() throws Exception {

		Optional<City> actual = cityGetUseCase.get(CityTestUtil.JP_LOCAL_GOV_CODE_SHINJUKUKU);

		assertTrue(actual.get().equals(CityTestUtil.shinjukuku()));
	}

	@Test
	void 存在しない地方自治体コードを指定した場合は空が返ってくる() {

		Optional<City> actual = cityGetUseCase.get(CityTestUtil.JP_LOCAL_GOV_CODE_NOT_EXIST);

		assertFalse(actual.isPresent());
	}
}

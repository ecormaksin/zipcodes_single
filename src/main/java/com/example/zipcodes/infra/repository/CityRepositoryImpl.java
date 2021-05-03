package com.example.zipcodes.infra.repository;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.city.City;
import com.example.zipcodes.domain.model.city.CityRepository;
import com.example.zipcodes.domain.model.city.JapaneseLocalGovernmentCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.infra.db.jpa.mapper.CityEntityMapper;
import com.example.zipcodes.infra.db.jpa.view.CityResource;
import com.example.zipcodes.infra.db.jpa.view.QCityResource;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CityRepositoryImpl implements CityRepository {

    private final EntityManager entityManager;
    private final CityEntityMapper cityEntityMapper;

    private JPAQueryFactory queryFactory;

    private QCityResource qCity = QCityResource.cityResource;

    @PostConstruct
    void postConstruct() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<City> findByPrefectureCode(PrefectureCode prefectureCode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<City> findByJapaneseLocalGovernmentCode(JapaneseLocalGovernmentCode japaneseLocalGovernmentCode) {

        final String jpLocalGovCodeStr = japaneseLocalGovernmentCode.getValue();

        // @formatter:off
        CityResource city = queryFactory
                .selectFrom(qCity)
                .where( qCity.japaneseLocalGovermentCode.eq(jpLocalGovCodeStr) )
                .fetchOne();
        // @formatter:on

        if (null == city) {
            return Optional.empty();
        }

        return Optional.of(cityEntityMapper.fromEntityToDomainObject(city));
    }

}

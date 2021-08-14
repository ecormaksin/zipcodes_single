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
import com.example.zipcodes.infra.db.jpa.entity.CityResource;
import com.example.zipcodes.infra.db.jpa.entity.QCityResource;
import com.example.zipcodes.infra.db.jpa.mapper.CityResourceMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CityRepositoryImpl implements CityRepository {

    private final EntityManager entityManager;
    private final CityResourceMapper cityResourceMapper;

    private JPAQueryFactory queryFactory;

    private QCityResource qCity = QCityResource.cityResource;

    @PostConstruct
    void postConstruct() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<City> findByPrefectureCode(final PrefectureCode prefectureCode) {

        // @formatter:off
        List<CityResource> cities = queryFactory
                .selectFrom(qCity)
                .where( qCity.prefectureCode.eq(prefectureCode.getValue()) )
                .orderBy( 
                        qCity.prefectureCode.asc()
                        , qCity.japaneseLocalGovermentCode.asc() 
                ).fetch();
        // @formatter:on

        return cityResourceMapper.fromEntityListToDomainObjectList(cities);
    }

    @Override
    public Optional<City> findByJapaneseLocalGovernmentCode(
            final JapaneseLocalGovernmentCode japaneseLocalGovernmentCode) {

        // @formatter:off
        CityResource city = queryFactory
                .selectFrom(qCity)
                .where( qCity.japaneseLocalGovermentCode.eq(japaneseLocalGovernmentCode.getValue()) )
                .fetchOne();
        
        return null == city ?
                Optional.empty()
                : Optional.of(cityResourceMapper.fromEntityToDomainObject(city));
        // @formatter:on
    }

    @Override
    public List<City> findByKeywords(final String keywords) {

        final BooleanBuilder keywordCondition = KeywordsConditionBuilder.build(qCity.cityName, qCity.cityNameKana,
                keywords);

        // @formatter:off
        List<CityResource> cities = queryFactory
                .selectFrom(qCity)
                .where( keywordCondition )
                .orderBy( 
                        qCity.prefectureCode.asc()
                        , qCity.japaneseLocalGovermentCode.asc() 
                ).fetch();
        // @formatter:on

        return cityResourceMapper.fromEntityListToDomainObjectList(cities);
    }

}

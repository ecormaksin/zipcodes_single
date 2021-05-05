package com.example.zipcodes.infra.repository;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureRepository;
import com.example.zipcodes.infra.db.jpa.mapper.PrefectureEntityMapper;
import com.example.zipcodes.infra.db.jpa.view.PrefectureResource;
import com.example.zipcodes.infra.db.jpa.view.QPrefectureResource;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PrefectureRepositoryImpl implements PrefectureRepository {

    private final EntityManager entityManager;
    private final PrefectureEntityMapper prefectureEntityMapper;

    private JPAQueryFactory queryFactory;

    private QPrefectureResource qPrefecture = QPrefectureResource.prefectureResource;

    @PostConstruct
    void postConstruct() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Prefecture> findAll() {

        // @formatter:off
		List<PrefectureResource> entityList = queryFactory
				.selectFrom(qPrefecture)
				.orderBy(qPrefecture.prefectureCode.asc())
				.fetch();
		// @formatter:on

        return prefectureEntityMapper.fromEntityListToDomainObjectList(entityList);
    }

    @Override
    public Optional<Prefecture> findByPrefectureCode(final PrefectureCode prefectureCode) {

        // @formatter:off
		PrefectureResource prefecture = queryFactory
				.selectFrom(qPrefecture)
				.where( qPrefecture.prefectureCode.eq(prefectureCode.getValue()) )
				.fetchOne();
		// @formatter:on

        if (null == prefecture) {
            return Optional.empty();
        }

        return Optional.of(prefectureEntityMapper.fromEntityToDomainObject(prefecture));
    }
}

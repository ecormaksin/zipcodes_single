package com.example.zipcodes.infra.repository;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureRepository;
import com.example.zipcodes.infra.db.jpa.mappedsuperclass.QPrefectureResource;
import com.example.zipcodes.infra.db.jpa.entity.QZipCodeResource;
import com.example.zipcodes.infra.db.jpa.mappedsuperclass.PrefectureResource;
import com.example.zipcodes.infra.db.jpa.mapper.PrefectureResourceMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.sql.SQLExpressions;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PrefectureRepositoryImpl implements PrefectureRepository {

	private final EntityManager entityManager;
	private final PrefectureResourceMapper prefectureResourceMapper;

	private JPAQueryFactory queryFactory;

	private QPrefectureResource qPrefecture = QPrefectureResource.prefectureResource;
	private QZipCodeResource qZipCodeResource = QZipCodeResource.zipCodeResource;

	@PostConstruct
	void postConstruct() {
		queryFactory = new JPAQueryFactory(entityManager);
	}

	@Override
	@Cacheable("prefectures")
	public List<Prefecture> findAll() {

		final StringExpression prefectureCodeExpr = qZipCodeResource.japaneseLocalGovermentCode.substring(0, 2);

		// @formatter:off
		List<PrefectureResource> resources = SQLExpressions.selectDistinct(
				Projections.constructor(PrefectureResource.class
				, prefectureCodeExpr
				, qZipCodeResource.prefectureName
				, qZipCodeResource.prefectureNameKana
		)).from(qPrefecture)
		.where(qZipCodeResource.updateDisplayFlag.in("0", "1"))
		.orderBy(prefectureCodeExpr.asc())
		.fetch();
		/*
        List<PrefectureResource> resources = queryFactory
                .selectFrom(qPrefecture)
                .orderBy(qPrefecture.prefectureCode.asc())
                .fetch();
          */
        // @formatter:on

		return prefectureResourceMapper.fromEntityListToDomainObjectList(resources);
	}

	@Override
	@Cacheable("prefecture")
	public Optional<Prefecture> findByPrefectureCode(final PrefectureCode prefectureCode) {

		// @formatter:off
        PrefectureResource entity = queryFactory
                .selectFrom(qPrefecture)
                .where( qPrefecture.prefectureCode.eq(prefectureCode.getValue()) )
                .fetchOne();

        return null == entity ? 
                Optional.empty()
                : Optional.of(prefectureResourceMapper.fromEntityToDomainObject(entity));
        // @formatter:on

	}

	@Override
	@Cacheable("prefectures")
	public List<Prefecture> findByPrefectureCode(final String keywords) {

		final BooleanBuilder keywordCondition = KeywordsConditionBuilder.build(qPrefecture.prefectureName,
				qPrefecture.prefectureNameKana, keywords);

		// @formatter:off
        List<PrefectureResource> entities = queryFactory
                .selectFrom(qPrefecture)
                .where( keywordCondition )
                .orderBy( 
                        qPrefecture.prefectureCode.asc()
                ).fetch();
        // @formatter:on

		return prefectureResourceMapper.fromEntityListToDomainObjectList(entities);
	}
}

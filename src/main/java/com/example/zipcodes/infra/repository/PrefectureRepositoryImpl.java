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
import com.example.zipcodes.infra.db.jpa.entity.QZipCodeResource;
import com.example.zipcodes.infra.db.jpa.mappedsuperclass.PrefectureResource;
import com.example.zipcodes.infra.db.jpa.mapper.PrefectureResourceMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PrefectureRepositoryImpl implements PrefectureRepository {

	private final EntityManager entityManager;
	private final PrefectureResourceMapper prefectureResourceMapper;

	private JPAQueryFactory queryFactory;

	private final QZipCodeResource qZipCodeResource = QZipCodeResource.zipCodeResource;
	private final StringExpression prefectureCodeExpr = qZipCodeResource.japaneseLocalGovermentCode.substring(0, 2);
	// @formatter:off
	private final ConstructorExpression<PrefectureResource> prefectureConstructorExpr = Projections.constructor(PrefectureResource.class
			, prefectureCodeExpr
			, qZipCodeResource.prefectureName
			, qZipCodeResource.prefectureNameKana
		);
	// @formatter:on
	private final BooleanExpression updateDisplayFlagExpr = qZipCodeResource.updateDisplayFlag.in("0", "1");

	@PostConstruct
	void postConstruct() {
		queryFactory = new JPAQueryFactory(entityManager);
	}

	@Override
	@Cacheable("prefectures")
	public List<Prefecture> findAll() {

		// @formatter:off
		List<PrefectureResource> resources = queryFactory.selectDistinct(
				prefectureConstructorExpr
			).from(qZipCodeResource)
			.where(updateDisplayFlagExpr)
			.orderBy(prefectureCodeExpr.asc())
			.fetch();
        // @formatter:on

		return prefectureResourceMapper.fromEntityListToDomainObjectList(resources);
	}

	@Override
	@Cacheable("prefecture")
	public Optional<Prefecture> findByPrefectureCode(final PrefectureCode prefectureCode) {

		// @formatter:off
        PrefectureResource entity = queryFactory.selectDistinct(
				prefectureConstructorExpr
			).from(qZipCodeResource)
			.where(
				updateDisplayFlagExpr
				.and(prefectureCodeExpr.eq(prefectureCode.getValue()))
			).fetchOne();

        return null == entity ? 
                Optional.empty()
                : Optional.of(prefectureResourceMapper.fromEntityToDomainObject(entity));
        // @formatter:on

	}

	@Override
	@Cacheable("prefectures")
	public List<Prefecture> findByPrefectureCode(final String keywords) {

		final BooleanBuilder keywordCondition = KeywordsConditionBuilder.build(qZipCodeResource.prefectureName,
				qZipCodeResource.prefectureNameKana, keywords);

		// @formatter:off
        List<PrefectureResource> entities = queryFactory.selectDistinct(
				prefectureConstructorExpr
			).from(qZipCodeResource)
			.where(
				updateDisplayFlagExpr
				.and(keywordCondition)
			).orderBy(prefectureCodeExpr.asc())
			.fetch();
        // @formatter:on

		return prefectureResourceMapper.fromEntityListToDomainObjectList(entities);
	}
}

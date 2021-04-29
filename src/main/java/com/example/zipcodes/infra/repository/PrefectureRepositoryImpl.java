package com.example.zipcodes.infra.repository;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureNotFoundException;
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
    private final PrefectureEntityMapper prefectureMapper;
    private final MessageSource messageSource;

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

        return prefectureMapper.fromEntityListToDomainObjectList(entityList);
    }

    @Override
    public Prefecture findByPrefectureCode(final PrefectureCode prefectureCode) throws PrefectureNotFoundException {

        final String prefecutureCodeStr = prefectureCode.getValue();

        // @formatter:off
		PrefectureResource prefecture = queryFactory
				.selectFrom(qPrefecture)
				.where( qPrefecture.prefectureCode.eq(prefecutureCodeStr) )
				.fetchOne();
		// @formatter:on

        if (null == prefecture) {
            throw new PrefectureNotFoundException(messageSource.getMessage("message.prefecture.not.found",
                    new Object[] { prefecutureCodeStr }, Locale.getDefault()));
        }

        return prefectureMapper.fromEntityToDomainObject(prefecture);
    }
}

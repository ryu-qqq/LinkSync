package com.ryuqq.linksyncserver.module.site.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.linksyncserver.module.site.dto.LanguageCodeResponse;
import com.ryuqq.linksyncserver.module.site.dto.QLanguageCodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.ryuqq.linksyncserver.module.site.entity.QLanguageCode.languageCode;


@Repository
@RequiredArgsConstructor
public class LanguageQueryRepositoryImpl implements LanguageQueryRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<LanguageCodeResponse> fetchLanguageCode(String code) {
        return Optional.ofNullable(
                queryFactory.select(
                        new QLanguageCodeResponse(
                                languageCode.id,
                                languageCode.languageName
                            )
                        )
                        .from(languageCode)
                        .where(languageCodeEq(code))
                        .fetchOne()

        );
    }

    private BooleanExpression languageCodeEq(String code) {
        return languageCode.id.eq(code);
    }

}

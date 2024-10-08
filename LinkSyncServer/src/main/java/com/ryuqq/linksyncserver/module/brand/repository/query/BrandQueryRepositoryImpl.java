package com.ryuqq.linksyncserver.module.brand.repository.query;

import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.dto.query.QBrandResponse;
import com.ryuqq.linksyncserver.module.brand.entity.QBrand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.ryuqq.linksyncserver.module.brand.entity.QBrand.brand;

@RequiredArgsConstructor
@Repository
public class BrandQueryRepositoryImpl implements BrandQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<BrandResponse> fetchBrandResponse(long brandId) {
        return Optional.ofNullable(
                queryFactory.select(
                        new QBrandResponse(
                            brand.id,
                            brand.brandName,
                            brand.languageCode
                        ))
                        .from(brand)
                        .where(brandIdEq(brandId))
                        .fetchOne()
        );
    }

    @Override
    public Optional<BrandResponse> fetchBrandResponse(String brandName, String languageCode) {
        return Optional.ofNullable(
                queryFactory.select(
                                new QBrandResponse(
                                        brand.id,
                                        brand.brandName,
                                        brand.languageCode
                                ))
                        .from(brand)
                        .where(brandNameEq(brandName), languageCodeEq(languageCode))
                        .fetchOne()
        );
    }


    private BooleanExpression brandIdEq(long brandId){
        return brand.id.eq(brandId);
    }

    private BooleanExpression brandNameEq(String brandName){
        return brand.brandName.eq(brandName);
    }

    private BooleanExpression languageCodeEq(String languageCode){
        return brand.languageCode.eq(languageCode);
    }

}

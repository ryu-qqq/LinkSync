package com.ryuqq.linksyncserver.module.brand.repository.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.dto.query.QBrandResponse;
import com.ryuqq.linksyncserver.module.brand.filter.BrandFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public List<BrandResponse> fetchBrandResponses(BrandFilter brandFilter, Pageable pageable) {
        return queryFactory.select(
                        new QBrandResponse(
                                brand.id,
                                brand.brandName,
                                brand.languageCode
                        ))
                .from(brand)
                .orderBy(brand.id.desc())
                .limit(pageable.getPageSize())
                .where(
                        brandNameIn(brandFilter.getBrandNames()),
                        languageCodeIn(brandFilter.getLanguageCodes()),
                        isBrandIdLt(brandFilter.getLastDomainId())
                )
                .fetch();
    }

    @Override
    public JPAQuery<Long> fetchBrandCountQuery(BrandFilter brandFilter) {
        return queryFactory.select(
                    brand.count()
                )
                .from(brand)
                .where(
                        brandNameIn(brandFilter.getBrandNames()),
                        languageCodeIn(brandFilter.getLanguageCodes()),
                        isBrandIdLt(brandFilter.getLastDomainId())
                );
    }


    private BooleanExpression brandIdEq(long brandId){
        return brand.id.eq(brandId);
    }

    private BooleanExpression brandNameEq(String brandName){
        return brand.brandName.eq(brandName);
    }

    private BooleanExpression brandNameIn(List<String> brandNames) {
        return (brandNames != null && !brandNames.isEmpty()) ? brand.brandName.in(brandNames) : null;
    }

    private BooleanExpression languageCodeEq(String languageCode){
        return brand.languageCode.eq(languageCode);
    }

    private BooleanExpression languageCodeIn(List<String> languageCodes){
        return (languageCodes != null && !languageCodes.isEmpty()) ? brand.languageCode.in(languageCodes) : null;
    }

    private BooleanExpression isBrandIdLt(Long lastDomainId){
        if(lastDomainId !=null) return brand.id.lt(lastDomainId);
        else return null;
    }

}

package com.ryuqq.linksyncserver.module.brand.repository.query;

import com.querydsl.jpa.impl.JPAQuery;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.filter.BrandFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BrandQueryRepository {

    Optional<BrandResponse> fetchBrandResponse(long brandId);
    Optional<BrandResponse> fetchBrandResponse(String brandName, String languageCode);

    List<BrandResponse> fetchBrandResponses(BrandFilter brandFilter, Pageable pageable);
    JPAQuery<Long> fetchBrandCountQuery(BrandFilter brandFilter);
}

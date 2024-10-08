package com.ryuqq.linksyncserver.module.brand.repository.query;

import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;

import java.util.Optional;

public interface BrandQueryRepository {

    Optional<BrandResponse> fetchBrandResponse(long brandId);
    Optional<BrandResponse> fetchBrandResponse(String brandName, String languageCode);
}

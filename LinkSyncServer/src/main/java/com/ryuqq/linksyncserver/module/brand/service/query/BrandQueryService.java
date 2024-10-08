package com.ryuqq.linksyncserver.module.brand.service.query;

import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;

public interface BrandQueryService {

    BrandResponse getBrand(long brandId);
    BrandResponse getBrand(String brandName, String languageCode);

}

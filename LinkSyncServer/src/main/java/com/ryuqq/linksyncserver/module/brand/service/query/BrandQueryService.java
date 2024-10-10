package com.ryuqq.linksyncserver.module.brand.service.query;

import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.filter.BrandFilter;
import com.ryuqq.linksyncserver.module.generic.CustomSlice;
import org.springframework.data.domain.Pageable;

public interface BrandQueryService {

    BrandResponse getBrand(long brandId);
    BrandResponse getBrand(String brandName, String languageCode);
    boolean brandExists(String brandName, String languageCode);

    CustomSlice<BrandResponse> getBrands(BrandFilter filter, Pageable pageable);

}

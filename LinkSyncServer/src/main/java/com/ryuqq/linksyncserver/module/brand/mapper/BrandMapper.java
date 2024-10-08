package com.ryuqq.linksyncserver.module.brand.mapper;

import com.ryuqq.linksyncserver.module.brand.dto.command.CreateBrandRequest;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.entity.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public Brand toEntity(CreateBrandRequest request) {
        return Brand.builder()
                .brandName(request.getBrandName())
                .languageCode(request.getLanguageCode())
                .build();
    }

    public BrandResponse toResponse(Brand brand) {
        return new BrandResponse(brand.getId(), brand.getBrandName(), brand.getLanguageCode());
    }
}
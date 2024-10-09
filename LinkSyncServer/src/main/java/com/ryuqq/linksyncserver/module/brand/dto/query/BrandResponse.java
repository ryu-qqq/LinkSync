package com.ryuqq.linksyncserver.module.brand.dto.query;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandResponse {

    private long brandId;
    private String brandName;
    private String languageCode;

    @QueryProjection
    public BrandResponse(long brandId, String brandName, String languageCode) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.languageCode = languageCode;
    }

}

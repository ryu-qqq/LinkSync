package com.ryuqq.linksyncserver.module.brand.dto.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.linksyncserver.module.generic.LastDomainIdProvider;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandResponse implements LastDomainIdProvider {

    private long brandId;
    private String brandName;
    private String languageCode;

    @QueryProjection
    public BrandResponse(long brandId, String brandName, String languageCode) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.languageCode = languageCode;
    }

    @JsonIgnore
    @Override
    public Long getId() {
        return brandId;
    }

}

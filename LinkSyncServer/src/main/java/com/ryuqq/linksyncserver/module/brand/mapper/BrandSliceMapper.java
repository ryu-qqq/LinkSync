package com.ryuqq.linksyncserver.module.brand.mapper;

import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.generic.CustomSlice;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandSliceMapper {
    CustomSlice<BrandResponse> toSlice(List<BrandResponse> brandResponses, Pageable pageable, long totalElements);
}

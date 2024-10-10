package com.ryuqq.linksyncserver.module.brand.mapper;

import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.generic.CustomSlice;
import com.ryuqq.linksyncserver.module.generic.mapper.AbstractGeneralSliceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandSliceMapperImpl extends AbstractGeneralSliceMapper<BrandResponse> implements BrandSliceMapper{

    @Override
    public CustomSlice<BrandResponse> toSlice(List<BrandResponse> brandResponses, Pageable pageable, long totalElements) {
        return super.toSlice(brandResponses, pageable, totalElements);
    }

}

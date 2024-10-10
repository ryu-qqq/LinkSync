package com.ryuqq.linksyncserver.module.brand.service.query;

import com.querydsl.jpa.impl.JPAQuery;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.exception.BrandNotFoundException;
import com.ryuqq.linksyncserver.module.brand.filter.BrandFilter;
import com.ryuqq.linksyncserver.module.brand.mapper.BrandSliceMapper;
import com.ryuqq.linksyncserver.module.brand.repository.query.BrandQueryRepository;
import com.ryuqq.linksyncserver.module.generic.CustomSlice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BrandQueryServiceImpl implements BrandQueryService{

    private final BrandQueryRepository brandQueryRepository;
    private final BrandSliceMapper brandSliceMapper;

    @Override
    public BrandResponse getBrand(long brandId) {
        return brandQueryRepository.fetchBrandResponse(brandId)
                .orElseThrow(() -> new BrandNotFoundException(brandId));
    }

    @Override
    public BrandResponse getBrand(String brandName, String languageCode) {
        return brandQueryRepository.fetchBrandResponse(brandName, languageCode)
                .orElseThrow(() -> new BrandNotFoundException(brandName, languageCode));
    }

    @Override
    public boolean brandExists(String brandName, String languageCode) {
        return brandQueryRepository.fetchBrandResponse(brandName, languageCode).isPresent();
    }

    @Override
    public CustomSlice<BrandResponse> getBrands(BrandFilter filter, Pageable pageable) {
        List<BrandResponse> brandResponses = brandQueryRepository.fetchBrandResponses(filter, pageable);

        if(brandResponses.isEmpty()) {
            return brandSliceMapper.toSlice(brandResponses, pageable, 0);
        }

        long brandCounts = getBrandCounts(filter);
        return brandSliceMapper.toSlice(brandResponses, pageable, brandCounts);
    }



    private long getBrandCounts(BrandFilter filter) {
        JPAQuery<Long> longJPAQuery = brandQueryRepository.fetchBrandCountQuery(filter);
        Long totalCount = longJPAQuery.fetchOne();
        if (totalCount == null) {
            return 0L;
        }
        return totalCount;
    }

}

package com.ryuqq.linksyncserver.module.brand.service.query;

import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.exception.BrandNotFoundException;
import com.ryuqq.linksyncserver.module.brand.repository.query.BrandQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BrandQueryServiceImpl implements BrandQueryService{

    private final BrandQueryRepository brandQueryRepository;

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

}

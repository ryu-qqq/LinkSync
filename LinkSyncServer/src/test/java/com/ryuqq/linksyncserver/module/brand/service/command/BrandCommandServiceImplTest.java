package com.ryuqq.linksyncserver.module.brand.service.command;

import com.ryuqq.linksyncserver.data.brand.BrandModuleHelper;
import com.ryuqq.linksyncserver.module.brand.dto.command.CreateBrandRequest;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.entity.Brand;
import com.ryuqq.linksyncserver.module.brand.mapper.BrandMapper;
import com.ryuqq.linksyncserver.module.brand.repository.command.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandCommandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    @InjectMocks
    private BrandCommandServiceImpl brandCommandService;


    @Test
    void shouldCreateBrandSuccessfully() {
        CreateBrandRequest request = BrandModuleHelper.toCreateBrandRequest();
        Brand brand = BrandModuleHelper.toBrand(request.getBrandName(), request.getLanguageCode());
        BrandResponse expectedResponse = BrandModuleHelper.toBrandResponse(brand.getId(), brand.getBrandName(), brand.getLanguageCode());

        when(brandMapper.toEntity(request)).thenReturn(brand);
        when(brandRepository.save(brand)).thenReturn(brand);
        when(brandMapper.toResponse(brand)).thenReturn(expectedResponse);

        BrandResponse result = brandCommandService.createBrand(request);
        assertEquals(expectedResponse, result);

        verify(brandMapper).toEntity(request);
        verify(brandRepository).save(brand);
        verify(brandMapper).toResponse(brand);
    }

}
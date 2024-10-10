package com.ryuqq.linksyncserver.module.brand.service.query;

import com.querydsl.jpa.impl.JPAQuery;
import com.ryuqq.linksyncserver.data.brand.BrandModuleHelper;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.exception.BrandNotFoundException;
import com.ryuqq.linksyncserver.module.brand.filter.BrandFilter;
import com.ryuqq.linksyncserver.module.brand.mapper.BrandSliceMapper;
import com.ryuqq.linksyncserver.module.brand.repository.query.BrandQueryRepository;
import com.ryuqq.linksyncserver.module.generic.CustomSlice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandQueryServiceImplTest {

    @Mock
    BrandSliceMapper brandSliceMapper;

    @Mock
    BrandQueryRepository brandQueryRepository;

    @InjectMocks
    BrandQueryServiceImpl brandQueryService;


    @Test
    void shouldReturnBrandResponseWhenBrandExistsById() {
        long brandId = 1L;
        BrandResponse expectedResponse = BrandModuleHelper.toBrandResponse();

        when(brandQueryRepository.fetchBrandResponse(brandId)).thenReturn(Optional.of(expectedResponse));

        BrandResponse result = brandQueryService.getBrand(brandId);
        assertEquals(expectedResponse, result);
    }

    @Test
    void shouldThrowBrandNotFoundExceptionWhenBrandDoesNotExistById() {
        long brandId = 1L;

        when(brandQueryRepository.fetchBrandResponse(brandId)).thenReturn(java.util.Optional.empty());

        assertThrows(BrandNotFoundException.class, () -> brandQueryService.getBrand(brandId));
    }


    @Test
    void shouldReturnBrandResponseWhenBrandExistsByNameAndLanguageCode() {
        BrandResponse expectedResponse = BrandModuleHelper.toBrandResponse();
        String brandName = expectedResponse.getBrandName();
        String languageCode = expectedResponse.getLanguageCode();


        when(brandQueryRepository.fetchBrandResponse(brandName, languageCode)).thenReturn(java.util.Optional.of(expectedResponse));

        BrandResponse result = brandQueryService.getBrand(brandName, languageCode);
        assertEquals(expectedResponse, result);
    }

    @Test
    void shouldThrowBrandNotFoundExceptionWhenBrandDoesNotExistByNameAndLanguageCode() {
        BrandResponse expectedResponse = BrandModuleHelper.toBrandResponse();
        String brandName = expectedResponse.getBrandName();
        String languageCode = expectedResponse.getLanguageCode();

        when(brandQueryRepository.fetchBrandResponse(brandName, languageCode)).thenReturn(java.util.Optional.empty());

        assertThrows(BrandNotFoundException.class, () -> brandQueryService.getBrand(brandName, languageCode));
    }

    @Test
    void shouldReturnCustomSliceOfBrands() {
        BrandFilter filter = new BrandFilter(List.of("Nike"), List.of("en"));
        Pageable pageable = PageRequest.of(0, 10);
        List<BrandResponse> brandResponses = List.of(BrandModuleHelper.toBrandResponse());
        long totalBrands = 1L;

        JPAQuery<Long> mockedCountQuery = mock(JPAQuery.class);
        CustomSlice<BrandResponse> expectedSlice = BrandModuleHelper.toCustomBrandResponseSlice(brandResponses, pageable, totalBrands);

        when(brandQueryRepository.fetchBrandResponses(filter, pageable)).thenReturn(brandResponses);
        when(brandQueryRepository.fetchBrandCountQuery(filter)).thenReturn(mockedCountQuery);
        when(mockedCountQuery.fetchOne()).thenReturn(totalBrands);
        when(brandSliceMapper.toSlice(brandResponses, pageable, totalBrands)).thenReturn(expectedSlice);

        CustomSlice<BrandResponse> result = brandQueryService.getBrands(filter, pageable);

        assertEquals(expectedSlice, result);
    }

    @Test
    void shouldReturnEmptyCustomSliceWhenNoBrandsFound() {
        BrandFilter filter = new BrandFilter(List.of("NonExistentBrand"), List.of("xx"));
        Pageable pageable = PageRequest.of(0, 10);
        List<BrandResponse> brandResponses = List.of();
        long totalBrands = 0L;


        CustomSlice<BrandResponse> expectedSlice = BrandModuleHelper.toCustomBrandResponseSlice(brandResponses, pageable, totalBrands);

        when(brandQueryRepository.fetchBrandResponses(filter, pageable)).thenReturn(brandResponses);
        when(brandSliceMapper.toSlice(brandResponses, pageable, totalBrands)).thenReturn(expectedSlice);

        CustomSlice<BrandResponse> result = brandQueryService.getBrands(filter, pageable);

        assertEquals(expectedSlice, result);
        assertTrue(result.getContent().isEmpty());
        assertEquals(0L, result.getTotalElements());
    }

    @Test
    void shouldCheckIfBrandExists() {
        String brandName = "Nike";
        String languageCode = "en";
        when(brandQueryRepository.fetchBrandResponse(brandName, languageCode)).thenReturn(Optional.of(BrandModuleHelper.toBrandResponse()));

        boolean exists = brandQueryService.brandExists(brandName, languageCode);

        assertTrue(exists);
    }

    @Test
    void shouldReturnFalseIfBrandDoesNotExist() {
        String brandName = "NonExistentBrand";
        String languageCode = "xx";
        when(brandQueryRepository.fetchBrandResponse(brandName, languageCode)).thenReturn(Optional.empty());

        boolean exists = brandQueryService.brandExists(brandName, languageCode);

        assertFalse(exists);
    }



}
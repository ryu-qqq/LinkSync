package com.ryuqq.linksyncserver.module.brand.service.query;

import com.ryuqq.linksyncserver.data.brand.BrandModuleHelper;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.exception.BrandNotFoundException;
import com.ryuqq.linksyncserver.module.brand.repository.query.BrandQueryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandQueryServiceImplTest {

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


}
package com.ryuqq.linksyncserver.module.brand.repository.query;

import com.ryuqq.linksyncserver.data.brand.BrandModuleHelper;
import com.ryuqq.linksyncserver.document.utils.BaseQueryRepositoryTest;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.entity.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BrandQueryRepositoryImplTest extends BaseQueryRepositoryTest {

    @Autowired
    BrandQueryRepositoryImpl brandQueryRepository;

    protected Brand brand;

    @BeforeEach
    void setUp() {
        saveBrand();
    }

    protected void saveBrand(){
        Brand newBrand = BrandModuleHelper.toBrandWithNoId();
        getEntityManager().persist(newBrand);
        brand = newBrand;
    }

    @Test
    void shouldFetchBrandById() {
        Brand savedBrand = this.brand;

        Optional<BrandResponse> result = brandQueryRepository.fetchBrandResponse(savedBrand.getId());

        assertTrue(result.isPresent());
        assertEquals(savedBrand.getId(), result.get().getBrandId());
        assertEquals(savedBrand.getBrandName(), result.get().getBrandName());
        assertEquals(savedBrand.getLanguageCode(), result.get().getLanguageCode());
    }

    @Test
    void shouldFetchBrandByNameAndLanguageCode() {
        Brand savedBrand = this.brand;

        Optional<BrandResponse> result = brandQueryRepository.fetchBrandResponse(savedBrand.getBrandName(), savedBrand.getLanguageCode());

        assertTrue(result.isPresent());
        assertEquals(savedBrand.getId(), result.get().getBrandId());
        assertEquals(savedBrand.getBrandName(), result.get().getBrandName());
        assertEquals(savedBrand.getLanguageCode(), result.get().getLanguageCode());
    }


    @Test
    void shouldReturnEmptyWhenBrandNotFoundById() {
        Optional<BrandResponse> result = brandQueryRepository.fetchBrandResponse(999L);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenBrandNotFoundByNameAndLanguageCode() {
        Optional<BrandResponse> result = brandQueryRepository.fetchBrandResponse("NonExistentBrand", "xx");
        assertTrue(result.isEmpty());
    }

}
package com.ryuqq.linksyncserver.module.brand.repository.query;

import com.ryuqq.linksyncserver.data.brand.BrandModuleHelper;
import com.ryuqq.linksyncserver.document.utils.BaseQueryRepositoryTest;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.entity.Brand;
import com.ryuqq.linksyncserver.module.brand.filter.BrandFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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


    @Test
    void shouldFetchBrandResponsesWithPagination() {
        BrandFilter brandFilter = new BrandFilter(List.of(brand.getBrandName()), List.of(brand.getLanguageCode()));
        Pageable pageable = PageRequest.of(0, 10);

        List<BrandResponse> result = brandQueryRepository.fetchBrandResponses(brandFilter, pageable);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(brand.getId(), result.getFirst().getBrandId());
        assertEquals(brand.getBrandName(), result.getFirst().getBrandName());
        assertEquals(brand.getLanguageCode(), result.getFirst().getLanguageCode());
    }


    @Test
    void shouldReturnEmptyBrandResponsesWhenNoMatches() {
        BrandFilter brandFilter = new BrandFilter(List.of("NonExistentBrand"), List.of("xx"));
        Pageable pageable = PageRequest.of(0, 10);

        List<BrandResponse> result = brandQueryRepository.fetchBrandResponses(brandFilter, pageable);

        assertTrue(result.isEmpty());
    }


    @Test
    void shouldReturnBrandCount() {
        BrandFilter brandFilter = new BrandFilter(List.of(brand.getBrandName()), List.of(brand.getLanguageCode()));

        long count = brandQueryRepository.fetchBrandCountQuery(brandFilter).fetchOne();

        assertEquals(1, count);
    }

    @Test
    void shouldReturnZeroBrandCountWhenNoMatches() {
        BrandFilter brandFilter = new BrandFilter(List.of("NonExistentBrand"), List.of("xx"));

        long count = brandQueryRepository.fetchBrandCountQuery(brandFilter).fetchOne();

        assertEquals(0, count);
    }

}
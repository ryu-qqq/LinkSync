package com.ryuqq.linksyncserver.module.generic.mapper;

import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.generic.CustomSlice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractGeneralSliceMapperTest {

    private static class GeneralSliceMapperImpl extends AbstractGeneralSliceMapper<BrandResponse> {
    }

    private GeneralSliceMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new GeneralSliceMapperImpl();
    }

    @Test
    void shouldReturnCustomSliceWithTotalElements() {
        // Given
        List<BrandResponse> brandResponses = List.of(
                new BrandResponse(1L, "Nike", "en"),
                new BrandResponse(2L, "Adidas", "en")
        );
        Pageable pageable = PageRequest.of(0, 10);
        long totalElements = 2L;

        CustomSlice<BrandResponse> customSlice = mapper.toSlice(brandResponses, pageable, totalElements);

        assertEquals(brandResponses.size(), customSlice.getContent().size());
        assertEquals(totalElements, customSlice.getTotalElements());
        assertFalse(customSlice.isEmpty());
        assertEquals(2L, customSlice.getLastDomainId());
    }

    @Test
    void shouldReturnCustomSliceWithoutTotalElements() {
        List<BrandResponse> brandResponses = List.of(
                new BrandResponse(1L, "Nike", "en"),
                new BrandResponse(2L, "Adidas", "en")
        );
        Pageable pageable = PageRequest.of(0, 10);

        CustomSlice<BrandResponse> customSlice = mapper.toSlice(brandResponses, pageable);

        assertEquals(brandResponses.size(), customSlice.getContent().size());
        assertNull(customSlice.getTotalElements());
        assertFalse(customSlice.isEmpty());
        assertEquals(2L, customSlice.getLastDomainId());
    }

    @Test
    void shouldReturnEmptyCustomSlice() {
        List<BrandResponse> brandResponses = List.of();
        Pageable pageable = PageRequest.of(0, 10);

        CustomSlice<BrandResponse> customSlice = mapper.toSlice(brandResponses, pageable);

        assertTrue(customSlice.isEmpty());
        assertNull(customSlice.getLastDomainId());  // lastDomainId는 null이어야 함
    }




}
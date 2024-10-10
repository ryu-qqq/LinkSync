package com.ryuqq.linksyncserver.module.brand.controller;

import com.ryuqq.linksyncserver.module.brand.dto.command.CreateBrandRequest;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.filter.BrandFilter;
import com.ryuqq.linksyncserver.module.brand.service.command.BrandCommandService;
import com.ryuqq.linksyncserver.module.brand.service.query.BrandQueryService;
import com.ryuqq.linksyncserver.module.common.ApiResponse;
import com.ryuqq.linksyncserver.module.generic.CustomSlice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ryuqq.linksyncserver.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
@RequiredArgsConstructor
public class BrandController {

    private final BrandCommandService brandCommandService;
    private final BrandQueryService brandQueryService;

    @PostMapping("/brand")
    public ResponseEntity<ApiResponse<BrandResponse>> createBrand(@RequestBody @Validated CreateBrandRequest createBrandRequest) {
        return ResponseEntity.ok(ApiResponse.success(brandCommandService.createBrand(createBrandRequest)));
    }

    @GetMapping("/brands")
    public ResponseEntity<ApiResponse<CustomSlice<BrandResponse>>> getBrands(@ModelAttribute BrandFilter brandFilter, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(brandQueryService.getBrands(brandFilter, pageable)));
    }


}

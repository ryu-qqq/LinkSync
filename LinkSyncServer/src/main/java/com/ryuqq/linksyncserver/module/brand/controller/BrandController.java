package com.ryuqq.linksyncserver.module.brand.controller;

import com.ryuqq.linksyncserver.module.brand.dto.command.CreateBrandRequest;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.service.command.BrandCommandService;
import com.ryuqq.linksyncserver.module.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ryuqq.linksyncserver.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
@RequiredArgsConstructor
public class BrandController {

    private final BrandCommandService brandCommandService;

    @PostMapping("/brand")
    public ResponseEntity<ApiResponse<BrandResponse>> createBrand(@RequestBody @Validated CreateBrandRequest createBrandRequest) {
        return ResponseEntity.ok(ApiResponse.success(brandCommandService.createBrand(createBrandRequest)));
    }


}

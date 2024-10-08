package com.ryuqq.linksyncserver.module.brand.validator;

import com.ryuqq.linksyncserver.module.brand.dto.command.CreateBrandRequest;
import com.ryuqq.linksyncserver.module.brand.exception.BrandNotFoundException;
import com.ryuqq.linksyncserver.module.brand.service.query.BrandQueryService;
import com.ryuqq.linksyncserver.module.site.exception.LanguageCodeNotFoundException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandValidator implements ConstraintValidator<BrandValidate, CreateBrandRequest> {

    private final BrandQueryService brandQueryService;

    @Override
    public boolean isValid(CreateBrandRequest createBrandRequest, ConstraintValidatorContext context) {
        try {
            brandQueryService.getBrand(createBrandRequest.getBrandName(), createBrandRequest.getLanguageCode());
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Brand already exists with the given name and language code")
                    .addConstraintViolation();
            return false;
        } catch (BrandNotFoundException e) {
            return true;
        }
    }



}

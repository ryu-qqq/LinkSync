package com.ryuqq.linksyncserver.module.brand.validator;

import com.ryuqq.linksyncserver.module.brand.dto.command.CreateBrandRequest;
import com.ryuqq.linksyncserver.module.brand.service.query.BrandQueryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandValidator implements ConstraintValidator<BrandValidate, CreateBrandRequest> {

    private final BrandQueryService brandQueryService;

    @Override
    public boolean isValid(CreateBrandRequest createBrandRequest, ConstraintValidatorContext context) {
        boolean exists = brandQueryService.brandExists(createBrandRequest.getBrandName(), createBrandRequest.getLanguageCode());
        if(exists){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Brand already exists with the given name and language code")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}

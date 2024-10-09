package com.ryuqq.linksyncserver.module.brand.validator;

import com.ryuqq.linksyncserver.data.brand.BrandModuleHelper;
import com.ryuqq.linksyncserver.data.validator.ValidatorHelper;
import com.ryuqq.linksyncserver.module.brand.dto.command.CreateBrandRequest;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.exception.BrandNotFoundException;
import com.ryuqq.linksyncserver.module.brand.service.query.BrandQueryService;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandValidatorTest {

    @Mock
    private BrandQueryService brandQueryService;

    @InjectMocks
    private BrandValidator brandValidator;


    @Test
    void shouldReturnTrueWhenBrandDoesNotExist() {

        CreateBrandRequest request = BrandModuleHelper.toCreateBrandRequest();

        when(brandQueryService.brandExists(request.getBrandName(), request.getLanguageCode()))
                .thenReturn(false);

        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        boolean result = brandValidator.isValid(request, context);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenBrandExists() {
        CreateBrandRequest request = BrandModuleHelper.toCreateBrandRequest();

        when(brandQueryService.brandExists(request.getBrandName(), request.getLanguageCode()))
                .thenReturn(true);

        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(violationBuilder);
        when(violationBuilder.addConstraintViolation()).thenReturn(context);

        boolean result = brandValidator.isValid(request, context);

        assertFalse(result);
        verify(context).disableDefaultConstraintViolation();
        verify(context).buildConstraintViolationWithTemplate("Brand already exists with the given name and language code");
    }



}
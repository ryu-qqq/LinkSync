package com.ryuqq.linksyncserver.module.site.validator;

import com.ryuqq.linksyncserver.module.site.exception.LanguageCodeNotFoundException;
import com.ryuqq.linksyncserver.module.site.service.LanguageCodeQueryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LanguageCodeValidator implements ConstraintValidator<LanguageCodeValidate, String> {

    private final LanguageCodeQueryService languageCodeQueryService;

    @Override
    public boolean isValid(String languageCode, ConstraintValidatorContext context) {
        try {
            languageCodeQueryService.getLanguageCode(languageCode);
            return true;
        } catch (LanguageCodeNotFoundException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
            return false;
        }
    }

}

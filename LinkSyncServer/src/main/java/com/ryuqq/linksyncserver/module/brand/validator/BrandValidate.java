package com.ryuqq.linksyncserver.module.brand.validator;


import com.ryuqq.linksyncserver.module.site.validator.LanguageCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LanguageCodeValidator.class)
@Documented
public @interface BrandValidate {

    String message() default "InValid Brand";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

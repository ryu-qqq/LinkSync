package com.ryuqq.linksyncserver.module.site.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LanguageCodeValidator.class)
@Documented
public @interface LanguageCodeValidate {
    String message() default "Not Supported Language";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

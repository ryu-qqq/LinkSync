package com.ryuqq.linksyncserver.module.brand.dto.command;

import com.ryuqq.linksyncserver.module.brand.validator.BrandValidate;
import com.ryuqq.linksyncserver.module.site.validator.LanguageCodeValidate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@BrandValidate
public class CreateBrandRequest {

    @NotBlank(message = "Brand name cannot be blank")
    @Size(max = 50, message = "Brand name cannot exceed 50 characters")
    private String brandName;

    @LanguageCodeValidate
    private String languageCode;

}

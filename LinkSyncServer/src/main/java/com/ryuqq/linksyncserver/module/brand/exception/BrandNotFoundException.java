package com.ryuqq.linksyncserver.module.brand.exception;

import com.ryuqq.linksyncserver.module.exception.NotFoundException;
import org.springframework.http.HttpStatus;

import static com.ryuqq.linksyncserver.module.brand.exception.BrandErrorCode.BRAND_NOT_FOUND;


public class BrandNotFoundException extends NotFoundException {

    public BrandNotFoundException(String brandName, String languageCode) {
        super(BRAND_NOT_FOUND, HttpStatus.NOT_FOUND, String.format("BrandName: %s, LanguageCode: %s", brandName, languageCode));
    }

    public BrandNotFoundException(long brandId) {
        super(BRAND_NOT_FOUND, HttpStatus.NOT_FOUND, String.format("BrandId: %d", brandId));
    }

}

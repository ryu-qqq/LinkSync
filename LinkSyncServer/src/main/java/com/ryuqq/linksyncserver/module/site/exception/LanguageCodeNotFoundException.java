package com.ryuqq.linksyncserver.module.site.exception;

import com.ryuqq.linksyncserver.module.exception.NotFoundException;
import org.springframework.http.HttpStatus;

import static com.ryuqq.linksyncserver.module.site.exception.SiteErrorCode.LANGUAGE_CODE_NOT_FOUND;

public class LanguageCodeNotFoundException extends NotFoundException {

    public LanguageCodeNotFoundException(String languageCode) {
        super(LANGUAGE_CODE_NOT_FOUND, HttpStatus.NOT_FOUND, languageCode);
    }

}

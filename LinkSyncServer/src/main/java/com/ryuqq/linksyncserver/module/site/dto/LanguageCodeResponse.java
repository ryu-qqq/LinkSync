package com.ryuqq.linksyncserver.module.site.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LanguageCodeResponse {

    private String languageId;
    private String languageName;

    @QueryProjection
    public LanguageCodeResponse(String languageId, String languageName) {
        this.languageId = languageId;
        this.languageName = languageName;
    }
}

package com.ryuqq.linksyncserver.module.site.repository;

import com.ryuqq.linksyncserver.module.site.dto.LanguageCodeResponse;

import java.util.Optional;

public interface LanguageQueryRepository {

    Optional<LanguageCodeResponse> fetchLanguageCode(String languageCode);

}

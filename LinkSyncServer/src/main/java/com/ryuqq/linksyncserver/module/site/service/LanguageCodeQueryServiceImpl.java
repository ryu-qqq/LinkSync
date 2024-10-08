package com.ryuqq.linksyncserver.module.site.service;


import com.ryuqq.linksyncserver.module.site.dto.LanguageCodeResponse;
import com.ryuqq.linksyncserver.module.site.exception.LanguageCodeNotFoundException;
import com.ryuqq.linksyncserver.module.site.repository.LanguageQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class LanguageCodeQueryServiceImpl implements LanguageCodeQueryService {

    private final LanguageQueryRepository languageQueryRepository;

    @Override
    public LanguageCodeResponse getLanguageCode(String languageCode){
        return languageQueryRepository.fetchLanguageCode(languageCode)
                .orElseThrow(() -> new LanguageCodeNotFoundException(languageCode));
    }

}

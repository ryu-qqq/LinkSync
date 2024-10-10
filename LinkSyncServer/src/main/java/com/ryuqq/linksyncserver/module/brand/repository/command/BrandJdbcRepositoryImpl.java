package com.ryuqq.linksyncserver.module.brand.repository.command;

import com.ryuqq.linksyncserver.module.brand.entity.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Set;

@RequiredArgsConstructor
@Repository
public class BrandJdbcRepositoryImpl implements BrandJdbcRepository{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void saveAll(Set<Brand> brands) {
        String sql = "INSERT INTO BRAND (BRAND_NAME, LANGUAGE_CODE) VALUES (:brandName, :languageCode)";

        SqlParameterSource[] batchParams = brands.stream()
                .map(brand -> new MapSqlParameterSource()
                        .addValue("brandName", brand.getBrandName())
                        .addValue("languageCode", brand.getLanguageCode()))
                .toArray(SqlParameterSource[]::new);

        namedParameterJdbcTemplate.batchUpdate(sql, batchParams);
    }
}

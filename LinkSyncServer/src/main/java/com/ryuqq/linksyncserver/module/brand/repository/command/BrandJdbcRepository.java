package com.ryuqq.linksyncserver.module.brand.repository.command;

import com.ryuqq.linksyncserver.module.brand.entity.Brand;

import java.util.Set;

public interface BrandJdbcRepository {
    void saveAll(Set<Brand> brands);
}

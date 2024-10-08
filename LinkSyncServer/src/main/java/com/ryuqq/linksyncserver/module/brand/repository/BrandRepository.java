package com.ryuqq.linksyncserver.module.brand.repository;

import com.ryuqq.linksyncserver.module.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}

package com.ryuqq.linksyncserver.module.category.repository;

import com.ryuqq.linksyncserver.module.category.entity.CategoryMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryMappingRepository extends JpaRepository<CategoryMapping, Long> {
}

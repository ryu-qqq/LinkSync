package com.ryuqq.linksyncserver.module.category.repository;

import com.ryuqq.linksyncserver.module.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

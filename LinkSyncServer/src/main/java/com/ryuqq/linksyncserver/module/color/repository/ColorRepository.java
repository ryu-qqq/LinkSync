package com.ryuqq.linksyncserver.module.color.repository;

import com.ryuqq.linksyncserver.module.color.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
}

package com.ryuqq.linksyncserver.module.category.entity;

import com.ryuqq.linksyncserver.module.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "CATEGORY_MAPPING")
@Entity
public class CategoryMapping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXTERNAL_CATEGORY_ID")
    private long id;

    @Column(name = "SITE_ID")
    private long siteId;

    @Column(name = "CATEGORY_ID")
    private long categoryId;

}

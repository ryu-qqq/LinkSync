package com.ryuqq.linksyncserver.module.category.entity;

import com.ryuqq.linksyncserver.module.category.enums.CategoryType;
import com.ryuqq.linksyncserver.module.category.enums.TargetGroup;
import com.ryuqq.linksyncserver.module.generic.entity.BaseEntity;
import com.ryuqq.linksyncserver.module.generic.enums.Yn;
import com.ryuqq.linksyncserver.module.site.LanguageCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "CATEGORY")
@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private long id;

    @Column(name = "CATEGORY_NAME", length = 50, nullable = false)
    private String categoryName;

    @Column(name = "LANGUAGE_CODE", length = 5, nullable = false)
    private String languageCode;

    @Column(name = "DEPTH", nullable = false)
    private int depth;

    @Column(name = "PATH", length = 255, nullable = false)
    private String path;

    @Column(name = "PARENT_CATEGORY_ID", nullable = true)
    private long parentCategoryId;

    @Column(name = "TARGET_GROUP", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private TargetGroup targetGroup;

    @Column(name = "CATEGORY_TYPE", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;


}


package com.ryuqq.linksyncserver.module.brand.entity;

import com.ryuqq.linksyncserver.module.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "BRAND")
@Entity
public class Brand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRAND_ID")
    private long id;

    @Column(name = "BRAND_NAME", nullable = false, length = 50)
    private String brandName;

    @Column(name = "LANGUAGE_CODE", length = 5, nullable = false)
    private String languageCode;

}

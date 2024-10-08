package com.ryuqq.linksyncserver.module.brand.entity;

import com.ryuqq.linksyncserver.module.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "BRAND_MAPPING")
@Entity
public class BrandMapping  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXTERNAL_BRAND_ID")
    private long id;

    @Column(name = "SITE_ID")
    private long siteId;

    @Column(name = "BRAND_ID")
    private long brandId;

}

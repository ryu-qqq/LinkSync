package com.ryuqq.linksyncserver.module.site;

import com.ryuqq.linksyncserver.module.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "SITE")
@Getter
@Entity
public class Site extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SITE_ID")
    private long id;

    @Column(name = "SITE_NAME", nullable = false, length = 20)
    private String siteName;

    @Column(name = "BASE_URL", nullable = false, length = 255)
    private String siteUrl;

}


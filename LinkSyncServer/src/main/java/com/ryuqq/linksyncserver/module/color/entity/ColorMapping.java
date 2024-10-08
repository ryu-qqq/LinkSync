package com.ryuqq.linksyncserver.module.color.entity;

import com.ryuqq.linksyncserver.module.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "COLOR")
@Getter
@Entity
public class ColorMapping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXTERNAL_COLOR_ID")
    private long id;

    @Column(name = "COLOR_ID")
    private String colorId;

    @Column(name = "SITE_ID")
    private long siteId;

}

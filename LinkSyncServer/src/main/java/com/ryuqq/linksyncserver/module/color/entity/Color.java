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
public class Color extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COLOR_ID")
    private long id;

    @Column(name = "COLOR_NAME", nullable = false, length = 50)
    private String colorName;


}

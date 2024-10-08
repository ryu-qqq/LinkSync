package com.ryuqq.linksyncserver.module.site;

import com.ryuqq.linksyncserver.module.generic.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "LANGUAGE_CODE")
@Getter
@Entity
public class LanguageCode extends BaseEntity {

    @Id
    @Column(name = "LANGUAGE_CODE")
    private String id;

    @Column(name = "LANGUAGE_NAME", nullable = false, length = 50)
    private String languageName;

}

package com.ryuqq.linksyncserver.module.generic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ryuqq.linksyncserver.module.generic.enums.Yn;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Setter
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "DELETE_YN", length = 1, nullable = false)
    private Yn deleteYn;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(updatable = false, name = "INSERT_DATE", nullable = false)
    private LocalDateTime insertDate;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @LastModifiedDate
    @Column(name = "UPDATE_DATE", nullable = false)
    private LocalDateTime updateDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "INSERT_OPERATOR", length = 50, nullable = false)
    private String insertOperator;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "UPDATE_OPERATOR", length = 50, nullable = false)
    private String updateOperator;

    @PrePersist
    public void before() {
        LocalDateTime now = LocalDateTime.now();
        this.insertDate = now;
        this.updateDate = now;
        this.insertOperator = MDC.get("user") ==null ? "Anonymous" : MDC.get("user");
        this.updateOperator = MDC.get("user") ==null ? "Anonymous" : MDC.get("user");
        this.deleteYn = Yn.N;
    }

    @PreUpdate
    public void always() {
        this.updateDate = LocalDateTime.now();
        this.updateOperator = MDC.get("user") == null ? "Anonymous" : MDC.get("user");
    }


}


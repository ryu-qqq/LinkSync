package com.ryuqq.linksyncserver.module.generic.filter;

public interface CursorFilter {
    Long getLastDomainId();
    void setLastDomainId(Long lastDomainId);
}

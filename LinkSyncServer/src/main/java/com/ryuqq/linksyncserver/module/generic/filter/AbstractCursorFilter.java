package com.ryuqq.linksyncserver.module.generic.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractCursorFilter implements CursorFilter {
    private Long lastDomainId;
}

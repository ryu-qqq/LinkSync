package com.ryuqq.linksyncserver.module.category.enums;

import com.ryuqq.linksyncserver.module.generic.EnumType;

public enum CategoryType implements EnumType {
    NONE,
    CLOTHING,
    SHOSE,
    BAG,
    ACC;

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getDescription() {
        return name();
    }
}

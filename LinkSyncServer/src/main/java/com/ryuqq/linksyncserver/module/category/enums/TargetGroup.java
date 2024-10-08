package com.ryuqq.linksyncserver.module.category.enums;

import com.ryuqq.linksyncserver.module.generic.EnumType;

public enum TargetGroup implements EnumType {
    MALE,
    FEMALE,
    KIDS,
    LIFE;

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getDescription() {
        return name();
    }
}

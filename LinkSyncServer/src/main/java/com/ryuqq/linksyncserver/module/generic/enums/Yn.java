package com.ryuqq.linksyncserver.module.generic.enums;

import com.ryuqq.linksyncserver.module.generic.EnumType;

public enum Yn implements EnumType {
    Y,
    N;

    public boolean isYes(){
        return equals(Y);
    }

    public boolean isNo(){
        return equals(N);
    }


    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return this.name();
    }
}


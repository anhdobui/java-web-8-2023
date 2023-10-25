package com.laptrinhjavaweb.enumDefine;

import com.laptrinhjavaweb.constant.TypeConstant;

public enum TypeEnum {
    TANG_TRET(TypeConstant.TANG_TRET),
    NGUYEN_CAN(TypeConstant.NGUYEN_CAN),
    NOI_THAT(TypeConstant.NOI_THAT);

    private final String name;

    TypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

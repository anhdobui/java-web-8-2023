package com.laptrinhjavaweb.enumDefine;

import com.laptrinhjavaweb.constant.TransactionConstant;

public enum TransactionTypeEnum {
    QUA_TRINH_CSKH(TransactionConstant.QUA_TRINH_CSKH),
    DAN_DI_AN(TransactionConstant.DAN_DI_AN);

    private final String name;

    TransactionTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

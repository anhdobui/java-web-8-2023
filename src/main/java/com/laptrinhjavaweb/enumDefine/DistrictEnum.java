package com.laptrinhjavaweb.enumDefine;

import com.laptrinhjavaweb.constant.DistrictConstant;

public enum DistrictEnum {
    QUAN_1(DistrictConstant.QUAN_1),
    QUAN_2(DistrictConstant.QUAN_2),
    QUAN_3(DistrictConstant.QUAN_3),
    QUAN_4(DistrictConstant.QUAN_4);

    private final String name;

    DistrictEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

package com.lite.hris.office;

import lombok.Getter;

@Getter
public enum OfficeType {
    HEAD_QUARTER("HQ", "Head Quarter"),
    BRANCH("BR","Branch"),
    WAREHOUSE("WH","Warehouse"),
    PLANT("PL","Plant");

    private final String code;
    private final String name;

    OfficeType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}

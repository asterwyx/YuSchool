package com.yuschool.constants.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 返回值的枚举
 */
public enum RetCode {
    SUCCESS(0),
    IO_ERROR(-1),
    WRONG_OP(-2),
    FAIL_OP(-3),
    DUP_VALUE(-4),
    MATCH_ERROR(-5);

    private final int value;

    RetCode(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return this.value;
    }
}

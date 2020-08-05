package com.kairong.util;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.util
 * @date:2020-08-04
 */
public enum GenderEnum {
    /**
     * 0未知 1男 2女
     */
    UNKNOWN(0), MAN(1),WOMAN(2);

    private int code;

    GenderEnum(int code) {
        this.code = code;
    }
}

package com.cs.springbootvue.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: ResultCodeEnum
 * Package: com.cs.springbootvue.utils
 * Description:
 *
 * @Author zhuwen
 * @Create 2023/5/21 11:46
 * @Version 1.0
 */

@NoArgsConstructor
public enum ResultCodeEnum {
    SUCCESS(1,"成功"),
    FAIL(201, "失败");
    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

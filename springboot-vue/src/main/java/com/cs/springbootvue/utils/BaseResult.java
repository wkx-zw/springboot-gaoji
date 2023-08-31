package com.cs.springbootvue.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * 结果集类
 */
@Data
@AllArgsConstructor
public class BaseResult {

    private int code;  //0：成功, 1:有问题

    private String msg; //返回的信息

    private String token;

    private Object obj;

    public BaseResult(int code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public static BaseResult getBaseResult(int code, String msg, String token, Object obj) {
        return new BaseResult(code, msg, token,  obj);
    }

    public static BaseResult getBaseResult(int code, String msg, Object obj) {

        return new BaseResult(code, msg, obj);
    }

}

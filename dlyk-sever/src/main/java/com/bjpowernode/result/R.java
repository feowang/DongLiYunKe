package com.bjpowernode.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * web层/controller返回结果的封装类
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class R {

    //返回的结果状态码（比如：200表示成功，400表示参数错误，500表示服务器错误）
    private int code;

    //返回的结果提示信息
    private String msg;

    //返回的结果数据 （int、String、List、Map、Object、Student等）
    private Object data;

    public static R OK() {
        return R.builder().code(200).msg("成功").build();
    }

    public static R OK(Object data) {
        return R.builder().code(200).msg("成功").data(data).build();
    }

    public static R FAIL() {
        return R.builder().code(500).msg("失败").build();
    }

    public static R FAIL(String msg) {
        return R.builder().code(500).msg(msg).build();
    }

    public static R FAIL(CodeEnum codeEnum) {
        return R.builder().code(codeEnum.getCode()).msg(codeEnum.getMsg()).build();
    }
}

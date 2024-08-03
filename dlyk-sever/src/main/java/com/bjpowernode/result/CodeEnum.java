package com.bjpowernode.result;

import lombok.*;

/**
 * 枚举类
 */
@NoArgsConstructor
@AllArgsConstructor
public enum CodeEnum {
    //定义枚举值，每个枚举值用逗号分隔，最后一个枚举值是分号结束
    OK(200, "成功"),

    FAIL(500, "失败"),

    PARAM_ERROR(400, "参数错误"),

    NO_AUTH(401, "没有权限"),

    NO_LOGIN(403, "没有登录"),

    NO_FOUND(404, "没有找到"),

    SERVER_ERROR(500, "服务器错误"),

    LOGIN_ERROR(901, "用户名或密码错误"),

    LOGIN_TOKEN_EMPTY(902, "登录Token为空"),

    LOGIN_TOKEN_ILLEGAL(903, "登录Token不合法"),

    LOGIN_TOKEN_ERR(903, "登录Token错误"),
    ;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;
}

package com.bjpowernode.constant;

import cn.hutool.core.util.StrUtil;

/**
 *常量类
 */

public class Constant {
    public static String JWT_SECRET = StrUtil.EMPTY; //JWT密钥

    public static final String JWT_PAYLOAD_KEY = "loginUser"; //JWT载荷中的用户信息键名

    //redis的key的命名规范： 项目名称:模块名称:功能名称:唯一参数名
    public static final String REDIS_JWT_KEY = "dlyk:user:login:";  //Redis中JWT的键名
}

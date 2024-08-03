package com.bjpowernode.handler;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import com.bjpowernode.constant.Constant;
import com.bjpowernode.entity.TUser;
import com.bjpowernode.manager.RedisManager;
import com.bjpowernode.result.R;
import com.bjpowernode.until.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 *
 */
@Component
public class DlykAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private RedisManager redisManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("登录成功......");
        TUser tUser = (TUser) authentication.getPrincipal();
        String userJSON = JSONUtil.toJsonStr(tUser);
        //生成jwt token并返回给客户端
        String token = JWTUtil.createToken(Map.of(Constant.JWT_PAYLOAD_KEY,userJSON), Constant.JWT_SECRET.getBytes());

        //jwt token存入redis
        redisManager.setValue(Constant.REDIS_JWT_KEY + tUser.getId(),token);

        //返回给前端token
        ResponseUtils.writeJson(response, R.OK(token));
    }
}

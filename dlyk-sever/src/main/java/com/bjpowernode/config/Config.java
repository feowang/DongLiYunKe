package com.bjpowernode.config;

import com.bjpowernode.handler.DlykAuthenticationFailureHandler;
import com.bjpowernode.handler.DlykAuthenticationSuccessHandler;
import jakarta.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 *
 */
@MapperScan(value = "com.bjpowernode.mapper")
@Configuration
public class Config {

    @Resource
    private DlykAuthenticationSuccessHandler dlykAuthenticationSuccessHandler;
    @Resource
    private DlykAuthenticationFailureHandler dlykAuthenticationFailureHandler;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));//允许所有来源
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));//允许所有头部
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));//允许所有方法

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);//注册全局跨域配置
        return urlBasedCorsConfigurationSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,CorsConfigurationSource corsConfigurationSource ) throws Exception {
        // 禁用CSRF防护
        return httpSecurity
                .formLogin((formLogin) ->{
                    formLogin.loginPage("/api/login") //登录的处理地址，也就是登录提交到该地址上
                             .usernameParameter("loginAct") //登录页面的用户名参数名
                             .passwordParameter("loginPwd")//登录页面的密码参数名
                             .successHandler(dlykAuthenticationSuccessHandler)//登录成功处理器
                            .failureHandler(dlykAuthenticationFailureHandler);//登录失败处理器
                })
                .authorizeHttpRequests((authorizeHttpRequests) ->{
                    authorizeHttpRequests.anyRequest().authenticated(); // 所有请求都需要认证才能访问
                })
                .csrf(AbstractHttpConfigurer::disable) // 禁用CSRF防护，因为前后端分离，前端拿不到csrf参数

                .cors((cors) ->{
                    cors.configurationSource(corsConfigurationSource); // 允许跨域请求
                })

                .build();
    }
}

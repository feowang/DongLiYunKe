package com.bjpowernode.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.bjpowernode.entity.TUser;
import com.bjpowernode.mapper.TUserMapper;
import com.bjpowernode.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private TUserMapper tUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TUser tUser = tUserMapper.selectByLoginAct(username);

        if (ObjectUtil.isEmpty(tUser)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return tUser;
    }
}

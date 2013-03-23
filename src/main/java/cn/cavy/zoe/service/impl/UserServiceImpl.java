package cn.cavy.zoe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cavy.zoe.mapper.UserMapper;
import cn.cavy.zoe.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

}

package com.imooc.service.impl;

import com.imooc.mapper.UsersMapper;
import com.imooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public boolean deleteStu(String username) {
        return false;
    }
}

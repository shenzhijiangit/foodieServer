package com.imooc.service;


import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;

public interface UserService {

    /**
     * 判断用户名是否存在
     * @param username 需要判断用户名的参数
     * @return 返回判断用户名是否存在的值
     */
    public boolean queryUsernameIsExist(String username);

    public Users createUser(UserBO userBo);
}

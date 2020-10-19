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

    /**
     * 创建用户
     * @param userBo 创建用户所需参数
     * @return 返回创建后的用户实体
     */
    public Users createUser(UserBO userBo);

    /**
     * 登录，检索用户名和密码是否匹配
     * @param username 用户名
     * @param password 用户密码
     * @return 返回创建后的用户
     */
    public Users queryUserForLogin(String username,String password);
}

package com.imooc.service.center;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;

public interface CenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId 用户ID
     * @return 返回查询过后的用户信息
     */
     Users queryUserInfo(String userId);

    /**
     * 修改用户信息
     * @param userId 用户ID
     * @param centerUserBO 返回用户的数据库实体
     */
     Users updateUserInfo(String userId, CenterUserBO centerUserBO);

    /**
     * 用户头像更新
     * @param userId 用户ID
     * @param faceUrl 更新的头像URL
     * @return 返回用户信息
     */
     Users updateUserFace(String userId, String faceUrl);
}

package com.imooc.service.impl;

import com.imooc.enums.Sex;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private UsersMapper usersMapper;

    private Sid sid;

    @Autowired
    public UserServiceImpl(UsersMapper usersMapper, Sid sid) {
        this.usersMapper = usersMapper;
        this.sid = sid;
    }

    //创建用户时的默认头像，图片地址百度上随机找的
    private static final String USER_FACE="https://timgsa.baidu.com/" +
            "timg?image&quality=80&size=b9999_10000&sec=1603007910467&di" +
            "=8414693d0ab2a243dabd5336812c7a91" +
            "&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang." +
            "com%2Fuploads%2Fitem%2F201509%2F16%2F20150916235818_HVAk2.jpeg";

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username",username);
        Users result = usersMapper.selectOneByExample(userExample);
        return result!=null;
    }

    /**
     * 创建用户
     * @param userBo 创建用户的所需数据
     * @return 返回创建用户
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Users createUser(UserBO userBo) {
        Users user =new Users();
        user.setId(sid.nextShort());
        user.setUsername(userBo.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //默认用户昵称同用户名
        user.setNickname(user.getUsername());
        user.setFace(USER_FACE);
        //创建生日默认为我1997-08-20本人生日
        //1. 这是通过DateFormat 字符串转日期格式实现，另外 new ParsePosition(0) 用来表明解析开始位
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1997-08-20",
                new ParsePosition(0)));
        //2. 这是通过工具类所实现
        //user.setBirthday(DateUtil.stringToDate("1997-08-20"));
        //默认性别为保密
        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        usersMapper.insert(user);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username",username);
        userCriteria.andEqualTo("password",password);

        return usersMapper.selectOneByExample(userExample);
    }
}

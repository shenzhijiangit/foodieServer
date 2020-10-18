package com.imooc.controller;

import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.IMOOCJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("passport")
public class PassportController {

    private UserService userService;

    @Autowired
    public PassportController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username){
        //1. 判断用户名不能为空
        if(StringUtils.isBlank(username)){
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        // 2. 查找注册的用户是否存在
        if(!userService.queryUsernameIsExist(username)){
            return IMOOCJSONResult.ok();
        }else return IMOOCJSONResult.errorMsg("用户名已经存在");
    }

    @PostMapping("/regist")
    public IMOOCJSONResult register(@RequestBody UserBO userBO){

        // 1.判断用户名和密码必须不为空
        if(StringUtils.isBlank(userBO.getUsername())||
                StringUtils.isBlank(userBO.getPassword())||
                StringUtils.isBlank(userBO.getConfirmPassword())){
            return IMOOCJSONResult.errorMsg("用户名和密码不能为空");
        }
        // 2.查询用户名是否存在
        if(userService.queryUsernameIsExist(userBO.getUsername())){
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        // 3.密码长度不能少于六位
        if(userBO.getPassword().length()<6){
            return IMOOCJSONResult.errorMsg("密码长度不能少于六位");
        }
        // 4.判断两次密码是否一致
        if(!userBO.getPassword().equals(userBO.getConfirmPassword())){
            return IMOOCJSONResult.errorMsg("密码前后不一致");
        }
        // 5.实现注册
        userService.createUser(userBO);

        return IMOOCJSONResult.ok();
    }
}

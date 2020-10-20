package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册登录",tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    private UserService userService;

    @Autowired
    public PassportController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
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

    @ApiOperation(value = "用户注册",notes = "用户注册",httpMethod = "POST")
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

    @ApiOperation(value = "用户登录",notes = "用户登录",httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO,
                                 HttpServletRequest request,
                                 HttpServletResponse reponse)throws Exception{

        // 1.判断用户名和密码必须不为空
        if(StringUtils.isBlank(userBO.getUsername())||
                StringUtils.isBlank(userBO.getPassword())){
            return IMOOCJSONResult.errorMsg("用户名和密码不能为空");
        }

        // 5.实现登录
        Users userResult =userService.queryUserForLogin(userBO.getUsername(),
                MD5Utils.getMD5Str(userBO.getPassword()));
        if(userResult == null){
            return IMOOCJSONResult.errorMsg("用户名或密码不正确");
        }

        Users userResult2=setNullProprty(userResult);
        CookieUtils.setCookie(request,reponse,"user",
                JsonUtils.objectToJson(userResult2),true);

        return IMOOCJSONResult.ok(userResult2);
    }

    @ApiOperation(value = "用户退出登录",notes = "用户退出登录",httpMethod = "POST")
    @PostMapping("/logout")
    public IMOOCJSONResult logout(@RequestParam String userId,
                                  HttpServletRequest request,
                                  HttpServletResponse response){
        //清楚用户的相关信息的Cookie
        CookieUtils.deleteCookie(request,response,"user");
        return IMOOCJSONResult.ok();
    }


    private Users setNullProprty(Users userResult){
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

}

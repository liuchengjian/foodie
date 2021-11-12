package com.liucj.controller;

import com.liucj.pojo.bo.UserBO;
import com.liucj.common.utils.CookieUtils;
import com.liucj.common.utils.JsonUtils;
import com.liucj.common.utils.MD5Utils;
import com.liucj.common.utils.ServerJSONResult;

import com.liucj.pojo.Users;
import com.liucj.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Controller
@Api(value = "注册登录", tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public ServerJSONResult usernameIsExist(@RequestParam String username){
        //判断用户不为空
        if(StringUtils.isBlank(username)){
            return ServerJSONResult.errorMsg("用户名不为空");
        }
        //查找注册用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if(isExist){
            return ServerJSONResult.errorMsg("用户名存在");
        }
        return ServerJSONResult.ok();
    }
    @ApiOperation(value = "用户名注册",notes = "用户名注册",httpMethod = "POST")
    @PostMapping("/regist")
    public ServerJSONResult register(@RequestBody UserBO userBO){

        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        // 0. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPwd)) {
            return ServerJSONResult.errorMsg("用户名或密码不能为空");
        }

        // 1. 查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return ServerJSONResult.errorMsg("用户名已经存在");
        }

        // 2. 密码长度不能少于6位
        if (password.length() < 6) {
            return ServerJSONResult.errorMsg("密码长度不能少于6");
        }

        // 3. 判断两次密码是否一致
        if (!password.equals(confirmPwd)) {
            return ServerJSONResult.errorMsg("两次密码输入不一致");
        }

        // 4. 实现注册
        Users userResult = userService.createUser(userBO);

//        userResult = setNullProperty(userResult);
//
//        CookieUtils.setCookie(request, response, "user",
//                JsonUtils.objectToJson(userResult), true);

        // TODO 生成用户token，存入redis会话
        // TODO 同步购物车数据

        return ServerJSONResult.ok();
    }

    @ApiOperation(value = "用户名登录",notes = "用户名注册",httpMethod = "POST")
    @PostMapping("/login")
    public ServerJSONResult login(@RequestBody UserBO userBO, HttpServletRequest request,
                                  HttpServletResponse response){

        String username = userBO.getUsername();
        String password = userBO.getPassword();

        // 0. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)) {
            return ServerJSONResult.errorMsg("用户名或密码不能为空");
        }

        // 4. 实现注册
        Users userResult = null;
        try {
            userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(userResult==null){
            return ServerJSONResult.errorMsg("用户名或密码不正确");
        }
        userResult = setNullProperty(userResult);
        //设置Cookie
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);

        // TODO 生成用户token，存入redis会话
        // TODO 同步购物车数据

        return ServerJSONResult.ok(userResult);
    }

    /**
     * 设置某些参数为空
     * @param userResult
     * @return
     */
    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public ServerJSONResult logout(@RequestParam String userId,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {

        // 清除用户的相关信息的cookie
        CookieUtils.deleteCookie(request, response, "user");

        // TODO 用户退出登录，需要清空购物车
        // TODO 分布式会话中需要清除用户数据

        return ServerJSONResult.ok();
    }
}

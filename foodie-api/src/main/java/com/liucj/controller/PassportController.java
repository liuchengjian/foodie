package com.liucj.controller;

import com.liucj.common.utils.ServerJSONResult;
import com.liucj.service.impl.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;
    @GetMapping("/usernameIsExist")
    public ServerJSONResult usernameIsExist(@RequestParam String username){
        //判断用户不为空
        if(StringUtils.isBlank(username)){
            return ServerJSONResult.errorMsg("用户名不为空");
        }
        //查找注册用户名是否存在
        boolean isExist = userService.queryUserNameIsExist(username);
        if(isExist){
            return ServerJSONResult.errorMsg("用户名存在");
        }
        return ServerJSONResult.ok();
    }

}

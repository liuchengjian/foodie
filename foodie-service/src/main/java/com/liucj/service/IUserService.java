package com.liucj.service;

import com.liucj.common.ServerResponse;
import com.liucj.vo.UserVo;

public interface IUserService {
    /**
     * 判断用户名是否存在
     */
     boolean queryUserNameIsExist(String username);

    //注册
    ServerResponse<String> register(UserVo userVo);
}

package com.liucj.service;

import com.liucj.pojo.bo.UserBO;
import com.liucj.pojo.Users;

public interface UserService {
    /**
     * 判断用户名是否存在
     */
     public boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userBo
     * @return
     */
    public Users createUser(UserBO userBo);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public Users queryUserForLogin(String username,String password);
}

package com.liucj.service.impl;

import com.liucj.common.ServerResponse;
import com.liucj.mapper.UserAddressMapper;
import com.liucj.mapper.UsersMapper;
import com.liucj.pojo.Users;
import com.liucj.service.IUserService;
import com.liucj.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户实现类
 */
@Service("IUserService")
public class UserService implements IUserService {
//    @Autowired
//    public UsersMapper usersMapper;

    @Override
    public boolean queryUserNameIsExist(String username) {
        return false;
    }

    @Override
    public ServerResponse<String> register(UserVo userVo) {
        Users users = new Users();
        users.setUsername(userVo.getUsername());
        users.setPassword(userVo.getPassword());
        users.setSex(2);
        users.setNickname(userVo.getUsername());
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());
//        usersMapper.insert(users);
        return null;
    }
}

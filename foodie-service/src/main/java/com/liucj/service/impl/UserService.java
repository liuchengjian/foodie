package com.liucj.service.impl;

import com.liucj.common.ServerResponse;
import com.liucj.mapper.UserAddressMapper;
import com.liucj.mapper.UsersMapper;
import com.liucj.pojo.Users;
import com.liucj.service.IUserService;
import com.liucj.vo.UserVo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * 用户实现类
 */
@Service("IUserService")
public class UserService implements IUserService {
    @Autowired
    private UsersMapper usersMapper;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUserNameIsExist(String username) {
        Example userExample = new Example(User.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", username);
        Users result = usersMapper.selectOneByExample(userExample);
        return result == null ? false : true;
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

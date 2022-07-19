package com.atstuding.ems.service;

import com.atstuding.ems.bean.User;
import com.atstuding.ems.bean.UserExample;
import com.atstuding.ems.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassNmae UserService
 * @Description TODO
 * @Author twj280
 * @Date 2022/7/9 23:34
 * @Version 1.0
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getUser(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUnameEqualTo(user.getUname()).andPwdEqualTo(user.getPwd());
        List<User> users = userMapper.selectByExample(userExample);
        return users;


    }
}

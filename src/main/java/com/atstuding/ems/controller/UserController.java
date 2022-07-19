package com.atstuding.ems.controller;

import com.atstuding.ems.bean.User;
import com.atstuding.ems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassNmae UserController
 * @Description TODO
 * @Author twj280
 * @Date 2022/7/8 9:51
 * @Version 1.0
 **/
@Controller
public class UserController {
    @Autowired
    public UserService userService;
/**
 * @Author: twj280
 * @Description: 用户登录验证
 * @date: 18:40 2022/7/11
 * @Param: [uname, pwd, model]
 * @return java.lang.String
 **/
    @RequestMapping(value = "/checkUser",method = RequestMethod.POST)
    @ResponseBody
    public User checkUser(String uname, String pwd){
        System.out.println("uname:"+uname + "pwd:" +pwd);
        List<User> userList = userService.getUser(new User(null, uname, pwd));
        if (userList.size() > 0){
            User currUser= userList.get(0);
            return currUser;
        }
        return null;
    }

}

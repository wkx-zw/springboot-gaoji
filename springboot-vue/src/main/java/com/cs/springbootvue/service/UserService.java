package com.cs.springbootvue.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.cs.springbootvue.entity.User;
import com.cs.springbootvue.exception.BaseException;
import com.cs.springbootvue.mapper.UserMapper;
import com.cs.springbootvue.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yantao
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //ajax

    public User login(String userName, String password) {
        //调用根据用户名查询的方法
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new BaseException("用户名不存在");
        }
       /* if (!MD5Utils.getMD5Str(password).equals(user.getPassword())) {
            throw new BaseException("密码错误");
        }*/
        if (!password.equals(user.getPassword())) {
            System.out.println(password);
            System.out.println(user.getPassword());
            throw new BaseException("密码错误");
        }
        return user;
    }

    public User getUserByName(String userName){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    public User getUserById(Long userId){
        User user = userMapper.selectById(userId);
        return user;
    }
}

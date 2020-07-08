package com.shareknowledge.service;

import com.shareknowledge.bean.User;
import com.shareknowledge.mapper.AccountMapper;
import com.shareknowledge.mapper.AuthorityMapper;
import com.shareknowledge.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    /**
     * 传入user的属性插入并且返回插入成功的user
     * @param userName 用户名
     * @param gender 性别
     * @param age 年龄
     * @param detail 详细描述
     * @param headFilePath 头像路径
     * @return 已经插入并且回填自增主键的user对象
     */
    public User addUser(String userName, String gender, int age, String detail, String headFilePath) {
        User user = new User();
        user.setUserName(userName);
        user.setGender(gender);
        user.setDetail(detail);
        user.setAge(age);
        user.setHeadFilePath(headFilePath);
        userMapper.insert(user);
        return user;
    }

}

package com.yuschool.service.impl;

import com.yuschool.bean.User;
import com.yuschool.constants.enums.Operation;
import com.yuschool.constants.enums.RetCode;
import com.yuschool.mapper.AccountMapper;
import com.yuschool.mapper.AuthorityMapper;
import com.yuschool.mapper.UserMapper;
import com.yuschool.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public boolean addUser(User prepUser) {
        int status = userMapper.insert(prepUser);
        if (status <= 0) {
            logger.error("添加用户失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean checkExistence(String username) {
        User find = userMapper.selectByUsername(username);
        return find != null;
    }

    @Override
    public List<User> getAllFans(int userId) {
        return null;
    }

    @Override
    public List<User> getAllFollows(int userId) {
        return null;
    }

    @Override
    public RetCode updateFollow(int userId, int followingId, Operation operation) {
        return null;
    }

    @Override
    public boolean checkFollow(int userId, int toCheckId) {
        return false;
    }

}

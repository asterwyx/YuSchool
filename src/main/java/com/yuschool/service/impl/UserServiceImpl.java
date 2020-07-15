package com.yuschool.service.impl;

import com.yuschool.bean.User;
import com.yuschool.mapper.AccountMapper;
import com.yuschool.mapper.AuthorityMapper;
import com.yuschool.mapper.UserMapper;
import com.yuschool.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    private static final Logger logger = LoggerFactory.getLogger("UserServiceLogger");

    /**
     * 传入user的属性插入并且返回插入成功的user
     * @param prepUser 需要被插入获取主键和时间的用户，除去数据库中获取的信息之外，其它信息必须填完整
     * @return 已经插入并且回填自增主键的user对象
     */
    @Override
    public boolean addUser(User prepUser) {
        int status = userMapper.insert(prepUser);
        if (status <= 0) {
            logger.error("添加用户失败");
            return false;
        }
        return true;
    }

    /**
     * 检查用户名是否已经被注册
     * @param username 要检查的用户名
     * @return 是否已经存在
     */
    @Override
    public boolean checkExistence(String username) {
        User find = userMapper.selectByUsername(username);
        return find != null;
    }

}

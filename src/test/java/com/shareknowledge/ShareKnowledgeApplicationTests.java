package com.shareknowledge;

import com.shareknowledge.bean.User;
import com.shareknowledge.mapper.UserMapper;
import com.shareknowledge.utils.MyConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShareKnowledgeApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testUserMapper() {
        User user = new User();
        user.setAge(20);
        user.setUserName("asterwyx");
        user.setGender("男");
        user.setDetail("");
        user.setHeadFilePath(MyConstants.DEFAULT_USER_HEAD_PATH);
        userMapper.insert(user);
        System.out.println(user);
        user = userMapper.selectById(3);
        System.out.println(user);
    }
}

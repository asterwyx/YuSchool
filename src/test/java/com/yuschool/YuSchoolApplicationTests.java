package com.yuschool;

import com.yuschool.bean.Account;
import com.yuschool.bean.Course;
import com.yuschool.bean.FanRelation;
import com.yuschool.bean.User;
import com.yuschool.constants.DefaultValue;
import com.yuschool.mapper.AccountMapper;
import com.yuschool.mapper.CourseMapper;
import com.yuschool.mapper.FanMapper;
import com.yuschool.mapper.UserMapper;
import com.yuschool.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class YuSchoolApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AccountServiceImpl accountService;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    FanMapper fanMapper;

    @Test
    void contextLoads() {
        testSelectCourse();
        testUpdateCourse();
    }

    @Test
    void testUserMapperInsert() {
        User user = new User("asterwyx");
        user.setAge(20);
        user.setGender("男");
        user.setDetail("交个朋友吧!");
        assert userMapper.insert(user) > 0;
        System.out.println(user);
    }

    @Test
    void testUserMapperSelect() {
        User user = userMapper.selectById(1);
        System.out.println(user);
        user = userMapper.selectByUsername("asterwyx");
        System.out.println(user);
    }

    @Test
    void testUserMapperUpdate() {
        User user = new User("aster");
        user.setId(1);
        user.setAge(DefaultValue.AGE_NULL);
        user.setDetail(null);
        user.setGender(null);
        user.setHeadFilePath(null);
        assert userMapper.updateSelective(user) > 0;
        System.out.println(user);
    }

    @Test
    void testAddAccount() {
        User user = userMapper.selectById(1);
        System.out.println(user);
        boolean status = accountService.addNormalAccount(user, "123456");
        assert status;
        Account account = accountMapper.selectByUsername(user.getUsername());
        System.out.println(account);
    }

    @Test
    void testSelectAccount() {
        Account account = accountMapper.selectByUsername("cecil");
        System.out.println(account);
    }

    @Test
    void testInsertCourse() {
        Course course = new Course();
        course.setCourseName("操作系统原理");
        course.setCoverFilePath("/default.jpg");
        course.setIntroduction("这是操作系统原理");
        course.setStarNum(1);
        System.out.println(course);
        courseMapper.insert(course);
    }

    @Test
    void testSelectCourse() {
        Course course = courseMapper.selectById(1);
        System.out.println(course);
        List<Course> courses = courseMapper.selectAll();
        System.out.println(courses);
    }

    @Test
    void testUpdateCourse() {
        Course course = courseMapper.selectById(2);
        course.setStarNum(5);
        int rc = courseMapper.update(course);
        System.out.println("返回码: " + rc);
        System.out.println(course);
    }

    @Test
    void testFanRelationSelect() {
        List<FanRelation> fanRelations = fanMapper.selectByUserId(1);
        System.out.println(fanRelations);
    }
}

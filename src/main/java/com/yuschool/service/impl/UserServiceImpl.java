package com.yuschool.service.impl;

import com.yuschool.bean.FanRelation;
import com.yuschool.bean.FollowRelation;
import com.yuschool.bean.User;
import com.yuschool.constants.enums.Operation;
import com.yuschool.constants.enums.RetCode;
import com.yuschool.mapper.*;
import com.yuschool.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.yuschool.constants.enums.RetCode.*;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserMapper userMapper;
    private final FanMapper fanMapper;
    private final FollowMapper followMapper;
    private final UserCourseRelationMapper userCourseRelationMapper;

    public UserServiceImpl(UserMapper userMapper, FanMapper fanMapper, FollowMapper followMapper, UserCourseRelationMapper userCourseRelationMapper) {
        this.userMapper = userMapper;
        this.fanMapper = fanMapper;
        this.followMapper = followMapper;
        this.userCourseRelationMapper = userCourseRelationMapper;
    }

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
    public RetCode updateUserInfo(User prepUser) {
        int infNum = userMapper.updateSelective(prepUser);
        if (infNum <= 0) {
            return FAIL_OP;
        } else {
            return SUCCESS;
        }
    }

    @Override
    public boolean checkExistence(String username) {
        User find = userMapper.selectByUsername(username);
        return find != null;
    }

    @Override
    public List<User> getAllFans(int userId) {
        List<Integer> fanIds = fanMapper.selectAllFansByUserId(userId);
        return getUsersByIds(fanIds);
    }

    @Override
    public List<User> getAllFollows(int userId) {
        List<Integer> followIds = followMapper.selectAllFollowsByUserId(userId);
        return getUsersByIds(followIds);
    }

    @Override
    public RetCode updateFollow(int userId, int followingId, Operation operation) {
        RetCode rc = SUCCESS;
        switch (operation) {
            case OP_ADD:
            {
                if (checkFollow(userId, followingId)) {
                    rc = DUP_VALUE;
                    break;
                }
                FollowRelation followRelation = new FollowRelation();
                followRelation.setUserId(userId);
                followRelation.setFollowingUserId(followingId);
                int infNum = followMapper.insert(followRelation);
                if (infNum < 0) {
                    // 插入新纪录失败
                    logger.error("插入关注关系失败");
                    rc = FAIL_OP;
                    break;
                }
                FanRelation fanRelation = new FanRelation();
                fanRelation.setUserId(followingId);
                fanRelation.setFanId(userId);
                infNum = fanMapper.insert(fanRelation);
                if (infNum < 0) {
                    logger.error("插入粉丝关系失败");
                    followMapper.deleteBy2Id(userId, followingId);
                    rc = FAIL_OP;
                    break;
                }
                break;
            }
            case OP_DEL:
            {
                if (checkFollow(userId, followingId)) {
                    int infNum = followMapper.deleteBy2Id(userId, followingId);
                    if (infNum < 0) {
                        logger.error("删除关注关系失败");
                        rc = FAIL_OP;
                        break;
                    }
                    infNum = fanMapper.deleteBy2Id(followingId, userId);
                    if (infNum < 0) {
                        logger.error("删除粉丝关系失败");
                        FollowRelation followRelation = new FollowRelation();
                        followRelation.setUserId(userId);
                        followRelation.setFollowingUserId(followingId);
                        followMapper.insert(followRelation);
                        rc = FAIL_OP;
                        break;
                    }
                } else {
                    rc = WRONG_OP;
                }
                break;
            }
        }
        return rc;
    }

    @Override
    public boolean checkFollow(int userId, int toCheckId) {
        FollowRelation relation = followMapper.selectBy2Id(userId, toCheckId);
        return relation != null;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> getUsersByPage(int page, int size) {
        return userMapper.selectByPage((page - 1) * size, size);
    }

    @Override
    public User getUserInfo(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<Integer> getPubCourses(int userId, int page, int size) {
        return userCourseRelationMapper.selectOwnsByUserIdInPage(userId, (page - 1) * size, size);
    }

    @Override
    public List<User> getUsersByIds(List<Integer> ids) {
        List<User> users = new ArrayList<>();
        for (int id : ids) {
            User user = userMapper.selectById(id);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }

}

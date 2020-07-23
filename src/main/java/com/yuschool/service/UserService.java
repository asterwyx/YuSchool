package com.yuschool.service;

import com.yuschool.bean.User;
import com.yuschool.constants.enums.Operation;
import com.yuschool.constants.enums.RetCode;

import java.util.List;

public interface UserService {

    /**
     * 传入user的属性插入并且返回插入成功的user
     * @param prepUser 需要被插入获取主键和时间的用户，除去数据库中获取的信息之外，其它信息必须填完整
     * @return 已经插入并且回填自增主键的user对象
     */
    boolean addUser(User prepUser);

    RetCode updateUserInfo(User prepUser);
    /**
     * 检查用户名是否已经被注册
     * @param username 要检查的用户名
     * @return 是否已经存在
     */
    boolean checkExistence(String username);

    List<User> getAllFans(int userId);

    List<User> getAllFollows(int userId);

    RetCode updateFollow(int userId, int followingId, Operation operation);

    /**
     * 检测前一个用户是否关注了后一个用户
     * @param userId 要检测是否关注的用户
     * @param toCheckId 要检测是否被关注的用户
     * @return 是否关注
     */
    boolean checkFollow(int userId, int toCheckId);
    List<User> getAllUsers();
    List<User> getUsersByPage(int page, int size);
    User getUserInfo(int id);
}

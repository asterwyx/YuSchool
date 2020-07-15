package com.yuschool.service;

import com.yuschool.bean.User;

public interface UserService {

    boolean addUser(User prepUser);

    boolean checkExistence(String username);

}

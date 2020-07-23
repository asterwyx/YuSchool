package com.yuschool.service;

import com.yuschool.bean.Account;
import com.yuschool.constants.enums.RetCode;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {

    RetCode changeUsername(int userId, String username);
    public boolean addAdminAccount(com.yuschool.bean.User user, String password);
    public boolean addNormalAccount(com.yuschool.bean.User user, String password);
    boolean changePassword(int userId, String password);
    Account getAccount(int userId);
}

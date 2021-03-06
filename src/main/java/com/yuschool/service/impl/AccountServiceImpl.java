package com.yuschool.service.impl;

import com.yuschool.bean.Account;
import com.yuschool.bean.Authority;
import com.yuschool.bean.factory.AuthorityFactory;
import com.yuschool.constants.enums.RetCode;
import com.yuschool.mapper.AccountMapper;
import com.yuschool.mapper.UserAuthorityMapper;
import com.yuschool.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.yuschool.constants.enums.RetCode.*;

@Service
public class AccountServiceImpl implements AccountService {

    public static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    UserAuthorityMapper userAuthorityMapper;
    @Autowired
    PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountMapper.selectByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("没有找到对应账户");
        } else {
            List<Integer> authorityIds = userAuthorityMapper.selectByUserId(account.getUserId());
            return new User(account.getUsername(), account.getPassword(), account.isEnabled(), account.isAccountNonExpired(), account.isCredentialsNonExpired(), account.isAccountNonLocked(), AuthorityFactory.getInstances(authorityIds));
        }
    }

    @Override
    public boolean addNormalAccount(com.yuschool.bean.User user, String password) {
        // 创建账户
        Authority authority = AuthorityFactory.getInstanceByName(Authority.ROLE_USER);
        Account account = new Account(user.getUsername(), encoder.encode(password));
        account.setUserId(user.getId());
        account.setPhoneNum("");
        int rc = accountMapper.insert(account);
        if (rc <= 0) {
            logger.error("插入用户账号失败");
            return false;
        }
        // 创建权限记录
        rc = userAuthorityMapper.insert(user.getId(), authority.getId());
        if (rc <= 0) {
            logger.error("插入用户权限记录失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean addAdminAccount(com.yuschool.bean.User user, String password) {
        // 创建账户
        List<Authority> authorities = Arrays.asList(
                AuthorityFactory.getInstanceByName(Authority.ROLE_USER),
                AuthorityFactory.getInstanceByName(Authority.ROLE_ADMIN)
        );
        Account account = new Account(user.getUsername(), encoder.encode(password));
        account.setUserId(user.getId());
        account.setPhoneNum("");
        int rc = accountMapper.insert(account);
        if (rc <= 0) {
            logger.error("插入用户账号失败");
            return false;
        }
        // 创建权限记录
        for (Authority authority : authorities) {
            rc = userAuthorityMapper.insert(user.getId(), authority.getId());
            if (rc <= 0) {
                logger.error("插入用户权限记录失败");
                return false;
            }
        }
        return true;
    }

    @Override
    public RetCode changeUsername(int userId, String username) {
        int infNum = accountMapper.updateUsername(userId, username);
        if (infNum <= 0) {
            return FAIL_OP;
        } else {
            return SUCCESS;
        }
    }

    @Override
    public boolean changePassword(int userId, String password) {
        password = encoder.encode(password);
        int infNum = accountMapper.updatePassword(userId, password);
        if (infNum <= 0) {
            logger.error("用户" + userId + "修改密码失败");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Account getAccount(int userId) {
        return accountMapper.selectByUserId(userId);
    }
}

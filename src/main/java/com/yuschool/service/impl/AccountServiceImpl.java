package com.yuschool.service.impl;

import com.yuschool.bean.Account;
import com.yuschool.bean.Authority;
import com.yuschool.bean.factory.AuthorityFactory;
import com.yuschool.mapper.AccountMapper;
import com.yuschool.mapper.UserAuthorityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountServiceImpl implements UserDetailsService {

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

    /**
     * 添加普通账户，这里包括创建账户，创建账户权限记录
     */
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
}

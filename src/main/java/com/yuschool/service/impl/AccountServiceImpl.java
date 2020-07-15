package com.yuschool.service.impl;

import com.yuschool.bean.Account;
import com.yuschool.bean.Authority;
import com.yuschool.factory.AuthorityFactory;
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
import java.util.Collections;
import java.util.List;

@Service
public class AccountServiceImpl implements UserDetailsService {

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    UserAuthorityMapper userAuthorityMapper;

    @Autowired
    PasswordEncoder encoder;

    public static final Logger logger = LoggerFactory.getLogger("AccountServiceLogger");
    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
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
     * @param user 已经创建好的用户基本信息
     * @param password 传过来的密码
     * @return 创建好的账户信息
     */
    public boolean addNormalAccount(com.yuschool.bean.User user, String password) {
        // 创建账户
        Authority authority = AuthorityFactory.getInstanceByName(Authority.ROLE_USER);
        Account account = new Account(user.getUsername(), encoder.encode(password), Collections.singletonList(authority));
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
        Account account = new Account(user.getUsername(), encoder.encode(password), authorities);
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

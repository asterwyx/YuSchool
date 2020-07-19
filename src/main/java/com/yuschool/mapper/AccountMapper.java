package com.yuschool.mapper;

import com.yuschool.bean.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface AccountMapper {

    @Select("SELECT * FROM accounts WHERE user_id=#{userId};")
    Account selectByUserId(int userId);

    @Insert("INSERT INTO accounts (user_id, username, password, phone_num, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_updated_time) VALUES (#{userId}, #{username}, #{password}, #{phoneNum}, #{accountNonExpired}, #{accountNonLocked}, #{credentialsNonExpired}, #{enabled}, #{lastUpdatedTime});")
    int insert(Account account);

    @Update("UPDATE accounts SET username=#{username}, password=#{password}, phone_num=#{phoneNum}, account_non_expired=#{accountNonExpired}, account_non_locked=#{accountNonLocked}, credentials_non_expired=#{credentialsNonExpired}, enabled=#{enabled}, last_updated_time=#{lastUpdatedTime} WHERE user_id=#{userId};")
    int update(Account account);

    @Delete("DELETE FROM accounts WHERE user_id=#{userId};")
    int deleteByUserId(int userId);

    @Update("UPDATE accounts SET enabled=1 WHERE user_id=#{userId}")
    int enableAccountByUserId(int userId);

    @Update("UPDATE accounts SET enabled=0 WHERE user_id=#{userId}")
    int disableAccountByUserId(int userId);

    int updateUsername(@Param("userId") int userId, @Param("username") String username);

    @Update("UPDATE accounts users SET password=#{password} WHERE user_id=#{userId};")
    int updatePassword(int userId, String password);

    @Select("SELECT * FROM accounts WHERE username=#{username};")
    Account selectByUsername(String username);

}

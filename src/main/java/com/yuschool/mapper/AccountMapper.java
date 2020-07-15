package com.yuschool.mapper;

import com.yuschool.bean.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface AccountMapper {

    @Select("SELECT * FROM accounts WHERE user_id=#{userId};")
    Account selectByUserId(int userId);

    @Insert("INSERT INTO accounts (user_id, username, password, phone_num, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_updated_time) VALUES (#{userId}, #{username}, #{password}, #{phoneNum}, #{accountNonExpired}, #{accountNonLocked}, #{credentialsNonExpired}, #{enabled}, #{lastUpdatedTime});")
    int insert(Account account);

    /**
     * 全更新
     * @param account 要更新的存有最新信息的账户
     * @return 被更新的账户的主键
     */
    @Update("UPDATE accounts SET username=#{username}, password=#{password}, phone_num=#{phoneNum}, account_non_expired=#{accountNonExpired}, account_non_locked=#{accountNonLocked}, credentials_non_expired=#{credentialsNonExpired}, enabled=#{enabled}, last_updated_time=#{lastUpdatedTime} WHERE user_id=#{userId};")
    int update(Account account);

    @Delete("DELETE FROM accounts WHERE user_id=#{userId};")
    int deleteByUserId(int userId);

    /**
     * 启用账户
     * @param userId 账户id
     * @return 被启用的账户id
     */
    @Update("UPDATE accounts SET enabled=1 WHERE user_id=#{userId}")
    int enableAccountByUserId(int userId);

    /**
     * 禁用账户
     * @param userId 账户id
     * @return 被禁用的账户的主键
     */
    @Update("UPDATE accounts SET enabled=0 WHERE user_id=#{userId}")
    int disableAccountByUserId(int userId);

    /**
     * 根据用户名获得账户
     * @param username 用户名
     * @return 获得的账户
     */
    @Select("SELECT * FROM accounts WHERE username=#{username};")
    Account selectByUsername(String username);

}

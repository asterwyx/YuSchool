package com.shareknowledge.mapper;

import com.shareknowledge.bean.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import static com.shareknowledge.constant.TypeConstants.STATUS_ACTIVE;
import static com.shareknowledge.constant.TypeConstants.STATUS_LOCKED;

@Component
public interface AccountMapper {

    @Select("SELECT * FROM accounts WHERE user_id=#{userId};")
    Account selectByUserId(int userId);

    @Insert("INSERT INTO accounts (user_id, email, password, salt, status, phone_num, last_updated_time) VALUES (#{userId}, #{email}, #{password}, #{salt}, #{status}, #{phoneNum}, NOW());")
    int insert(Account account);

    @Update("UPDATE accounts SET email=#{email}, salt=#{salt}, status=#{status}, phone_num=#{phoneNum} WHERE user_id=#{userId};")
    int update(Account account);

    @Delete("DELETE FROM accounts WHERE user_id=#{userId};")
    int deleteByUserId(int userId);

    @Update("UPDATE accounts SET status=" + STATUS_ACTIVE + " WHERE user_id=#{userId}")
    int activateAccountByUserId(int userId);

    @Update("UPDATE accounts SET status=" + STATUS_LOCKED + " WHERE user_id=#{userId}")
    int lockAccountByUserId(int userId);

}

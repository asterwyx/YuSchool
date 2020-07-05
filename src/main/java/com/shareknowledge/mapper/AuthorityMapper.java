package com.shareknowledge.mapper;

import com.shareknowledge.bean.Authority;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface AuthorityMapper {

    @Select("SELECT * FROM authorities WHERE user_id=#{userId}")
    Authority selectByUserId(int userId);

    @Insert("INSERT INTO authorities (user_id, authority, last_updated_time) VALUES (#{userId}, #{authority}, NOW());")
    int insert(Authority authority);

    @Update("UPDATE authorities SET authority=#{authority} WHERE user_id=#{userId};")
    int update(Authority authority);

    @Delete("DELETE FROM authorities WHERE user_id=#{userId}")
    int deleteByUserId(int userId);

}

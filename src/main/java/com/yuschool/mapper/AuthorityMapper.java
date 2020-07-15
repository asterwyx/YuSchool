package com.yuschool.mapper;

import com.yuschool.bean.Authority;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuthorityMapper {

    @Select("SELECT * FROM authorities WHERE id=#{id}")
    Authority selectById(int id);

    @Insert("INSERT INTO authorities (authority) VALUES (#{authority});")
    int insert(Authority authority);

    @Update("UPDATE authorities SET authority=#{authority} WHERE id=#{id};")
    int update(Authority authority);

    @Delete("DELETE FROM authorities WHERE id=#{id}")
    int deleteById(int id);

    @Select("SELECT * FROM authorities;")
    List<Authority> selectAll();
}

package com.shareknowledge.mapper;

import com.shareknowledge.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    @Select("SELECT * FROM users WHERE id=#{id}")
    User selectById(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO users (user_name, gender, age, head_file_path, detail, register_time, last_updated_time) VALUES (#{userName}, #{gender}, #{age}, #{headFilePath}, #{detail}, NOW(), NOW());")
    int insert(User user);

    @Update("UPDATE users SET id=#{id}, user_name=#{userName}, gender=#{gender}, age=#{age}, head_file_path=#{headFilePath}, detail=#{detail};")
    int update(User user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    int deleteById(int id);
}

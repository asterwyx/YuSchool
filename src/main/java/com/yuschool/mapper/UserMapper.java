package com.yuschool.mapper;

import com.yuschool.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    @Select("SELECT * FROM users WHERE id=#{id}")
    User selectById(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO users (username, gender, age, head_file_path, detail, register_time, last_updated_time) VALUES (#{username}, #{gender}, #{age}, #{headFilePath}, #{detail}, #{registerTime}, #{lastUpdatedTime});")
    int insert(User user);

    @Update("UPDATE users SET username=#{username}, gender=#{gender}, age=#{age}, head_file_path=#{headFilePath}, detail=#{detail}, last_updated_time=#{lastUpdatedTime} WHERE id=#{id};")
    int update(User user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    int deleteById(int id);

    @Select("SELECT * FROM users WHERE username=#{username};")
    User selectByUsername(String username);
}

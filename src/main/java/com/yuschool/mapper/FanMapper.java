package com.yuschool.mapper;

import com.yuschool.bean.FanRelation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FanMapper {

    @Select("SELECT * FROM fans WHERE id=#{id};")
    FanRelation selectById(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO fans (user_id, following_user_id, status, created_time, last_updated_time) VALUES (#{userId}, #{followingUserId}, #{status}, #{createdTime}, #{lastUpdatedTime});")
    int insert(FanRelation fanRelation);

    @Update("UPDATE fans SET user_id=#{user_id}, following_user_id=#{followingUserId}, status=#{status}, created_time=#{createdTime}, last_updated_time=#{lastUpdatedTime} WHERE id=#{id};")
    int update(FanRelation fanRelation);

    @Delete("DELETE FROM fans WHERE id=#{id}")
    int deleteById(int id);

    @Select("SELECT * FROM fans WHERE user_id=#{userId};")
    List<FanRelation> selectByUserId(int userId);
}

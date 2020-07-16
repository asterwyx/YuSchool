package com.yuschool.mapper;

import com.yuschool.bean.FanRelation;
import com.yuschool.bean.FollowRelation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FollowMapper {

    @Select("SELECT * FROM follows WHERE id=#{id};")
    FollowRelation selectById(int id);

    @Select("SELECT following_user_id FROM follows WHERE user_id=#{userId};")
    List<Integer> selectAllFollowsByUserId(int userId);

    @Select("SELECT * FROM follows WHERE user_id=#{userId} AND following_user_id=#{followingUserId};")
    FollowRelation selectBy2Id(int userId, int followingUserId);

    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("INSERT INTO follows (user_id, following_user_id, created_time, last_updated_time) VALUES (#{userId}, #{followingUserId}, #{createdTime}, #{lastUpdatedTime});")
    int insert(FollowRelation followRelation);

    @Update("UPDATE follows SET user_id=#{userId}, following_user_id=#{followingUserId}, created_time=#{createdTime}, last_updated_time=#{lastUpdatedTime} WHERE id=#{id};")
    int update(FanRelation fanRelation);

    @Delete("DELETE FROM follows WHERE id=#{id};")
    int deleteById(int id);

    @Delete("DELETE FROM follows WHERE user_id=#{userId} AND following_user_id=#{followingUserId};")
    int deleteBy2Id(int userId, int followingUserId);
}

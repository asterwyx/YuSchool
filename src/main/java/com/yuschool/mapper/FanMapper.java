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
    @Insert("INSERT INTO fans (user_id, fan_id, created_time, last_updated_time) VALUES (#{userId}, #{fanId}, #{createdTime}, #{lastUpdatedTime});")
    int insert(FanRelation fanRelation);

    @Update("UPDATE fans SET user_id=#{user_id}, fan_id=#{fanId}, created_time=#{createdTime}, last_updated_time=#{lastUpdatedTime} WHERE id=#{id};")
    int update(FanRelation fanRelation);

    @Delete("DELETE FROM fans WHERE id=#{id}")
    int deleteById(int id);

    @Select("SELECT * FROM fans WHERE user_id=#{userId};")
    List<FanRelation> selectByUserId(int userId);

    @Select("SELECT fan_id FROM fans WHERE user_id=#{userId};")
    List<Integer> selectAllFansByUserId(int userId);
}

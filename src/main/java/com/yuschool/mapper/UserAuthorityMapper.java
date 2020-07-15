package com.yuschool.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserAuthorityMapper {

    @Insert("INSERT INTO user_authorities (user_id, authority_id) VALUES (#{userId}, #{authorityId});")
    int insert(int userId, int authorityId);

    @Delete("DELETE FROM user_authorities WHERE user_id=#{userId} and authority_id=#{authorityId};")
    int delete(int userId, int authorityId);

    @Select("SELECT authority_id FROM user_authorities WHERE user_id=#{userId};")
    List<Integer> selectByUserId(int userId);


}

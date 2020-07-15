package com.yuschool.mapper;

import com.yuschool.bean.Resource;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface ResourceMapper {

    @Select("SELECT * FROM resources WHERE id=#{id}")
    Resource selectById(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO resources (resource_name, full_name, type, size, section_id) VALUES (#{id}, #{resourceName}, #{fullName}, #{type}, #{size}, #{sectionId});")
    int insert(Resource resource);

    @Update("UPDATE resources SET resource_name=#{resourceName}, full_name=#{fullName}, type=#{type}, size=#{size}, section_id=#{sectionId} WHERE id=#{id}")
    int update(Resource resource);

    @Delete("DELETE FROM resources WHERE id=#{id}")
    int deleteById(int id);
}

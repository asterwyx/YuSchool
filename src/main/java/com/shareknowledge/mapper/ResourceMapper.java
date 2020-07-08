package com.shareknowledge.mapper;

import com.shareknowledge.bean.Resource;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface ResourceMapper {

    @Select("SELECT * FROM resources WHERE id=#{id}")
    Resource selectById(int id);

    // TODO 自动回填插入时间，更新时间
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO resources (resource_name, full_name, type, size, section_id) VALUES (#{id}, #{resourceName}, #{fullName}, #{type}, #{size}, #{sectionId});")
    int insert(Resource resource);

    // TODO 自动回填更新时间
    @Update("UPDATE resources SET resource_name=#{resourceName}, full_name=#{fullName}, type=#{type}, size=#{size}, section_id=#{sectionId} WHERE id=#{id}")
    int update(Resource resource);

    @Delete("DELETE FROM resources WHERE id=#{id}")
    int deleteById(int id);
}

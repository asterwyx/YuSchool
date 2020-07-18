package com.yuschool.mapper;

import com.yuschool.bean.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseMapper {

    @Select("SELECT * FROM courses;")
    List<Course> selectAll();

    @Select("SELECT * FROM courses WHERE id=#{id};")
    Course selectById(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO courses (course_name, introduction, cover_file_path, star_num, is_reviewed, is_locked, created_time, last_updated_time) VALUES (#{courseName}, #{introduction}, #{coverFilePath}, #{starNum}, #{isReviewed}, #{isLocked}, #{createdTime}, #{lastUpdatedTime});")
    int insert(Course course);

    @Update("UPDATE courses SET course_name=#{courseName}, introduction=#{introduction}, cover_file_path=#{coverFilePath}, star_num=#{starNum}, is_reviewed=#{isReviewed}, is_locked=#{isLocked}, last_updated_time=#{lastUpdatedTime} WHERE id=#{id};")
    int update(Course course);

    @Delete("DELETE FROM courses WHERE id=#{id};")
    int deleteById(int id);
}

package com.yuschool.mapper;

import com.yuschool.bean.Course;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseMapper {

    @Select("SELECT * FROM courses;")
    List<Course> selectAll();

    List<Course> selectByPage(@Param("off") int off, @Param("size") int size);
    @Select("SELECT * FROM courses WHERE id=#{id};")
    Course selectById(@Param("id") int id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO courses (course_name, introduction, cover_file_path, star_num, is_reviewed, is_locked, created_time, last_updated_time) VALUES (#{courseName}, #{introduction}, #{coverFilePath}, #{starNum}, #{isReviewed}, #{isLocked}, #{createdTime}, #{lastUpdatedTime});")
    int insert(@Param("course") Course course);

    @Update("UPDATE courses SET course_name=#{courseName}, introduction=#{introduction}, cover_file_path=#{coverFilePath}, star_num=#{starNum}, is_reviewed=#{isReviewed}, is_locked=#{isLocked}, last_updated_time=#{lastUpdatedTime} WHERE id=#{id};")
    int update(@Param("course") Course course);

    int deleteCourseById(@Param("id") int id);
    int deleteCoursesByIds(@Param("ids") List<Integer> ids);
    int updateReviewStatus(@Param("courseId") int courseId, @Param("status") boolean status);
    int updateLockStatus(@Param("courseId") int courseId, @Param("status") boolean status);
}

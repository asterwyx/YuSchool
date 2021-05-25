package com.yuschool.mapper;

import com.yuschool.bean.UserCourseRelation;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserCourseRelationMapper {

    @Select("SELECT * FROM user_course_relations WHERE id=#{id};")
    UserCourseRelation selectById(int id);

    @Select("SELECT * FROM user_course_relations WHERE user_id=#{userId} AND course_id=#{courseId};")
    UserCourseRelation selectBy2Id(int userId, int courseId);

    @Select("SELECT course_id FROM user_course_relations WHERE user_id=#{userId} AND has_starred=1;")
    List<Integer> selectAllStarsByUserId(int userId);

    List<Integer> selectOwnsByUserIdInPage(@Param("userId") int userId, @Param("off") int off, @Param("size") int size);
    List<Integer> selectStarsByUserIdInPage(@Param("userId") int userId, @Param("off") int off, @Param("size") int size);
    @Select("SELECT course_id FROM user_course_relations WHERE user_id=#{userId} AND owns=1;")
    List<Integer> selectAllOwnsByUserId(int userId);

    @Select("SELECT course_id FROM user_course_relations WHERE user_id=#{userId} AND manages=1;")
    List<Integer> selectAllManagesByUserId(int userId);

    @Select("SELECT user_id FROM user_course_relations WHERE course_id=#{courseId} AND has_starred=1;")
    List<Integer> selectAllStarMemsByCourseId(int courseId);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO user_course_relations (user_id, course_id, has_starred, owns, manages, created_time, last_updated_time) VALUES (#{userId}, #{courseId}, #{hasStarred}, #{owns}, #{manages}, #{createdTime}, #{lastUpdatedTime});")
    int insert(UserCourseRelation userCourseRelation);

    @Update("UPDATE user_course_relations SET user_id=#{userId}, course_id=#{courseId}, has_starred=#{hasStarred}, owns=#{owns}, manages=#{manages}, created_time=#{createdTime}, last_updated_time=#{lastUpdatedTime} WHERE id=#{id};")
    int update(UserCourseRelation userCourseRelation);

    int cancleStarBy2Id(@Param("userId") int userId, @Param("courseId") int courseId);
    int cancleManageBy2Id(@Param("userId") int userId, @Param("courseId") int courseId);
    int cancleOwnBy2Id(@Param("userId") int userId, @Param("courseId") int courseId);
    int deleteById(@Param("id") int id);
    int deleteBy2Id(@Param("userId") int userId, @Param("courseId") int courseId);
}

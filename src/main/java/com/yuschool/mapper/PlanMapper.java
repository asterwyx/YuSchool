package com.yuschool.mapper;

import com.yuschool.bean.Plan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface PlanMapper {

    @Select("SELECT * FROM plans WHERE course_id=#{courseId};")
    Plan selectByCourseId(int courseId);

    @Select("SELECT plan FROM plan_stages WHERE plan_id=#{planId};")
    String selectStage(int courseId);

    @Insert("INSERT INTO plan_stages (plan_id, plan) VALUES (#{planId}, #{plan});")
    int addStage(int courseId, String plan);

    @Insert("INSERT INTO plans (course_id, plan, created_time, last_updated_time) VALUES (#{courseId}, #{plan}, #{createdTime}, #{lastUpdatedTime});")
    int insert(Plan plan);

    @Update("UPDATE plans SET plan=#{plan}, last_updated_time=#{lastUpdatedTime} WHERE course_id=#{courseId};")
    int update(Plan plan);

    @Update("UPDATE plan_stages SET plan=#{plan} WHERE plan_id=#{planId};")
    int updateStage(int courseId, String plan);

    @Delete("DELETE FROM plan_stages WHERE plan_id=#{planId};")
    int deleteStage(int courseId);

    @Delete("DELETE FROM plans WHERE course_id=#{courseId};")
    int deleteByCourseId(int courseId);
}

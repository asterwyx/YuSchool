package com.yuschool.mapper;

import com.yuschool.bean.Plan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PlanMapper {

    @Select("SELECT * FROM plans WHERE course_id=#{courseId};")
    Plan selectByCourseId(int courseId);

    @Insert("INSERT INTO plans (course_id, plan, created_time, last_updated_time) VALUES (#{courseId}, #{plan}, #{createdTime}, #{lastUpdatedTime});")
    int insert(Plan plan);

    @Update("UPDATE plans SET plan=#{plan}, last_updated_time=#{lastUpdatedTime} WHERE course_id=#{courseId};")
    int update(Plan plan);

    @Delete("DELETE FROM plans WHERE course_id=#{courseId};")
    int deleteByCourseId(int courseId);
}

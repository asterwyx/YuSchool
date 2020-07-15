package com.yuschool.mapper;

import com.yuschool.bean.CheckinRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface CheckinRecordMapper {

    @Select("SELECT * FROM checkin_records WHERE user_id=#{userId};")
    CheckinRecord selectByUserId(int userId);

    @Insert("INSERT INTO checkin_records (user_id, checkin_days, points, last_checkin_time) VALUES (#{userId}, #{checkinDays}, #{points}, #{lastCheckinTime});")
    int insert(CheckinRecord checkinRecord);

    @Update("UPDATE checkin_records SET checkin_days=#{checkinDays}, points=#{points}, last_checkin_time=#{lastCheckinTime} WHERE user_id=#{userId};")
    int update(CheckinRecord checkinRecord);

    @Delete("DELETE FROM checkin_records WHERE user_id=#{userId};")
    int deleteByUserId(int userId);
}

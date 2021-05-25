package com.yuschool.mapper;

import com.yuschool.bean.History;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HistoryMapper {
    List<History> selectHistoriesByUserId(@Param("userId") int userId);
    List<History> selectPubHistoriesByUserId(@Param("userId") int userId);
    List<History> selectPriHistoriesByUserId(@Param("userId") int userId);
}

package com.yuschool.mapper.provider;

import com.yuschool.bean.User;
import com.yuschool.constants.DefaultValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class UserMapperProvider {
    public static final Logger logger = LoggerFactory.getLogger(UserMapperProvider.class);
    public String updateSelective(Map<String, Object> params) {
        User user = (User) params.get("user");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE users SET ");
        if (user.getUsername() != null) {
            stringBuilder.append("username='").append(user.getUsername()).append("', ");
        }
        if (user.getAge() != DefaultValue.AGE_NULL) {
            stringBuilder.append("age=").append(user.getAge()).append(", ");
        }
        if (user.getGender() != null) {
            stringBuilder.append("gender='").append(user.getGender()).append("', ");
        }
        if (user.getHeadFilePath() != null) {
            stringBuilder.append("head_file_path=").append(user.getHeadFilePath()).append("', ");
        }
        if (user.getDetail() != null) {
            stringBuilder.append("detail='").append(user.getDetail()).append("' ");
        }
        stringBuilder.append("last_updated_time='").append(user.getLastUpdatedTime());
        stringBuilder.append("' WHERE id=").append(user.getId()).append(";");
        logger.debug(stringBuilder.toString());
        return stringBuilder.toString();
    }
}

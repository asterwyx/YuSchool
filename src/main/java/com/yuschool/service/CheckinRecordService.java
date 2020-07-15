package com.yuschool.service;

import com.yuschool.bean.CheckinRecord;
import com.yuschool.constants.enums.RetCode;

public interface CheckinRecordService {

    CheckinRecord getCheckinRecord(int userId);

    RetCode checkin(int userId);
}

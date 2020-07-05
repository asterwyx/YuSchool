package com.shareknowledge.bean;


import com.shareknowledge.utils.Logger;

import java.sql.Timestamp;

import static com.shareknowledge.utils.MyConstants.PK_NULL;


/**
 * 存储用户签到天数的实体，非自增主键，主键即是用户id
 */
public class CheckinRecord extends Entity{

    private int checkinDays;
    private Timestamp lastCheckinTime;

    public CheckinRecord() {
        this(PK_NULL, 0, Logger.getCurrentTime());
    }

    public CheckinRecord(
            int primaryKey,
            int checkinDays,
            Timestamp lastCheckinTime
    ) {
        super(primaryKey);
        this.checkinDays = checkinDays;
        this.lastCheckinTime = lastCheckinTime;
    }

    public int getCheckinDays() {
        return checkinDays;
    }

    public void setCheckinDays(int checkinDays) {
        this.checkinDays = checkinDays;
    }

    public Timestamp getLastCheckinTime() {
        return lastCheckinTime;
    }

    public void setLastCheckinTime(Timestamp lastCheckinTime) {
        this.lastCheckinTime = lastCheckinTime;
    }
}

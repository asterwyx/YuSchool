package com.shareknowledge.bean;

import java.sql.Timestamp;

import static com.shareknowledge.utils.MyConstants.FLAG_NULL;
import static com.shareknowledge.utils.MyConstants.PK_NULL;

public class History extends Entity{
    private Timestamp time;
    private String detail;
    private int limit;
    private int userId;

    public History() {
        this(PK_NULL, new Timestamp(System.currentTimeMillis()), "", FLAG_NULL, PK_NULL);
    }

    public History(
            int primaryKey,
            Timestamp time,
            String detail,
            int limit,
            int userId
    ) {
        super(primaryKey);
        this.time = time;
        this.detail = detail;
        this.limit = limit;
        this.userId = userId;
    }

    public Timestamp getTime() { return time; }

    public void setTime(Timestamp time) { this.time = time; }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

    public int getLimit() { return limit; }

    public void setLimit(int limit) { this.limit = limit; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }
}

package com.shareknowledge.bean;


import java.sql.Timestamp;

public class Report {
    private int id;
    private int reporterId;
    private String reportReason;
    private int reporteeId;
    private String detail;
    private String targetUrl;
    private Timestamp reportedTime;
    private int isHandled;


    public int getReporterId() { return reporterId; }

    public void setReporterId(int reporterId) { this.reporterId = reporterId; }

    public String getReportReason() { return reportReason; }

    public void setReportReason(String reportReason) { this.reportReason = reportReason; }

    public int getReporteeId() { return reporteeId; }

    public void setReporteeId(int reporteeId) { this.reporteeId = reporteeId; }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

    public String getTargetUrl() { return targetUrl; }

    public void setTargetUrl(String targetUsl) { this.targetUrl = targetUsl; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getReportedTime() {
        return reportedTime;
    }

    public void setReportedTime(Timestamp reportedTime) {
        this.reportedTime = reportedTime;
    }

    public int getIsHandled() {
        return isHandled;
    }

    public void setIsHandled(int isHandled) {
        this.isHandled = isHandled;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reporterId=" + reporterId +
                ", reportReason='" + reportReason + '\'' +
                ", reporteeId=" + reporteeId +
                ", detail='" + detail + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                ", reportedTime=" + reportedTime +
                ", isHandled=" + isHandled +
                '}';
    }
}

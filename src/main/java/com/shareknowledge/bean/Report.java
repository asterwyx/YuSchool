package com.shareknowledge.bean;


import static com.shareknowledge.utils.MyConstants.PK_NULL;

public class Report extends Entity{
    private int reporterId;
    private String reportReason;
    private int reporteeId;
    private String detail;
    private String targetUrl;

    public Report() {
        this(PK_NULL, PK_NULL, "", PK_NULL, "", "");
    }

    public Report(
            int primaryKey,
            int reporterId,
            String reportReason,
            int reporteeId,
            String detail,
            String targetUrl
    ) {
        super(primaryKey);
        this.reporterId = reporterId;
        this.reportReason = reportReason;
        this.reporteeId = reporteeId;
        this.detail = detail;
        this.targetUrl = targetUrl;
    }

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
}

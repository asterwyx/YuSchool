package com.shareknowledge.utils;

public class ResultInfo implements java.io.Serializable {
    private boolean flag;
    private Object data;
    private String errorMsg;

    public ResultInfo() {
        this(true, null, null);
    }
    public ResultInfo(boolean flag) {
        this(flag, null, null);
    }
    public ResultInfo(String errorMsg) {
        this(false, null, errorMsg);
    }
    public ResultInfo(boolean flag, Object data, String errorMsg) {
        this.flag = flag;
        this.data = data;
        this.errorMsg = errorMsg;
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

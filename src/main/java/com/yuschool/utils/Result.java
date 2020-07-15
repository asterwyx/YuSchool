package com.yuschool.utils;

import com.yuschool.constants.enums.RetCode;
import org.springframework.util.Assert;

import java.io.Serializable;

public class Result implements Serializable {

    public final static long serialVersionUID = 1L;

    private RetCode retCode;
    private String message;
    private Object data;

    public Result(RetCode retCode, String message, Object data) {
        this.retCode = retCode;
        this.message = message;
        this.data = data;
    }

    // 默认成功构造函数
    public Result() {
        this(RetCode.SUCCESS, "", "");
    }

    public RetCode getRetCode() {
        return retCode;
    }

    public void setRetCode(RetCode retCode) {
        this.retCode = retCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultBuilder builder() {
        return new ResultBuilder();
    }

    public static ResultBuilder withCode(RetCode retCode) {
        return builder().code(retCode);
    }

    public static ResultBuilder withMessage(String message) {
        return builder().message(message);
    }

    public static ResultBuilder withData(Object data) {
        return builder().data(data);
    }

    /**
     * builder类
     */
    public static class ResultBuilder {

        private RetCode retCode;
        private String message;
        private Object data;

        public ResultBuilder() {
            this.retCode = RetCode.SUCCESS;
            this.message = "";
            this.data = "";
        }

        public ResultBuilder code(RetCode retCode) {
            Assert.notNull(retCode, "返回码不能够为空");
            this.retCode = retCode;
            return this;
        }

        public ResultBuilder message(String message) {
            Assert.notNull(message, "消息不能够为空");
            this.message = message;
            return this;
        }

        public ResultBuilder data(Object data) {
            Assert.notNull(data, "数据不能够为空");
            this.data = data;
            return this;
        }

        public Result build() {
            return new Result(this.retCode, this.message, this.data);
        }
    }

    @Override
    public String toString() {
        return "Result{" +
                "retCode=" + retCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

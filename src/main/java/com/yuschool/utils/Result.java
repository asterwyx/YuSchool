package com.yuschool.utils;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.Assert;

import java.io.Serializable;

public class Result implements Serializable {

    public final static long serialVersionUID = 1L;

    private Code code;
    private String message;
    private Object data;

    public Result(Code code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 默认成功构造函数
    public Result() {
        this(Code.SUCCESS, "", "");
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
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

    public static ResultBuilder withCode(Code code) {
        return builder().code(code);
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

        private Code code;
        private String message;
        private Object data;

        public ResultBuilder() {
            this.code = Code.SUCCESS;
            this.message = "";
            this.data = "";
        }

        public ResultBuilder code(Code code) {
            Assert.notNull(code, "返回码不能够为空");
            this.code = code;
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
            return new Result(this.code, this.message, this.data);
        }
    }

    /**
     * 返回值的枚举
     */
    public enum Code {
        SUCCESS(0),
        IO_ERROR(-1),
        WRONG_OP(-2),
        FAIL_OP(-3),
        DUP_VALUE(-4);

        private final int value;

        Code(int value) {
            this.value = value;
        }

        @JsonValue
        public int getValue() {
            return this.value;
        }
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

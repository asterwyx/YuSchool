package com.shareknowledge.utils;

import java.io.PrintStream;
import java.sql.Timestamp;

public class Logger {

    public static final int DEBUG = 0;
    public static final int INFO = 1;
    public static final int WARN = 2;
    public static final int ERROR = 3;
    public static final int FATAL = 4;
    public static final int OFF = 5;

    private int level;
    private PrintStream ps;

    public Logger(PrintStream ps, int level) {
        this.ps = ps;
        this.level = level;
    }

    public Logger() {
        this(OFF);
    }

    public Logger(int level) {
        this(System.err, level);
    }

    public static Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    public void debug(Object msg) {
        if (this.level <= DEBUG) {
            this.ps.println(getCurrentTime() + "--调试--" + msg);
        }
    }

    public void info(Object msg) {
        if (this.level <= INFO) {
            this.ps.println(getCurrentTime() + "--信息--" + msg);
        }
    }

    public void warn(Object msg) {
        if (this.level <= WARN) {
            this.ps.println(getCurrentTime() + "--警告--" + msg);
        }
    }

    public void error(Object msg) {
        if (this.level <= ERROR) {
            this.ps.println(getCurrentTime() + "--错误--" + msg);
        }
    }

    public void fatal(Object msg) {
        if (this.level <= FATAL) {
            this.ps.println(getCurrentTime() + "--致命--" + msg);
        }
    }

    public PrintStream getPs() {
        return ps;
    }

    public void setPs(PrintStream ps) {
        this.ps = ps;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

package com.study.common;

/**
 * 返回值状态
 * 每个值代表不同的返回状态
 * Created by vector on 16/11/10.
 */
public class Status {

    public static final short SUCCESS = 0;

    public static final short ERROR = 1;

    public static final short NO_REMOVE = 2;

    public static final short NO_DELETE = 3;

    public static final short NO_UPDATE = 4;

    public static final short LOCK = 5;

    public static final short EXISTS = 6;

    public static final short NOT_EXISTS = 7;

    public static final short VERSION_NOT_MATCH = 8;

    public static final short PASSWORD_NOT_MATCH = 9;

    public static final short CODE_NOT_MATCH = 9;

    public static final short BLOCKED = 11;
}

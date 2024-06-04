package com.diligrp.assistant.shared;

/**
 * 系统错误码列表 - 错误码前三位用于区分模块
 */
public class ErrorCode {
    // 系统未知异常
    public static final int SYSTEM_UNKNOWN_ERROR = 100000;
    // 无效参数错误
    public static final int ILLEGAL_ARGUMENT_ERROR = 100001;
    // 访问未授权
    public static final int UNAUTHORIZED_ACCESS_ERROR = 100002;
    // 操作不允许
    public static final int OPERATION_NOT_ALLOWED = 100003;
    // 对象不存在
    public static final int OBJECT_NOT_FOUND = 100004;
    // 对象已存在
    public static final int OBJECT_ALREADY_EXISTS = 100005;
    // 远程服务访问错误
    public static final int SERVICE_ACCESS_ERROR = 101000;

    public static final String MESSAGE_UNKNOWN_ERROR = "基础服务系统未知异常，请联系管理员";

    public static final String MESSAGE_ACCESS_DENIED = "未授权的系统访问";
}

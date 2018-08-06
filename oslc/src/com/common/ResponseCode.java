package com.common;

/**
 * Created by tuguoping 
 */
public enum ResponseCode {

    SUCCESS(200,"SUCCESS"),
    ASY_SUCCESS(201,"异步响应成功"),
    ERROR(400,"请求出错"),
    AUTH_ERROR(401,"认证失败"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(20,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResponseCode(int code ,String desc){
        this.code=code;
        this.desc=desc;
    }
    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }


}

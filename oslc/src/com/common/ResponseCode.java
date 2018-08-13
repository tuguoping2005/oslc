package com.common;

/**
 * Created by tuguoping 
 */
public enum ResponseCode {

    SUCCESS(200,"请求成功"),
    CREATE_SUCCESS(201,"资源创建成功"),
    ERROR(400,"请求错误"),
    UN_RESPONSE(404,"服务器无法响应"),
    AUTH_ERROR(401,"认证失败"),
    NEED_LOGIN(10,"用户名或密码错误,请重新登录"),
    ILLEGAL_ARGUMENT(20,"参数不合法"),
    ILLEGAL_JSON(21,"json数据不合法"),	
    NULL_JSON(22,"JSON数据为空"),
    ILLEGAL_PERCENT(23,"摊销百分比总和不等于100");

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

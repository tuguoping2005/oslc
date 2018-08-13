package com.common;

/**
 * Created by tuguoping 
 */
public enum ResponseCode {

    SUCCESS(200,"����ɹ�"),
    CREATE_SUCCESS(201,"��Դ�����ɹ�"),
    ERROR(400,"�������"),
    UN_RESPONSE(404,"�������޷���Ӧ"),
    AUTH_ERROR(401,"��֤ʧ��"),
    NEED_LOGIN(10,"�û������������,�����µ�¼"),
    ILLEGAL_ARGUMENT(20,"�������Ϸ�"),
    ILLEGAL_JSON(21,"json���ݲ��Ϸ�"),	
    NULL_JSON(22,"JSON����Ϊ��"),
    ILLEGAL_PERCENT(23,"̯���ٷֱ��ܺͲ�����100");

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

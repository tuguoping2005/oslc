package com.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TririgaResponse implements Serializable{
	private String msg;
	private int code;
	@SuppressWarnings("unused")
	private TririgaResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	private TririgaResponse(String msg, int code) {
		super();
		this.msg = msg;
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
		
	
	public TririgaResponse(int code) {
		super();
		this.code = code;
	}
	public static TririgaResponse createBySuccess(){
        return new TririgaResponse(ResponseCode.SUCCESS.getCode());
    }
	public static TririgaResponse createBySuccessMessage(String msg){
        return new TririgaResponse(msg,ResponseCode.SUCCESS.getCode());
    }
	public static TririgaResponse createBySuccessCodeMessage(int code,String msg){
		return new TririgaResponse(msg,code);
	}

    public static TririgaResponse createByError(){
        return new TririgaResponse(ResponseCode.ERROR.getDesc(),ResponseCode.ERROR.getCode());
    }

    public static   TririgaResponse createByErrorMessage(String msg){
        return new TririgaResponse(msg,ResponseCode.ERROR.getCode());
    }

    public static  TririgaResponse createByErrorCodeMessage(int code, String msg){
        return new TririgaResponse(msg,code);
    }

	
}

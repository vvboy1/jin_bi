package com.example.demo.common;

/**
 * Created by sun
 **/
public enum  ResponseCode {
    SUCCESS(0,"SUCCESS"), //成功
    ERROR(1,"ERROR"),            //失败
    NEED_LOGIN(10,"NEED_LOGIN"),     //需要强制登录
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");  // 参数错误

    private final int code;
    private final  String desc;


    ResponseCode(int code ,String desc){
        this.code = code;
        this.desc = desc;
    }

    public  int getCode(){
        return code;
    }

    public  String getDesc(){
        return desc;
    }

}

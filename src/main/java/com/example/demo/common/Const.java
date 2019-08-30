package com.example.demo.common;

/**
 * Created by sun
 **/
public class Const {


    public static final String CURRENT_USER = "currentUser"; //学生session登陆属性

    public static final String CURRENT_ADMIN = "currentAdmin";//管理员session登陆属性

    public static final String EMAIL = "stuemail";   //用户email

    public static final String STUID = "stuid";  //学号

    public interface  Role{
        int ROLE_CUSTOMER = 1; //普通用户
        int ROLE_ADMIN = 2;  //管理员
        int ROLE_SADMIN = 3; //超级管理员
    }


    //用户状态
    public interface  UserStatus{
        int USER_START = 0; //可用状态
        int USER_LOCK = 1;  //锁定
        int USER_DEL = 2; //已删除状态
    }
}

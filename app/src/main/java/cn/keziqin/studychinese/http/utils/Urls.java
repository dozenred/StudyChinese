package cn.keziqin.studychinese.http.utils;

/**
 * Created by Dozen on 2017/11/13.
 */

public class Urls {
    public static final String SERVER = "http://39.104.78.225/chinese/interface/";
    public static final String SERVER1 = "http://39.104.78.225:8080/logistics/interface/";

    //登录
    public static final String URL_USER_LOGIN = SERVER + "user/login";
    //注册
    public static final String URL_USER_REGISTER = SERVER + "user/register";
    //查询等级
    public static final String URL_SELECT_LEVEL = SERVER + "user/updateLevel";
    //查询分组
    public static final String URL_SELECT_COURSE = SERVER + "group/queryGroup";
    //学习课程
    public static final String URL_COURSE_CONTENT = SERVER + "learnObject/queryLearnObject";
}

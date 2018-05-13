package cn.keziqin.studychinese.beans;

import android.content.Context;


import cn.keziqin.studychinese.appconfig.MyApplication;
import cn.keziqin.studychinese.utils.SharedPreferencesUtil;

/**
 * Created by Dozen on 2017/8/22.
 * 用户登录的实体类
 */

public class UserBean {
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USER_TOKEN = "token";
    public static final String KEY_IS_LOGIN = "isLogin";
    public static final String KEY_LEVEL = "level";
    private static final String COURSE_ID = "courseId";
    private static final String USER_ID = "userId";

    private String username;
    private String password;
    private int userId;
    private int level;
    private String course;
    //private String token;
    private boolean isLogin;

    private static UserBean instance;

    public static UserBean getInstance() {
        initUserInfo(MyApplication.getApplication());
        return instance;
    }

    public static void initUserInfo (Context context) {
        instance = new UserBean();
        instance.username = (String) SharedPreferencesUtil.getParam(context, KEY_USER_NAME, "");
        instance.password = (String) SharedPreferencesUtil.getParam(context, KEY_PASSWORD, "");
      //  instance.token = (String) SharedPreferencesUtil.getParam(context, KEY_USER_TOKEN, "");
        instance.isLogin = (Boolean) SharedPreferencesUtil.getParam(context, KEY_IS_LOGIN, false);
        instance.level = (Integer) SharedPreferencesUtil.getParam(context, KEY_LEVEL, 1-1);
        instance.userId = (Integer) SharedPreferencesUtil.getParam(context, USER_ID, -1);
    }

    public static void setUserInfo (Context context, UserBean userBean) {
        SharedPreferencesUtil.setParam(context, KEY_USER_NAME, userBean.getUsername());
        SharedPreferencesUtil.setParam(context, KEY_PASSWORD, userBean.getPassword());
        //SharedPreferencesUtil.setParam(context, KEY_USER_TOKEN, userBean.getToken());
        SharedPreferencesUtil.setParam(context, KEY_IS_LOGIN, userBean.isLogin());

    }

    public static void clearUserInfo (Context context) {
        SharedPreferencesUtil.setParam(context, KEY_USER_NAME, "");
        SharedPreferencesUtil.setParam(context, KEY_PASSWORD, "");
        //SharedPreferencesUtil.setParam(context, KEY_USER_TOKEN, "");
        SharedPreferencesUtil.setParam(context, KEY_IS_LOGIN, false);
        SharedPreferencesUtil.setParam(context, KEY_LEVEL, 1-1);
        SharedPreferencesUtil.setParam(context, USER_ID, -1);

    }

    public static void changeUserState (Context context) {
        //SharedPreferencesUtil.setParam(context, KEY_USER_TOKEN, "");
        SharedPreferencesUtil.setParam(context, KEY_IS_LOGIN, false);
    }

    public UserBean() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getToken() {
//        return token;
//    }

//    public void setToken(String token) {
//        this.token = token;
//    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setIslogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}

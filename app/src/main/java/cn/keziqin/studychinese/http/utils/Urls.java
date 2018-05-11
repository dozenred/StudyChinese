package cn.keziqin.studychinese.http.utils;

/**
 * Created by Dozen on 2017/11/13.
 */

public class Urls {
    public static final String SERVER = "http://39.104.78.225/logistics/interface/";
    public static final String SERVER1 = "http://39.104.78.225:8080/logistics/interface/";
    //司机接口网址
    public static final String URL_UNDELIVERY_ORDER = SERVER + "short_delivering_android/query_delivering_android";
    public static final String URL_DELIVERY_ORDER = SERVER + "short_delivered_android//query_delivered_android";
    public static final String URL_UNRECEIVE_ORDER = SERVER + "short_receiving_android/query_receiving_android";
    public static final String URL_RECEIVE_ORDER = SERVER + "short_received_android/query_received_android";

    public static final String URL_DELIVERY_ORDER_FAIL = SERVER + "short_delivering_android/update_delivering_fail";
    public static final String URL_DELIVERY_ORDER_SUCCESS = SERVER + "short_delivering_android/update_delivering_success";
    public static final String URL_RECEIVE_ORDER_FAIL = SERVER + "short_receiving_android/update_receiving_fail";
    public static final String URL_RECEIVE_ORDER_SUCCESS = SERVER + "short_receiving_android/update_receiving_success";

    public static final String URL_QUERY_DELIVER_MONEY = SERVER + "short_delivering_android/query_deliver_money";

    public static final String URL_UPDATE_RECEIVED_SUCCESS = SERVER + "short_received_android/update_received_success";

    public static final String URL_HISTORY_ORDER = SERVER + "user_android/query_history_android";

    public static final String URL_DRIVER_PERSONAL_CENTER = SERVER + "user_android/query_user_android";
    public static final String URL_DRIVER_STATE_UPDATE = SERVER + "user_android/update_car_state";



    //登录
    public static final String URL_USER_LOGIN = SERVER + "loginManagement/android_login";

}

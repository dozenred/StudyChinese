package cn.keziqin.studychinese.utils;


import android.util.Log;

import cn.keziqin.studychinese.ui.BuildConfig;


/**
 * 日志帮助类，所有要打印日志的地方必须调用此类来实现，不得调用系统类
 */
public class LogUtil {

    private static boolean LOGV = BuildConfig.DEBUG;
    private static boolean LOGD = BuildConfig.DEBUG;
    private static boolean LOGI = BuildConfig.DEBUG;
    private static boolean LOGW = BuildConfig.DEBUG;
    private static boolean LOGE = BuildConfig.DEBUG;

    public static void v(String tag, String mess) {
        if (LOGV) {
            Log.v(tag, mess);
        }
    }

    public static void d(String tag, String mess) {
        if (LOGD) {
            Log.d(tag, mess);
        }
    }

    public static void i(String tag, String mess) {
        if (LOGI) {
            Log.i(tag, mess);
        }
    }

    public static void w(String tag, String mess) {
        if (LOGW) {
            Log.w(tag, mess);
        }
    }

    public static void e(String tag, String mess) {
        if (LOGE) {
            Log.e(tag, mess);
        }
    }
}

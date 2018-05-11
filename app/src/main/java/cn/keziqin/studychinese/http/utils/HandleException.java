package cn.keziqin.studychinese.http.utils;

import android.content.Context;
import android.content.Intent;

import com.lzy.okgo.exception.HttpException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Set;

import cn.keziqin.studychinese.utils.LogUtil;
import cn.keziqin.studychinese.utils.ToastUtils;


/**
 * Created by Dozen on 2017/12/7.
 */

public class HandleException {
    public static void handleException(Throwable exception, String TAG, Context mContext) {
        if (exception != null)
            exception.printStackTrace();

        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            LogUtil.d(TAG, "网络异常，与服务器连接失败，请重新连接");
            ToastUtils.showToast(mContext, "网络连接失败，请重新连接");
        } else if (exception instanceof SocketTimeoutException) {
            LogUtil.d(TAG, "网络请求超时");
            ToastUtils.showToast(mContext, "网络请求超时");
        } else if (exception instanceof HttpException) {
            LogUtil.d(TAG, "返回码是404or500? 服务器异常，请稍后重试");
            ToastUtils.showToast(mContext, "服务器异常，请稍后重试");
        } else if (exception instanceof IllegalStateException) {
            //这个异常是自己抛出的，这里获取到的message是前面异常抛出类的数据
            String message = exception.getMessage();
            LogUtil.d(TAG, message);
            ToastUtils.showToast(mContext, message);

        }
    }
}

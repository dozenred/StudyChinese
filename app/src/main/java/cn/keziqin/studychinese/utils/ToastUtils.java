package cn.keziqin.studychinese.utils;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Toast帮助类，所有使用到Toast的地方都必须调用此类实现，不可调用系统方法。
 *
 * 解决了点击多次显示多次的问题！
 *
 * 先谈谈Toast的原理：
 *      先通过makeText()实例化出一个Toast，然后调用toast.Show()方法，
 *      这时并不会马上显示Toast，而是会实例化一个TN变量(源码可见)，然后通过service.enqueueToast()将其加到服务队列里面去等待显示。
 *      在TN中进行调控Toast的显示格式以及里面的hide()、show()方法来控制Toast的出现以及消失，这个队列是系统维护的，我们并不能干涉。
 */
public class ToastUtils {

    private static Toast mToast;

    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        @Override
        public void run() {
            mToast.cancel();
            mToast = null;// toast隐藏后，将其置为null
        }
    };

    /**
     * 展示Toast
     *
     * @param context context
     * @param message message
     */
    public static void showToast(Context context, String message) {
        mHandler.removeCallbacks(r);
        if (mToast == null) {// 只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        }
        mHandler.postDelayed(r, 1000);// 延迟1秒隐藏toast
        mToast.show();
    }

    /**
     * 展示Toast
     *
     * @param context context
     * @param resId   the string resource id
     */
    public static void showToast(Context context, @StringRes int resId) {
        showToast(context,context.getString(resId));
    }
}

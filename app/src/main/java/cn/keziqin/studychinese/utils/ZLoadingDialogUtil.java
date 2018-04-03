package cn.keziqin.studychinese.utils;

import android.content.Context;
import android.graphics.Color;

import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

/**
 * Created by dells on 2017/11/20.
 */

public class ZLoadingDialogUtil {
    private static ZLoadingDialog dialog;

    public static void loadDialog (Context context) {
        //加载动画
        dialog = new ZLoadingDialog(context);
        dialog.setLoadingBuilder(Z_TYPE.STAR_LOADING)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("Loading...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.GRAY)  // 设置字体颜色
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .show();
    }

    public static void dismissDialog () {
        dialog.dismiss();
    }
}

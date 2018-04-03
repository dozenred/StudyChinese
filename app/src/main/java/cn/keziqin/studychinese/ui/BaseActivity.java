package cn.keziqin.studychinese.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import cn.keziqin.studychinese.appconfig.MyApplication;
import cn.keziqin.studychinese.utils.LogUtil;
import cn.keziqin.studychinese.view.SystemBarTintManager;

/**
 * Created by dells on 2017/8/8.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    protected SystemBarTintManager tintManager;
    protected final String TAG = getClass().getSimpleName();
    protected Bundle savedInstanceState;

    abstract protected void getLayoutResource();

    abstract protected void initView();

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutResource();

        MyApplication.getApplication().addActivity(this);
        //getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        LogUtil.i(TAG, "onCreate");
        this.savedInstanceState = savedInstanceState;

        initView();
        initStatusBar();
    }

    public void jumpToActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    public void jumpToActivityForResult(Class<?> cls, int requestCode, Bundle extras) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.putExtras(extras);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    public void jumpToActivity(Class<?> c) {
        Intent intent = new Intent();
        intent.setClass(this, c);
        this.startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    public void jumpToActivity(Class<?> c, Bundle extras) {
        Intent intent = new Intent();
        intent.setClass(this, c);
        intent.putExtras(extras);
        this.startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    protected void jumpToActivity(Class<?> c, View sharedElement, String shareElementName) {
        Intent intent = new Intent();
        intent.setClass(this, c);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, sharedElement, shareElementName);
        ActivityCompat.startActivity(
                this, intent, optionsCompat.toBundle()
        );
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);

        /**
         * 在目标Activity中执行此操作
         */
        //ViewCompat.setTransitionName(sharedElement, shareElementName);
    }

    protected void jumpToActivity(Class<?> c, Bundle extras, View sharedElement, String shareElementName) {
        Intent intent = new Intent();
        intent.setClass(this, c);
        intent.putExtras(extras);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, sharedElement, shareElementName);
        ActivityCompat.startActivity(
                this, intent, optionsCompat.toBundle());
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    protected Bundle getBundle() {
        return this.getIntent().getExtras();
    }


    protected String getTag() {
        return TAG;
    }

    /**
     * 查找view
     */
    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    protected void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
            }
        });
    }

    /**
     * 设置状态栏
     */
    protected void initStatusBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.colorPrimaryDark2);
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy");
        MyApplication.getApplication().removeActivity(this);
        //AppData.getApplication().getRefWatcher().watch(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);

    }


}
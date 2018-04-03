package cn.keziqin.studychinese.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.keziqin.studychinese.utils.LogUtil;


abstract public class BaseFragment extends Fragment {
    protected final String TAG = getClass().getSimpleName();
    protected View mRootView;
    protected BaseActivity mContext;
    protected Toolbar toolbar;
    //是否已经加载过数据
    protected boolean isLoadData = false;
    //控件是否已经初始化
    protected boolean isCreateView = false;
    private boolean isVisible = false;

    //这种方式建立的Activity，在finish掉后，后向启动它的activity返回requestCode
    //同时启动它的Activity在onActivityResult()方法中resultCode接收该参数
    public void jumpToActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), cls);
        startActivityForResult(intent, requestCode);
    }

    public void jumpToActivityForResult(Class<?> cls, int requestCode, Bundle extras) {
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), cls);
        intent.putExtras(extras);
        startActivityForResult(intent, requestCode);
    }

    protected void jumpToActivity(Class c) {
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), c);
        startActivity(intent);
    }

    protected void jumpToActivity(Class c, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), c);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void initToolbar() {
        toolbar.setNavigationIcon(null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 此方法在控件初始化前调用，所以不能在此方法中直接操作控件会出现空指针
     * 首次进入不会执行if中的语句，isCreateView为false，这个方法在onActivityCreated()之前调用
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isVisible && isCreateView) {
            lazyLoad();
        }
    }

    protected void lazyLoad() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG, "onActivityCreated");
        isCreateView = true;
        //这个判断如果不写，则进入activity后显示的第一个fragment不会显示数据
        if (isVisible) {
            lazyLoad();

        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isCreateView = false;
        LogUtil.i(TAG, "onDestroy");

        /*RefWatcher refWatcher = AppData.getApplication().getRefWatcher();
        refWatcher.watch(this);*/
    }

}

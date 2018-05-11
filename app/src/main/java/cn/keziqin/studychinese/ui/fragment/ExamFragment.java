package cn.keziqin.studychinese.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.keziqin.studychinese.beans.ExamMessageEvent;
import cn.keziqin.studychinese.ui.BaseFragment;
import cn.keziqin.studychinese.ui.R;
import cn.keziqin.studychinese.ui.databinding.FragmentExamBinding;
import cn.keziqin.studychinese.utils.ToastUtils;

public class ExamFragment extends BaseFragment {
    private FragmentExamBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注册订阅者
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ExamMessageEvent messageEvent) {
//        mText.setText(messageEvent.getMessage());
        ToastUtils.showToast(this.getActivity(), messageEvent.getMessage());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_exam, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mBinding.examTest1.setText(Html.fromHtml("一道一道<font color='#ff0000'>选择题</font>还没做"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "destroy");
        EventBus.getDefault().unregister(this);
    }
}

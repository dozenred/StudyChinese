package cn.keziqin.studychinese.ui.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.keziqin.studychinese.beans.UserBean;
import cn.keziqin.studychinese.ui.BaseFragment;
import cn.keziqin.studychinese.ui.R;
import cn.keziqin.studychinese.ui.activity.LoginActivity;
import cn.keziqin.studychinese.ui.activity.SelectLevelActivity;
import cn.keziqin.studychinese.ui.databinding.FragmentMyBinding;
import cn.keziqin.studychinese.utils.SharedPreferencesUtil;
import cn.keziqin.studychinese.utils.ToastUtils;

public class MyFragment extends BaseFragment {
    private FragmentMyBinding mBinding;
    public static final String KEY_IS_LOGIN = "isLogin";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(view.getContext(), "LogOut!");
                SharedPreferencesUtil.setParam(view.getContext(), KEY_IS_LOGIN, false);
                UserBean.clearUserInfo(view.getContext());
                Intent intent = new Intent(view.getContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        mBinding.btnMySelectLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToastUtils.showToast(view.getContext(), "LogOut!");
                jumpToActivity(SelectLevelActivity.class);
            }
        });
    }
}

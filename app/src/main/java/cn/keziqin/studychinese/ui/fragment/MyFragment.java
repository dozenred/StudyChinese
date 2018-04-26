package cn.keziqin.studychinese.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.keziqin.studychinese.ui.BaseFragment;
import cn.keziqin.studychinese.ui.R;
import cn.keziqin.studychinese.ui.databinding.FragmentMyBinding;

public class MyFragment extends BaseFragment {
    private FragmentMyBinding mBinding;

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
    }
}

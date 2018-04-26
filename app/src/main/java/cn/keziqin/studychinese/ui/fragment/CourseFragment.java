package cn.keziqin.studychinese.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import cn.keziqin.studychinese.ui.BaseFragment;
import cn.keziqin.studychinese.ui.R;
import cn.keziqin.studychinese.ui.databinding.FragmentCourseBinding;

public class CourseFragment extends BaseFragment {
    private FragmentCourseBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_course, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        String url = "http://img.soogif.com/N31KglHjux1DB7b3ko7Jok52gjh1dUA6.gif";
        Glide.with(this).load(url).into(mBinding.ivGifBishun);
    }
}

package cn.keziqin.studychinese.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import cn.keziqin.studychinese.ui.BaseActivity;
import cn.keziqin.studychinese.ui.MainActivity;
import cn.keziqin.studychinese.ui.R;
import cn.keziqin.studychinese.ui.databinding.ActivitySelectLevelBinding;
import cn.keziqin.studychinese.utils.ToastUtils;

public class SelectLevelActivity extends BaseActivity {
    private ActivitySelectLevelBinding mBinding;
    private int level;

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_level);
    }

    @Override
    protected void initView() {
        initToolbar();
        selectLevel();
    }

    private void selectLevel() {
        mBinding.btnCommitLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected = mBinding.rgSelect.getCheckedRadioButtonId();
                switch (selected) {
                    case R.id.rb_level1:
                        level = 1;
                        break;
                    case R.id.rb_level2:
                        level = 2;
                        break;
                    case R.id.rb_level3:
                        level = 3;
                        break;
                    default:
                        break;
                }
                ToastUtils.showToast(SelectLevelActivity.this, "select level " + level);
                jumpToActivity(MainActivity.class);
                finish();
            }
        });
    }

    @Override
    protected void initToolbar() {
        toolbar = mBinding.selectToolbarToolbar.toolbar;
        toolbar.setTitle("");
        mBinding.selectToolbarToolbar.toolbarText.setText("select level");
        super.initToolbar();
    }
}

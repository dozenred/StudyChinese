package cn.keziqin.studychinese.ui.activity;

import android.databinding.DataBindingUtil;
import android.view.View;


import cn.keziqin.studychinese.ui.BaseActivity;
import cn.keziqin.studychinese.ui.R;
import cn.keziqin.studychinese.ui.databinding.ActivityRegisterBinding;
import cn.keziqin.studychinese.utils.ToastUtils;

public class RegisterActivity extends BaseActivity {
    private ActivityRegisterBinding mBinding;
    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
    }

    @Override
    protected void initView() {
        initToolbar();
        initListener();

    }

    private void initListener() {
        mBinding.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBinding.edtRegUsername.getText().toString().isEmpty()) {
                    ToastUtils.showToast(RegisterActivity.this, R.string.username_not_input_tip);
                } else if (mBinding.edtRegPassword.getText().toString().isEmpty()) {
                    ToastUtils.showToast(RegisterActivity.this, R.string.password_not_input_tip);
                } else if (mBinding.edtRegRepPassword.getText().toString().isEmpty()) {
                    ToastUtils.showToast(RegisterActivity.this, R.string.password_not_input_tip);
                } else if (!mBinding.edtRegPassword.getText().toString().equals(mBinding.edtRegRepPassword.getText().toString())) {
                    ToastUtils.showToast(RegisterActivity.this, "两次输入的密码不一致");
                } else {
                    execRegister();
                }
            }
        });

    }

    //注册账号
    private void execRegister() {
        ToastUtils.showToast(RegisterActivity.this, "注册");
    }

    @Override
    protected void initToolbar() {
        toolbar = mBinding.registerToolbar.toolbar;
        toolbar.setTitle("");
        mBinding.registerToolbar.toolbarText.setText("注册");
        super.initToolbar();
    }
}

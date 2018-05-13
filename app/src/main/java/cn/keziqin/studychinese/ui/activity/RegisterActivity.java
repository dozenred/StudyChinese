package cn.keziqin.studychinese.ui.activity;

import android.databinding.DataBindingUtil;
import android.view.View;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.keziqin.studychinese.beans.UserBean;
import cn.keziqin.studychinese.http.beans.ServerResult;
import cn.keziqin.studychinese.http.callback.JsonCallback;
import cn.keziqin.studychinese.http.utils.HandleException;
import cn.keziqin.studychinese.http.utils.Urls;
import cn.keziqin.studychinese.ui.BaseActivity;
import cn.keziqin.studychinese.ui.R;
import cn.keziqin.studychinese.ui.databinding.ActivityRegisterBinding;
import cn.keziqin.studychinese.utils.ToastUtils;
import cn.keziqin.studychinese.utils.ZLoadingDialogUtil;

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
                    ToastUtils.showToast(RegisterActivity.this, "Entered passwords differ, please reenter it");
                } else {
                    execRegister();
                }
            }
        });

    }

    //注册账号
    private void execRegister() {
        ZLoadingDialogUtil.loadDialog(RegisterActivity.this);
        Map<String, String> params = new HashMap<>();
        params.put("username", mBinding.edtRegUsername.getText().toString());
        params.put("password", mBinding.edtRegPassword.getText().toString());
        JSONObject json = new JSONObject(params);
        OkGo.<ServerResult<String>>post(Urls.URL_USER_REGISTER)
                .tag(this)
                .upJson(json)
                .execute(new JsonCallback<ServerResult<String>>() {
                    @Override
                    public void onSuccess(Response<ServerResult<String>> response) {
                        ZLoadingDialogUtil.dismissDialog();
                        jumpToActivity(LoginActivity.class);
                    }
                    @Override
                    public void onError(Response<ServerResult<String>> response) {
                        super.onError(response);
                        ZLoadingDialogUtil.dismissDialog();
                        Throwable exception = response.getException();
                        if (exception != null)
                            exception.printStackTrace();

                        //处理其他异常
                        HandleException.handleException(exception, TAG, RegisterActivity.this);
                    }
                });
        //ToastUtils.showToast(RegisterActivity.this, "注册");
    }

    @Override
    protected void initToolbar() {
        toolbar = mBinding.registerToolbar.toolbar;
        toolbar.setTitle("");
        mBinding.registerToolbar.toolbarText.setText("Register");
        super.initToolbar();
    }
}

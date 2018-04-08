package cn.keziqin.studychinese.ui.activity;



import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.keziqin.studychinese.beans.UserBean;
import cn.keziqin.studychinese.ui.BaseActivity;
import cn.keziqin.studychinese.ui.MainActivity;

import cn.keziqin.studychinese.ui.R;
import cn.keziqin.studychinese.ui.databinding.ActivityLoginBinding;
import cn.keziqin.studychinese.utils.SharedPreferencesUtil;
import cn.keziqin.studychinese.utils.ToastUtils;


public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding mBinding;
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USER_TOKEN = "token";
    public static final String KEY_IS_LOGIN = "isLogin";

    private boolean isRemember;
    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    @Override
    protected void initView() {
        if ((Boolean) SharedPreferencesUtil.getParam(this, KEY_IS_LOGIN, false)) {
                jumpToActivity(MainActivity.class);
                //finish();
        }
        initToolbar();
        Glide.with(this).load(R.drawable.login_background).into(mBinding.loginBackgroundImageView);
        isRemember = (Boolean) SharedPreferencesUtil.getParam(this, "isRemember", false);
        if (isRemember) {
            mBinding.loginUsernameEdt.setText(UserBean.getInstance().getUsername());
            mBinding.loginPasswordEdt.setText(UserBean.getInstance().getPassword());
        }
        initListener();
        jumpToRegister();
    }

    private void jumpToRegister() {
        mBinding.tvReg.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                jumpToActivity(RegisterActivity.class);
            }
        });
    }

    @Override
    protected void initToolbar() {
        toolbar = mBinding.loginToolbar.toolbar;
        toolbar.setTitle("");
        mBinding.loginToolbar.toolbarText.setText("登录");
        toolbar.setNavigationIcon(null);
        super.initToolbar();
    }

    private void initListener() {
        mBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.loginUsernameEdt.getText().toString().isEmpty()) {
                    ToastUtils.showToast(LoginActivity.this, R.string.username_not_input_tip);
                } else if (mBinding.loginPasswordEdt.getText().toString().isEmpty()) {
                    ToastUtils.showToast(LoginActivity.this, R.string.password_not_input_tip);
                } else {
                    execLoginTask();

                }
            }
        });
    }

    //发起登录请求
    private void execLoginTask(){
        if (mBinding.loginRememberPasswordCb.isChecked()) {
            SharedPreferencesUtil.setParam(LoginActivity.this, "isRemember", true);
        } else {
            SharedPreferencesUtil.setParam(LoginActivity.this, "isRemember", false);
        }
        SharedPreferencesUtil.setParam(LoginActivity.this, KEY_USER_NAME, mBinding.loginUsernameEdt.getText().toString());
        SharedPreferencesUtil.setParam(LoginActivity.this, KEY_PASSWORD, mBinding.loginPasswordEdt.getText().toString());
        SharedPreferencesUtil.setParam(LoginActivity.this, KEY_USER_TOKEN, "aaa");
        SharedPreferencesUtil.setParam(LoginActivity.this, KEY_IS_LOGIN, true);
        jumpToActivity(MainActivity.class);
        //finish();


    }

    private boolean mIsExit = false;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();

            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}


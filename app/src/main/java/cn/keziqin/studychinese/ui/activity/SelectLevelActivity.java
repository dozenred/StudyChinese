package cn.keziqin.studychinese.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.security.acl.Group;
import java.util.HashMap;
import java.util.Map;

import cn.keziqin.studychinese.beans.GroupBean;
import cn.keziqin.studychinese.beans.GroupBeanList;
import cn.keziqin.studychinese.beans.UserBean;
import cn.keziqin.studychinese.http.beans.ServerResult;
import cn.keziqin.studychinese.http.callback.JsonCallback;
import cn.keziqin.studychinese.http.utils.HandleException;
import cn.keziqin.studychinese.http.utils.Urls;
import cn.keziqin.studychinese.ui.BaseActivity;
import cn.keziqin.studychinese.ui.MainActivity;
import cn.keziqin.studychinese.ui.R;
import cn.keziqin.studychinese.ui.databinding.ActivitySelectLevelBinding;
import cn.keziqin.studychinese.utils.SharedPreferencesUtil;
import cn.keziqin.studychinese.utils.ToastUtils;
import cn.keziqin.studychinese.utils.ZLoadingDialogUtil;

public class SelectLevelActivity extends BaseActivity {
    private ActivitySelectLevelBinding mBinding;
    private int level;
    private AlertDialog dialog;
    private String items[];
    private static final String COURSE_ID = "courseId";

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_level);
    }

    @Override
    protected void initView() {
        initToolbar();
        selectLevel();

    }

    private void getLevelCourse() {
        ZLoadingDialogUtil.loadDialog(SelectLevelActivity.this);
        Map<String, Object> params = new HashMap<>();
        params.put("userId", UserBean.getInstance().getUserId());
        if (mBinding.rgSelect.getCheckedRadioButtonId() == R.id.rb_level1) level = 1;
        if (mBinding.rgSelect.getCheckedRadioButtonId() == R.id.rb_level2) level = 2;
        if (mBinding.rgSelect.getCheckedRadioButtonId() == R.id.rb_level3) level = 3;
        params.put("level", level);
        JSONObject json = new JSONObject(params);
        OkGo.<ServerResult<String>>post(Urls.URL_SELECT_LEVEL)
                .tag(this)
                .upJson(json)
                .execute(new JsonCallback<ServerResult<String>>() {
                    @Override
                    public void onSuccess(Response<ServerResult<String>> response) {
                        ZLoadingDialogUtil.dismissDialog();
                        Map<String, Object> params = new HashMap<>();
                        params.put("userId", UserBean.getInstance().getUserId());
                        JSONObject json = new JSONObject(params);
                        OkGo.<ServerResult<GroupBeanList>>post(Urls.URL_SELECT_COURSE)
                                .tag(this)
                                .upJson(json)
                                .execute(new JsonCallback<ServerResult<GroupBeanList>>() {
                                    @Override
                                    public void onSuccess(final Response<ServerResult<GroupBeanList>> response) {
                                        items = new String[response.body().getContent().getGroupList().size()];
                                        for(int i = 0; i < response.body().getContent().getGroupList().size(); i++) {
                                            items[i] = response.body().getContent().getGroupList().get(i).getGroupContent();
                                        }
                                        switch (level) {
                                            case 1:
                                                level = 1;

                                                dialog = new AlertDialog.Builder(SelectLevelActivity.this)
                                                        .setIcon(R.mipmap.ic_launcher)//设置标题的图片
                                                        .setTitle("select course")//设置对话框的标题
                                                        .setItems(items, new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                //Toast.makeText(SelectLevelActivity.this, items[which], Toast.LENGTH_SHORT).show();
                                                                UserBean.getInstance().setCourse(items[which]);
                                                                //GroupBean.getInstance().setGroupId(response.body().getContent().getGroupList().get(which).getGroupId());
                                                                SharedPreferencesUtil.setParam(SelectLevelActivity.this, COURSE_ID, response.body().getContent().getGroupList().get(which).getGroupId());
                                                                jumpToActivity(MainActivity.class);
                                                                finish();
                                                            }
                                                        })
                                                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                            }
                                                        }).create();
                                                dialog.show();
                                                break;
                                            case 2:
                                                level = 2;
                                                //items = new String[]{level + "-1", level + "-2", level + "-3", level + "-4"};
                                                dialog = new AlertDialog.Builder(SelectLevelActivity.this)
                                                        .setIcon(R.mipmap.ic_launcher)//设置标题的图片
                                                        .setTitle("select course")//设置对话框的标题
                                                        .setItems(items, new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                //Toast.makeText(SelectLevelActivity.this, items[which], Toast.LENGTH_SHORT).show();
                                                                UserBean.getInstance().setCourse(items[which]);
                                                                //GroupBean.getInstance().setGroupId(response.body().getContent().getGroupList().get(which).getGroupId());
                                                                SharedPreferencesUtil.setParam(SelectLevelActivity.this, COURSE_ID, response.body().getContent().getGroupList().get(which).getGroupId());
                                                                jumpToActivity(MainActivity.class);
                                                                finish();
                                                            }
                                                        })
                                                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                            }
                                                        }).create();
                                                dialog.show();
                                                break;
                                            case 3:
                                                level = 3;
                                                // items = new String[]{level + "-1", level + "-2", level + "-3", level + "-4"};
                                                dialog = new AlertDialog.Builder(SelectLevelActivity.this)
                                                        .setIcon(R.mipmap.ic_launcher)//设置标题的图片
                                                        .setTitle("select course")//设置对话框的标题
                                                        .setItems(items, new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                //Toast.makeText(SelectLevelActivity.this, items[which], Toast.LENGTH_SHORT).show();
                                                                UserBean.getInstance().setCourse(items[which]);
                                                                //GroupBean.getInstance().setGroupId(response.body().getContent().getGroupList().get(which).getGroupId());
                                                                SharedPreferencesUtil.setParam(SelectLevelActivity.this, COURSE_ID, response.body().getContent().getGroupList().get(which).getGroupId());
                                                                jumpToActivity(MainActivity.class);
                                                                finish();
                                                            }
                                                        })
                                                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                            }
                                                        }).create();
                                                dialog.show();
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    @Override
                                    public void onError(Response<ServerResult<GroupBeanList>> response) {
                                        super.onError(response);
                                        //ZLoadingDialogUtil.dismissDialog();
                                        Throwable exception = response.getException();
                                        if (exception != null)
                                            exception.printStackTrace();

                                        //处理其他异常
                                        HandleException.handleException(exception, TAG, SelectLevelActivity.this);

                                    }
                                });
                    }
                    @Override
                    public void onError(Response<ServerResult<String>> response) {
                        super.onError(response);
                        ZLoadingDialogUtil.dismissDialog();
                        Throwable exception = response.getException();
                        if (exception != null)
                            exception.printStackTrace();

                        //处理其他异常
                        HandleException.handleException(exception, TAG, SelectLevelActivity.this);

                    }
                });
    }

    private void selectLevel() {
        mBinding.btnCommitLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int selected = mBinding.rgSelect.getCheckedRadioButtonId();
                getLevelCourse();


                //ToastUtils.showToast(SelectLevelActivity.this, "select level " + level);
                //jumpToActivity(MainActivity.class);
                //finish();
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

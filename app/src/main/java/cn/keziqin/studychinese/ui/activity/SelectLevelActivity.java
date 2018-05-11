package cn.keziqin.studychinese.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cn.keziqin.studychinese.ui.BaseActivity;
import cn.keziqin.studychinese.ui.MainActivity;
import cn.keziqin.studychinese.ui.R;
import cn.keziqin.studychinese.ui.databinding.ActivitySelectLevelBinding;
import cn.keziqin.studychinese.utils.ToastUtils;

public class SelectLevelActivity extends BaseActivity {
    private ActivitySelectLevelBinding mBinding;
    private int level;
    private AlertDialog dialog;
    private String items[];

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
                        items = new String[]{level + "-1", level + "-2", level + "-3", level + "-4"};
                        dialog = new AlertDialog.Builder(SelectLevelActivity.this)
                                .setIcon(R.mipmap.ic_launcher)//设置标题的图片
                                .setTitle("select course")//设置对话框的标题
                                .setItems(items, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(SelectLevelActivity.this, items[which], Toast.LENGTH_SHORT).show();
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
                    case R.id.rb_level2:
                        level = 2;
                        items = new String[]{level + "-1", level + "-2", level + "-3", level + "-4"};
                        dialog = new AlertDialog.Builder(SelectLevelActivity.this)
                                .setIcon(R.mipmap.ic_launcher)//设置标题的图片
                                .setTitle("select course")//设置对话框的标题
                                .setItems(items, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(SelectLevelActivity.this, items[which], Toast.LENGTH_SHORT).show();
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
                    case R.id.rb_level3:
                        level = 3;
                        items = new String[]{level + "-1", level + "-2", level + "-3", level + "-4"};
                        dialog = new AlertDialog.Builder(SelectLevelActivity.this)
                                .setIcon(R.mipmap.ic_launcher)//设置标题的图片
                                .setTitle("select course")//设置对话框的标题
                                .setItems(items, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(SelectLevelActivity.this, items[which], Toast.LENGTH_SHORT).show();
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

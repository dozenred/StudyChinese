package cn.keziqin.studychinese.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import cn.keziqin.studychinese.ui.R;

/**
 * Created by Dozen on 2017/8/30.
 */

public class CustomAlertDialog extends Dialog {

    protected View mView;

    //ui
    protected TextView mTextTitle,mTextContent,mTextCancel,mTextSure;

    public CustomAlertDialog(Context context) {
        this(context,0);
    }

    public CustomAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
        mView = LayoutInflater.from(context).inflate(R.layout.view_custom_alert_dialog,null);
        setContentView(mView);
        initView();
        //setting size
        Window dialogWindow = this.getWindow();
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        //手机横竖屏时候
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            p.height = (int) (d.getHeight() * 0.35); // 高度设置为屏幕的 0.25
            p.width = (int) (d.getWidth() * 0.7); // 宽度设置为屏幕的 0.7
        }else if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            p.height = (int) (d.getHeight() * 0.4); // 高度设置为屏幕的 0.3
            p.width = (int) (d.getWidth() * 0.4); // 宽度设置为屏幕的 0.4
        }
        dialogWindow.setAttributes(p);
    }

    private void initView() {
        try{
            mTextTitle = (TextView) mView.findViewById(R.id.view_custom_alter_dialog_title);
            mTextContent = (TextView) mView.findViewById(R.id.view_custom_alter_dialog_content);
            mTextCancel = (TextView) mView.findViewById(R.id.view_custom_alter_dialog_cancel);
            mTextSure = (TextView) mView.findViewById(R.id.view_custom_alter_dialog_sure);
        }catch(Exception _e){
            _e.printStackTrace();
        }
    }


    public void setText(String _title,String _content,String _cancel,String _sure){
        try{
            if (null != _title)
                mTextTitle.setText(_title);
            if (null != _content)
                mTextContent.setText(_content);
            if (null != _cancel)
                mTextCancel.setText(_cancel);
            if (null != _sure)
                mTextSure.setText(_sure);
        }catch(Exception _e){
            _e.printStackTrace();
        }
    }

    public void setLeftOnclick(View.OnClickListener _onclick){
        try{
            mTextCancel.setOnClickListener(_onclick);
        }catch(Exception _e){
            _e.printStackTrace();
        }
    }

    public void setRightOnclick(View.OnClickListener _onclick){
        try{
            mTextSure.setOnClickListener(_onclick);
        }catch(Exception _e){
            _e.printStackTrace();
        }
    }

}
package cn.keziqin.studychinese.ui;



import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.keziqin.studychinese.ui.databinding.ActivityMainBinding;
import cn.keziqin.studychinese.ui.fragment.CourseFragment;
import cn.keziqin.studychinese.ui.fragment.ExamFragment;
import cn.keziqin.studychinese.ui.fragment.MyFragment;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

    }

    @Override
    protected void initToolbar() {
        toolbar = mBinding.mainToolbar.toolbar;
        toolbar.setTitle("");
        mBinding.mainToolbar.toolbarText.setText("教程");
        toolbar.setNavigationIcon(null);
        super.initToolbar();
    }

    @Override
    protected void initView() {
        initToolbar();
        initMainFragment();
        changeNavBar();

    }

    private void initMainFragment() {
        selectCourseBg(true);
        selectExamBg(false);
        selectMyBg(false);
        replaceFragment(0);
        mBinding.mainToolbar.toolbarText.setText("教程");
    }

    private void changeNavBar() {
        mBinding.layoutNavCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectCourseBg(true);
                selectExamBg(false);
                selectMyBg(false);
                replaceFragment(0);
                mBinding.mainToolbar.toolbarText.setText("教程");
            }
        });

        mBinding.layoutNavExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCourseBg(false);
                selectExamBg(true);
                selectMyBg(false);
                replaceFragment(1);
                mBinding.mainToolbar.toolbarText.setText("考试");
            }
        });

        mBinding.layoutNavPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCourseBg(false);
                selectExamBg(false);
                selectMyBg(true);
                replaceFragment(2);
                mBinding.mainToolbar.toolbarText.setText("我的");
            }
        });

    }

    private void replaceFragment(int index) {
        transaction = fragmentManager.beginTransaction();
        switch (index) {
            case 0:
                CourseFragment courseFragment = new CourseFragment();
                transaction.replace(R.id.main_fragment, courseFragment);
//                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 1:
                ExamFragment examFragment = new ExamFragment();
                transaction.replace(R.id.main_fragment, examFragment);
//                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 2:
                MyFragment myFragment = new MyFragment();
                transaction.replace(R.id.main_fragment, myFragment);
//                transaction.addToBackStack(null);
                transaction.commit();
                break;
            default:
                break;

        }
    }

    private void selectCourseBg(Boolean select) {
        if (select) {
            mBinding.ivCourse.setImageResource(R.drawable.course_select);
            mBinding.tvCourse.setTextColor(getResources().getColor(R.color.colorAccent1));
        } else {
            mBinding.ivCourse.setImageResource(R.drawable.course);
            mBinding.tvCourse.setTextColor(getResources().getColor(R.color.gray));
        }
    }

    private void selectExamBg(Boolean select) {
        if (select) {
            mBinding.ivExam.setImageResource(R.drawable.exam_select);
            mBinding.tvExam.setTextColor(getResources().getColor(R.color.colorAccent1));
        } else  {
            mBinding.ivExam.setImageResource(R.drawable.exam);
            mBinding.tvExam.setTextColor(getResources().getColor(R.color.gray));
        }
    }

    private void selectMyBg(Boolean select) {
        if (select) {
            mBinding.ivMy.setImageResource(R.drawable.person_select);
            mBinding.tvMy.setTextColor(getResources().getColor(R.color.colorAccent1));
        } else {
            mBinding.ivMy.setImageResource(R.drawable.png_my);
            mBinding.tvMy.setTextColor(getResources().getColor(R.color.gray));
        }
    }



}

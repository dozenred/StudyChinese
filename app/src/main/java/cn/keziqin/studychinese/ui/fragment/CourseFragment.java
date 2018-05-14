package cn.keziqin.studychinese.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import cn.keziqin.studychinese.appconfig.MyApplication;
import cn.keziqin.studychinese.beans.CourseContent;
import cn.keziqin.studychinese.beans.CourseMessageEvent;
import cn.keziqin.studychinese.beans.GroupBean;
import cn.keziqin.studychinese.beans.GroupBeanList;
import cn.keziqin.studychinese.beans.SourceBean;
import cn.keziqin.studychinese.beans.UserBean;
import cn.keziqin.studychinese.http.beans.ServerResult;
import cn.keziqin.studychinese.http.callback.JsonCallback;
import cn.keziqin.studychinese.http.utils.HandleException;
import cn.keziqin.studychinese.http.utils.Urls;
import cn.keziqin.studychinese.ui.BaseFragment;
import cn.keziqin.studychinese.ui.R;
import cn.keziqin.studychinese.ui.activity.SelectLevelActivity;
import cn.keziqin.studychinese.ui.databinding.FragmentCourseBinding;
import cn.keziqin.studychinese.utils.ConvertScreenUnitUtil;
import cn.keziqin.studychinese.utils.SharedPreferencesUtil;
import cn.keziqin.studychinese.utils.ToastUtils;
import cn.keziqin.studychinese.utils.ZLoadingDialogUtil;

import android.view.ViewGroup.*;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseFragment extends BaseFragment {
    private FragmentCourseBinding mBinding;
    private int currentCount = 0;
    private int maxCount;
    private static final String COURSE_ID = "courseId";
    private String subject;
    private ArrayList<CourseContent> courseContents = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注册订阅者
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(CourseMessageEvent messageEvent) {
//        mText.setText(messageEvent.getMessage());
        //ToastUtils.showToast(this.getActivity(), messageEvent.getMessage());
        if (messageEvent.getMessage().equals("学习内容下一个")) {
            if (currentCount == maxCount - 1) {
                ToastUtils.showToast(this.getContext(), "This group of courses has been completed~~~");
            } else {
                currentCount++;
                fillingContent(currentCount, courseContents);
            }
        } else if (messageEvent.getMessage().equals("学习内容上一个")) {
            if (currentCount == 0) {
                ToastUtils.showToast(this.getContext(), "This is the first one~~~");
            } else {
                currentCount--;
                fillingContent(currentCount, courseContents);
            }
        }
    }

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
        Glide.with(this).load(R.drawable.zhou).into(mBinding.ivGifBishun);
        //setContent();
        setContentFromHttp();
        startSpeaking();
//        String url = "http://img.soogif.com/N31KglHjux1DB7b3ko7Jok52gjh1dUA6.gif";
//        Glide.with(this).load(url).into(mBinding.ivGifBishun);
//        mBinding.ivSound1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String strTextToSpeech = "科大讯飞，让世界聆听我们的声音aaaaaaaaaaaaaaaaaaaaaaaaaaaaaf解放路看电视剧法律的撒娇了疯狂的撒娇了开发商大结局施蒂利克放假了sad甲方凉快圣诞节疯狂了的萨副经理开始大家分开了的设计费凉快圣诞节福利速度快解放快乐圣诞节疯狂了时代峻峰福建省多了几分圣诞快乐甲方圣诞快乐副经理开始的减肥快乐是打飞机圣诞快乐荆防颗粒时代峻峰圣诞快乐附件福建省对啦开发加速度快乐积分卡拉时代峻峰";
//                MyApplication.getmSynthesizer().startSpeaking(strTextToSpeech, listener);
//
//            }
//        });
//        mBinding.ivSound2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String strTextToSpeech = "hello is me";
//                MyApplication.getmSynthesizer().startSpeaking(strTextToSpeech, listener);
//
//            }
//        });
    }

    private void setContentFromHttp() {
        ZLoadingDialogUtil.loadDialog(this.getContext());
        Map<String, Object> params = new HashMap<>();
        //SharedPreferencesUtil.getParam(this.getContext(), COURSE_ID, 0)
        params.put("groupId", 1);
        JSONObject json = new JSONObject(params);
        OkGo.<ServerResult<ArrayList<CourseContent>>>post(Urls.URL_COURSE_CONTENT)
                .tag(this)
                .upJson(json)
                .execute(new JsonCallback<ServerResult<ArrayList<CourseContent>>>() {
                    @Override
                    public void onSuccess(Response<ServerResult<ArrayList<CourseContent>>> response) {
                        ZLoadingDialogUtil.dismissDialog();

                        currentCount = 0;
                        maxCount = response.body().getContent().size();
                        courseContents = response.body().getContent();
                        fillingContent(currentCount, response.body().getContent());
                        //mBinding.setCourse(response.body().getContent().get(2));
                    }
                    public void onError(Response<ServerResult<ArrayList<CourseContent>>> response) {
                        super.onError(response);
                        ZLoadingDialogUtil.dismissDialog();
                        Throwable exception = response.getException();
                        if (exception != null)
                            exception.printStackTrace();

                        //处理其他异常
                        HandleException.handleException(exception, TAG, CourseFragment.this.getContext());

                    }
                });
    }
    //用来动态修改课程里的内容
    private void fillingContent(int count, ArrayList<CourseContent> content) {
        if (content.get(count).getType() == 1) {
            mBinding.courseLayout1.setVisibility(View.VISIBLE);
            mBinding.courseLayout2.setVisibility(View.VISIBLE);
            mBinding.courseLayout3.setVisibility(View.VISIBLE);
            mBinding.courseLayout4.setVisibility(View.VISIBLE);

            mBinding.courseLayout5.setVisibility(View.GONE);
            mBinding.courseLayout6.setVisibility(View.GONE);

            mBinding.ivZiStructure.setVisibility(View.VISIBLE);
            mBinding.tvCourseJuzi.setVisibility(View.GONE);
            //显示字词结构
            Glide.with(this).load(content.get(count).getStrcutImg()).into(mBinding.ivZiStructure);
            //显示笔顺
            mBinding.ivGifBishun.setVisibility(View.VISIBLE);
            Glide.with(this).load(content.get(count).getStrokesImg()).into(mBinding.ivGifBishun);
            //显示解释
            mBinding.tvWordExplanationContent.setText(content.get(count).getInterpretation().replace("\\n", "\n"));
            //显示图片
            Glide.with(this).load(content.get(count).getImg()).into(mBinding.ivCoursePicture);
            //字形演变
            mBinding.courseZixingContent.removeAllViews();
            for (int i = 0; i < content.get(count).getSourceList().size(); i++) {
                ImageView imageView = new ImageView(this.getActivity());
                LinearLayout.LayoutParams iv_params = new LinearLayout.LayoutParams(ConvertScreenUnitUtil.dip2px(this.getContext(), 80), ConvertScreenUnitUtil.dip2px(this.getContext(), 80));
                iv_params.leftMargin = ConvertScreenUnitUtil.dip2px(this.getContext(), 16);
                imageView.setLayoutParams(iv_params);  //设置图片宽高
                Glide.with(this).load(content.get(count).getSourceList().get(i).getSourceImgSrc()).into(imageView); //图片资源
                mBinding.courseZixingContent.addView(imageView); //动态添加图片
            }

        } else if (content.get(count).getType() == 2) {
            mBinding.courseLayout1.setVisibility(View.VISIBLE);
            mBinding.courseLayout2.setVisibility(View.VISIBLE);
            mBinding.courseLayout3.setVisibility(View.VISIBLE);
            mBinding.courseLayout5.setVisibility(View.VISIBLE);

            mBinding.courseLayout4.setVisibility(View.GONE);
            mBinding.courseLayout6.setVisibility(View.GONE);

            mBinding.ivZiStructure.setVisibility(View.VISIBLE);
            mBinding.tvCourseJuzi.setVisibility(View.GONE);
            //显示字词结构
            Glide.with(this).load(content.get(count).getStrcutImg()).into(mBinding.ivZiStructure);
            //不显示笔顺
            mBinding.ivGifBishun.setVisibility(View.GONE);
            //显示解释
            mBinding.tvWordExplanationContent.setText(content.get(count).getInterpretation().replace("\\n", "\n"));
            //显示图片
            Glide.with(this).load(content.get(count).getImg()).into(mBinding.ivCoursePicture);
            //中英文例句
            mBinding.tvLijuContentCn.setText(Html.fromHtml(content.get(count).getSentenceList().get(0).getSentence()));
            mBinding.tvLijuContentEn.setText(Html.fromHtml(content.get(count).getSentenceList().get(0).getInterpretation()));
        } else if (content.get(count).getType() == 3) {
            mBinding.courseLayout1.setVisibility(View.VISIBLE);
            mBinding.courseLayout2.setVisibility(View.VISIBLE);
            mBinding.courseLayout6.setVisibility(View.VISIBLE);

            mBinding.courseLayout3.setVisibility(View.GONE);
            mBinding.courseLayout4.setVisibility(View.GONE);
            mBinding.courseLayout5.setVisibility(View.GONE);

            mBinding.ivZiStructure.setVisibility(View.GONE);
            mBinding.tvCourseJuzi.setVisibility(View.VISIBLE);

            //不显示笔顺
            mBinding.ivGifBishun.setVisibility(View.GONE);
            //显示解释
            mBinding.tvWordExplanationContent.setText(content.get(count).getInterpretation().replace("\\n", "\n"));
            //句子语法分析
            mBinding.tvCourseGrammarContent.setText(content.get(count).getGrammatical().replace("\\n", "\n"));

        }
        mBinding.setCourse(content.get(count));
        //更新读音
        subject = content.get(count).getObjectName();
    }

    private void setContent() {
        //字英文解释
        String content = "You. Used as refer to the person being spoken or written to. It’s second person singular.";
        mBinding.tvWordExplanationContent.setText(content);
        //加载笔画动图
        Glide.with(this).load(R.drawable.you).into(mBinding.ivCoursePicture);
        //加载字生动形象图
        //加载字形演变解释图
        ImageView imageView = new ImageView(this.getActivity());
        LinearLayout.LayoutParams iv_params = new LinearLayout.LayoutParams(ConvertScreenUnitUtil.dip2px(this.getContext(), 80), ConvertScreenUnitUtil.dip2px(this.getContext(), 80));
        iv_params.leftMargin = ConvertScreenUnitUtil.dip2px(this.getContext(), 16);
        imageView.setLayoutParams(iv_params);  //设置图片宽高
        imageView.setImageResource(R.drawable.you_zixing_1); //图片资源
        mBinding.courseZixingContent.addView(imageView); //动态添加图片

        ImageView imageView1 = new ImageView(this.getActivity());
        LinearLayout.LayoutParams iv_params1 = new LinearLayout.LayoutParams(ConvertScreenUnitUtil.dip2px(this.getContext(), 80), ConvertScreenUnitUtil.dip2px(this.getContext(), 80));
        iv_params1.leftMargin = ConvertScreenUnitUtil.dip2px(this.getContext(), 16);
        imageView1.setLayoutParams(iv_params1);  //设置图片宽高
        imageView1.setImageResource(R.drawable.ni_structure); //图片资源
        mBinding.courseZixingContent.addView(imageView1); //动态添加图片
        //加载字形演变解释
        mBinding.courseZixingExplanation.setText("The original form of “you” is the combination of a person and an arrow. Like “me’, it extends from the pointing arrow to “you”");
        //加载例句
        String aa1 = "<font color='#ff0000'>人们</font>在笑。";
        mBinding.tvLijuContentCn.setText(Html.fromHtml(aa1));
        String bb1 = "<font color='#ff0000'>People</font> are laughing.";
        mBinding.tvLijuContentEn.setText(Html.fromHtml(bb1));
        //语法
        mBinding.tvCourseGrammarContent.setText("Question word: who\n" +
                "Be: are\n" +
                "Subject: you");
    }

    private void startSpeaking() {
        mBinding.ivSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (subject != null)
                    MyApplication.getmSynthesizer().startSpeaking(subject, listener);
            }
        });
    }

    private SynthesizerListener listener = new SynthesizerListener() {
        @Override
        public void onSpeakBegin() {

        }

        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {

        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        public void onSpeakProgress(int i, int i1, int i2) {

        }

        @Override
        public void onCompleted(SpeechError speechError) {

        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "destroy");
        EventBus.getDefault().unregister(this);
    }
}

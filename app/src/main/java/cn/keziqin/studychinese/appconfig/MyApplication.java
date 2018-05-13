package cn.keziqin.studychinese.appconfig;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.util.ResourceUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;


import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import cn.keziqin.studychinese.utils.LogUtil;
import okhttp3.OkHttpClient;


/**
 * Created by dells on 2017/8/8.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    private final LinkedList<Activity> activityList = new LinkedList<>();

    public static MyApplication getApplication() {
        return instance;
    }

    private String filePath;

    private static SpeechSynthesizer mSynthesizer;
    // 默认本地发音人
    public String voicerLocal = "xiaoyan";
        private InitListener mTtsInitListner = new InitListener() {
        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                LogUtil.i("kedaxunfei", "aaa");
            } else {
                setSpeakParam();
            }
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //JPushInterface.setDebugMode(true);
       // JPushInterface.init(this);
        filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + instance.getApplicationContext().getPackageName() + "/";
        // 将“12345678”替换成您申请的APPID，申请地址：http://www.xfyun.cn
        // 请勿在“=”与appid之间添加任何空字符或者转义符
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5af8601a");
        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        mSynthesizer = SpeechSynthesizer.createSynthesizer(this, mTtsInitListner);
        initOkGo();
    }

    private void initOkGo() {
        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders();
        HttpParams params = new HttpParams();

        //----------------------------------------------------------------------------------------//

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志

        //超时时间设置，默认60秒
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(10000, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(1)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params);                       //全局公共参数
    }


    public void setSpeakParam() {
        //清空参数
        mSynthesizer.setParameter(com.iflytek.cloud.SpeechConstant.PARAMS, null);
        //设置使用本地引擎
        mSynthesizer.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
        //设置发音人资源路径 使用离线合成SDK时使用
        mSynthesizer.setParameter(ResourceUtil.TTS_RES_PATH, getResourcePath());
        //设置发音人
        mSynthesizer.setParameter(SpeechConstant.VOICE_NAME, voicerLocal);
        //设置合成速度
        mSynthesizer.setParameter(SpeechConstant.SPEED, "50");
        //设置合成音量
        mSynthesizer.setParameter(SpeechConstant.VOLUME, "50");//设置音量，范围0~100
        //设置播放合成音频打断音乐播放，默认为true
        mSynthesizer.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        //mSynthesizer.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
       // mSynthesizer.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.wav");
        // 设置播放器音频流类型
        mSynthesizer.setParameter(SpeechConstant.STREAM_TYPE, "3");
    }

    public String getFilePath() {
        return filePath;
    }

    public String getRoutesPath() {
        return filePath + "result_store";
    }

    public String getScriptPath() {
        return filePath + "script";
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

        //获取发音人资源路径
    private String getResourcePath() {
        StringBuffer tempBuffer = new StringBuffer();
        //合成通用资源
        tempBuffer.append(ResourceUtil.generateResourcePath(this, ResourceUtil.RESOURCE_TYPE.assets, "tts/common.jet"));
        tempBuffer.append(";");
        //发音人资源
        tempBuffer.append(ResourceUtil.generateResourcePath(this, ResourceUtil.RESOURCE_TYPE.assets, "tts/" + voicerLocal + ".jet"));
        return tempBuffer.toString();
    }

    public static SpeechSynthesizer getmSynthesizer() {
        return mSynthesizer;
    }

    public void setmSynthesizer(SpeechSynthesizer mSynthesizer) {
        this.mSynthesizer = mSynthesizer;
    }

    public static void stopSpeaking() {
        if (mSynthesizer.isSpeaking()) mSynthesizer.stopSpeaking();
    }
}

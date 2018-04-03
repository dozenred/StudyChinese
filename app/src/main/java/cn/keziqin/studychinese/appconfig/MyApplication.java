package cn.keziqin.studychinese.appconfig;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;


import java.util.LinkedList;


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

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //JPushInterface.setDebugMode(true);
       // JPushInterface.init(this);
        filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + instance.getApplicationContext().getPackageName() + "/";
        //initOkGo();
    }

//    private void initOkGo() {
//        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
//        HttpHeaders headers = new HttpHeaders();
//        HttpParams params = new HttpParams();
//
//        //----------------------------------------------------------------------------------------//
//
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        //log相关
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
//        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
//        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
//        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
//
//        //超时时间设置，默认60秒
//        builder.readTimeout(10000, TimeUnit.MILLISECONDS);      //全局的读取超时时间
//        builder.writeTimeout(10000, TimeUnit.MILLISECONDS);     //全局的写入超时时间
//        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);   //全局的连接超时时间
//
//        // 其他统一的配置
//        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
//        OkGo.getInstance().init(this)                           //必须调用初始化
//                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
//                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
//                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
//                .setRetryCount(1)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders(headers)                      //全局公共头
//                .addCommonParams(params);                       //全局公共参数
//    }

//    public String getDeviceId() {
//        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        return tm.getDeviceId();
//    }

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
}

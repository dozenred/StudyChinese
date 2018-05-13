package cn.keziqin.studychinese.http.callback;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


import cn.keziqin.studychinese.http.beans.ServerResult;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by dells on 2017/11/14.
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {
    private Type type;
    private Class<T> clazz;

    public JsonCallback() {
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;
        //判断token过期,服务器返回的isLogin是false时token过期，抛出异常，登录接口没有isLogin
//        if (response.headers().get("isLogin") != null) {
//            if (response.headers().get("isLogin").equals("false")) {
//                throw new IllegalStateException("登录过期");
//            }
//        }

        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());

        if (type != null) data = gson.fromJson(jsonReader, type);
        if (clazz != null) data = gson.fromJson(jsonReader, clazz);
        else {
            Type genType = getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            Type type1 = params[0];
            Type rawType = ((ParameterizedType) type1).getRawType();
            if (rawType == ServerResult.class) {
                ServerResult serverResult = gson.fromJson(jsonReader, type1);
                if (serverResult.getResult() == 1000) {
                    //返回成功
                    return (T)serverResult;
                } else if (serverResult.getResult() == 4001) {
                    throw new IllegalStateException(serverResult.getResultDesp());
                } else if (serverResult.getResult() == -1) {
                    return (T) serverResult;
                }
            }
        }


        return data;
    }
}

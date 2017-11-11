package com.yzl.gank.pagetest.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 沈小建 on 2017/3/2 0002.
 *         初始化okHttp和retrofit
 */

public class RetrofitApi {

    public final Retrofit mRetrofit;

    /**
     * 默认读写时间.
     */
    private static final int DEFAULT_TIME_SECONDS = 30;

    private static class SingleHolder {
        private static final RetrofitApi INSTANCE = new RetrofitApi();
    }

    public static RetrofitApi getInstance() {
        return SingleHolder.INSTANCE;
    }

    private RetrofitApi() {

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_SECONDS, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_SECONDS, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}

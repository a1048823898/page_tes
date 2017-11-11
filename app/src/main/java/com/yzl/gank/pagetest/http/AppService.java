package com.yzl.gank.pagetest.http;


import com.yzl.gank.pagetest.bean.GankData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 10488 on 2017-07-24.
 */

public interface AppService {


    /**
     * 福利.
     */
    @GET("data/福利/{number}/{page}")
    Call<BaseResponse<List<GankData>>> getWelfare1(@Path("page") int page, @Path("number") int number);
}

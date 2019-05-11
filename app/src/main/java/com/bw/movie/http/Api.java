package com.bw.movie.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @ProjectName: WeiduMovie
 * @ClassName: Api
 * @Description: java类作用描述
 * @Author: 刘继超
 * @CreateDate: 2019/5/10 15:07:51
 */
public interface Api {
    @POST
    Observable<ResponseBody> doPost(@Url String url, @QueryMap Map<String,String> map);
    @GET
    Observable<ResponseBody> doGetMap(@Url String url, @QueryMap Map<String,String> map);
    @GET
    Observable<ResponseBody> doGet(@Url String url);
}

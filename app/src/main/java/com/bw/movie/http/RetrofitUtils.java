package com.bw.movie.http;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: WeiduMovie
 * @ClassName: RetrofitUtils
 * @Description: java类作用描述
 * @Author: 刘继超
 * @CreateDate: 2019/5/10 15:08:25
 */
public class RetrofitUtils {

    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    private static RetrofitUtils utils;

    private RetrofitUtils(){
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("","")
                                .addHeader("","")
                                .build();
                        Log.e("intercept: ",request.url()+"");
                        Response proceed = chain.proceed(request);
                        return proceed;
                    }
                })
                .build();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Url.NEI_URL)
                .build();
    }

    public static RetrofitUtils getInstance(){
        if (utils==null){
            utils = new RetrofitUtils();
        }
        return utils;
    }


}


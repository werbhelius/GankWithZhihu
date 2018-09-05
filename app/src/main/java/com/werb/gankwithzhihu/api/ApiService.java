package com.werb.gankwithzhihu.api;

import com.werb.gankwithzhihu.api.services.DailyApi;
import com.werb.gankwithzhihu.api.services.GankApi;
import com.werb.gankwithzhihu.api.services.ZhihuApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.werb.gankwithzhihu.api.Constant.DAILY_BASE_URL;
import static com.werb.gankwithzhihu.api.Constant.GANK_BASE_URL;
import static com.werb.gankwithzhihu.api.Constant.ZHIHU_BASE_URL;

/**
 * Created by Werb on 2016/8/18.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 * Singleton Factory with retrofit
 */
public class ApiService {

    private static ZhihuApi zhihuApiSingleton = null;
    private static GankApi gankApiSingleton = null;
    private static DailyApi dailyApiSingleton = null;

    //return Singleton
    public static ZhihuApi getZhihuApiSingleton() {
        if (zhihuApiSingleton == null) {
            synchronized (ZhihuApi.class){
                if (zhihuApiSingleton == null){
                    Retrofit retrofit_zhihu = new Retrofit.Builder()
                            .baseUrl(ZHIHU_BASE_URL)
                            .client(OkHttpManager.getInstance())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                    zhihuApiSingleton = retrofit_zhihu.create(ZhihuApi.class);
                }
            }
        }
        return zhihuApiSingleton;
    }

    public static GankApi getGankApiSingleton() {
        if (gankApiSingleton == null) {
            synchronized (GankApi.class){
                if (gankApiSingleton == null){
                    Retrofit retrofit_gank = new Retrofit.Builder()
                            .baseUrl(GANK_BASE_URL)
                            .client(OkHttpManager.getInstance())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                    gankApiSingleton = retrofit_gank.create(GankApi.class);
                }
            }
        }
        return gankApiSingleton;
    }

    public static DailyApi getDailyApiSingleton() {
        if (dailyApiSingleton == null) {
            synchronized (DailyApi.class){
                if (dailyApiSingleton == null){
                    Retrofit retrofit_daily= new Retrofit.Builder()
                            .baseUrl(DAILY_BASE_URL)
                            .client(OkHttpManager.getInstance())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                    dailyApiSingleton = retrofit_daily.create(DailyApi.class);
                }
            }
        }
        return dailyApiSingleton;
    }
}

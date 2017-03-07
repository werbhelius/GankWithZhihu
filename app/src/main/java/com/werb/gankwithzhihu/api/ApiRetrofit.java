package com.werb.gankwithzhihu.api;

import android.os.Environment;

import com.werb.gankwithzhihu.MyApp;
import com.werb.gankwithzhihu.util.StateUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Werb on 2016/8/18.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 * retrofit instance
 */
public class ApiRetrofit {

    public ZhihuApi ZhihuApiService;
    public GankApi GankApiService;
    public DailyApi DailyApiService;
    public static final String ZHIHU_BASE_URL = "http://news-at.zhihu.com/api/4/";
    public static final String GANK_BASE_URL = "http://gank.io/api/";
    public static final String DAILY_BASE_URL = "http://app3.qdaily.com/app3/";

    public ZhihuApi getZhihuApiService() {
        return ZhihuApiService;
    }

    public GankApi getGankApiService() {
        return GankApiService;
    }

    public DailyApi getDailyApiService() {
        return DailyApiService;
    }

    ApiRetrofit() {
        //cache url
        File httpCacheDirectory = new File(MyApp.mContext.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache).build();

        Retrofit retrofit_zhihu = new Retrofit.Builder()
                .baseUrl(ZHIHU_BASE_URL)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Retrofit retrofit_gank = new Retrofit.Builder()
                .baseUrl(GANK_BASE_URL)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Retrofit retrofit_daily= new Retrofit.Builder()
                .baseUrl(DAILY_BASE_URL)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ZhihuApiService = retrofit_zhihu.create(ZhihuApi.class);
        GankApiService = retrofit_gank.create(GankApi.class);
        DailyApiService = retrofit_daily.create(DailyApi.class);
    }

    private static File createDir(String directory) {
        File createDir = new File(Environment.getExternalStorageDirectory() + File.separator + directory);
        if (!createDir.exists()) {
            if (createDir.mkdirs()) {
                return createDir;
            }
        } else {
            return createDir;
        }
        return null;
    }

    public static File getCache() {
        return createDir(MyApp.mContext.getPackageName() + File.separator + "Cache");
    }

    private OkHttpClient configClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        if (true) {
            Interceptor loggingIntercept = chain -> {
                Request request = chain.request();
                Response response = chain.proceed(request);
                ResponseBody responseBody = response.body();
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();
                Charset UTF8 = Charset.forName("UTF-8");
//                    L.i("ds>>> REQUEST_JSON", buffer.clone().readString(UTF8));
//                    L.i("ds>>> REQUEST_URL", request.toString());
                return response;
            };
            okHttpClient.addInterceptor(loggingIntercept);
        }
        File httpCacheDirectory = new File(getCache(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
//        L.i("ds>>>", " 缓存  " + StringUtil.getExternalCacheDir(App.getApplication()) + "  , 缓存长度 " + httpCacheDirectory.length());
//        int cacheSize = 10 * 1024 * 1024;
//        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        okHttpClient.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);//离线
        okHttpClient.cache(cache);
//        okHttpClient.addNetworkInterceptor(getNetWorkInterceptor());//在线
        okHttpClient.connectTimeout(10000L, TimeUnit.SECONDS);
        okHttpClient.readTimeout(10000L, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(10000L, TimeUnit.SECONDS);
        return okHttpClient.build();
    }


    Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {
        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);
        cacheBuilder.maxStale(365, TimeUnit.DAYS);
        CacheControl cacheControl = cacheBuilder.build();

        Request request = chain.request();
        if (!StateUtils.isNetworkAvailable(MyApp.mContext)) {
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (StateUtils.isNetworkAvailable(MyApp.mContext)) {
            int maxAge = 0; // read from cache
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public ,max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    };

//    //cache
//    Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {
//
//        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
//        cacheBuilder.maxAge(0, TimeUnit.SECONDS);
//        cacheBuilder.maxStale(365, TimeUnit.DAYS);
//        CacheControl cacheControl = cacheBuilder.build();
//
//        Request request = chain.request();
//        if (!StateUtils.isNetworkAvailable(MyApp.mContext)) {
//            request = request.newBuilder()
//                    .cacheControl(cacheControl)
//                    .build();
//
//        }
//        Response originalResponse = chain.proceed(request);
//        if (StateUtils.isNetworkAvailable(MyApp.mContext)) {
//            int maxAge = 0; // read from cache
//            return originalResponse.newBuilder()
//                    .removeHeader("Pragma")
//                    .header("Cache-Control", "public ,max-age=" + maxAge)
//                    .build();
//        } else {
//            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
//            return originalResponse.newBuilder()
//                    .removeHeader("Pragma")
//                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                    .build();
//        }
//    };
}

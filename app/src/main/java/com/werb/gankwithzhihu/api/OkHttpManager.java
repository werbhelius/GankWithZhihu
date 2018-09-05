package com.werb.gankwithzhihu.api;

import com.werb.gankwithzhihu.MyApp;
import com.werb.gankwithzhihu.util.StateUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpManager {

    private static OkHttpClient client = null;

    public static OkHttpClient getInstance() {
        if (client == null) {
            synchronized (OkHttpManager.class){
                if (client == null)
                {
                    //cache url
                    File httpCacheDirectory = new File(MyApp.mContext.getCacheDir(), "responses");
                    int cacheSize = 10 * 1024 * 1024; // 10 MiB
                    Cache cache = new Cache(httpCacheDirectory, cacheSize);

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
                    client = new OkHttpClient.Builder()
                            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                            .cache(cache).build();
                }
            }
        }
        return client;
    }
}

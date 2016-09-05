package com.werb.gankwithzhihu;

import android.app.Application;
import android.content.Context;

/**
 * Created by Werb on 2016/8/18.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public class MyApp extends Application {

    private static final String DB_NAME = "weibo.db";

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}

package com.werb.gankwithzhihu.ui.base;

import com.werb.gankwithzhihu.api.ApiFactory;
import com.werb.gankwithzhihu.api.DailyApi;
import com.werb.gankwithzhihu.api.GankApi;
import com.werb.gankwithzhihu.api.ZhihuApi;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Werb on 2016/8/18.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 * Base of Presenter
 */
public abstract class BasePresenter<V> {

    protected Reference<V> mViewRef;

    public static final ZhihuApi zhihuApi = ApiFactory.getZhihuApiSingleton();
    public static final GankApi gankApi = ApiFactory.getGankApiSingleton();
    public static final DailyApi dailyApi = ApiFactory.getDailyApiSingleton();

    public void attachView(V view){
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView(){
        return mViewRef.get();
    }

    public boolean isViewAttached(){
        return mViewRef != null&&mViewRef.get()!=null;
    }

    public void detachView(){
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
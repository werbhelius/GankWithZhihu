package com.werb.gankwithzhihu.ui.presenter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.bean.zhihu.SplashImage;
import com.werb.gankwithzhihu.ui.base.BasePresenter;
import com.werb.gankwithzhihu.ui.view.ISplashView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Werb on 2016/8/18.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public class SplashPresenter extends BasePresenter<ISplashView> {

    private Context context;
    private ISplashView splashView;
    private ImageView coverImg;

    public SplashPresenter(Context context) {
        this.context = context;
    }

    public void getSplashImage() {
        splashView = getView();
        if (splashView != null) {
            coverImg = splashView.getCoverImg();

            zhihuApi.getSplashImage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(splashImage -> {
                        disPlayImage(splashImage,coverImg);
                    },this::loadError);
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
    }

    private void disPlayImage(SplashImage splashImage, ImageView iv){
        Glide.with(context).load(splashImage.getImg()).centerCrop().into(iv);
    }

    public void destroyImg(){
        Glide.clear(coverImg);
    }

}

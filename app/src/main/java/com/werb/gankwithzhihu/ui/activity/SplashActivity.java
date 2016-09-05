package com.werb.gankwithzhihu.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.ui.base.MVPBaseActivity;
import com.werb.gankwithzhihu.ui.presenter.SplashPresenter;
import com.werb.gankwithzhihu.ui.view.ISplashView;

import butterknife.Bind;

/**
 * Created by Werb on 2016/8/18.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 * Splash with zhihu start-image
 */
public class SplashActivity extends MVPBaseActivity<ISplashView,SplashPresenter> implements ISplashView {

    @Bind(R.id.iv_cover)
    ImageView iv_cover;
    @Bind(R.id.tv_slogan)
    TextView tv_slogan;

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getSplashImage();

        //Old English 字体
        Typeface typeFace =Typeface.createFromAsset(getAssets(),"fonts/rm_albion.ttf");
        tv_slogan.setTypeface(typeFace);
        tv_slogan.setTextSize(30f);

        //进入主界面
        new Handler().postDelayed(() -> goToMain(),1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroyImg();
    }

    @Override
    public ImageView getCoverImg() {
        return iv_cover;
    }

    @Override
    public void goToMain() {
        finish();
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
    }
}

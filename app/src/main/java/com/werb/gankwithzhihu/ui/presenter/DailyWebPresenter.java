package com.werb.gankwithzhihu.ui.presenter;

import android.content.Context;

import com.werb.gankwithzhihu.ui.base.BasePresenter;
import com.werb.gankwithzhihu.ui.view.IDailyWebView;

/**
 * Created by Werb on 2016/9/5.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public class DailyWebPresenter extends BasePresenter<IDailyWebView> {

    private Context context;

    public DailyWebPresenter(Context context) {
        this.context = context;
    }
}

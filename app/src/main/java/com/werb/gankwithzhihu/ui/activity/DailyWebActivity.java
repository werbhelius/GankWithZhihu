package com.werb.gankwithzhihu.ui.activity;

import com.werb.gankwithzhihu.ui.base.MVPBaseActivity;
import com.werb.gankwithzhihu.ui.presenter.DailyWebPresenter;
import com.werb.gankwithzhihu.ui.view.IDailyWebView;

/**
 * Created by Werb on 2016/9/5.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public class DailyWebActivity extends MVPBaseActivity<IDailyWebView,DailyWebPresenter> implements IDailyWebView {

    @Override
    protected DailyWebPresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return 0;
    }
}

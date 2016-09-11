package com.werb.gankwithzhihu.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;
import android.widget.TextView;

import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.ui.base.BasePresenter;
import com.werb.gankwithzhihu.ui.base.MVPBaseActivity;

import butterknife.Bind;

/**
 * Created by Werb on 2016/9/6.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 * 希望可以帮助到你 :)
 */
public class AboutMeActivity extends MVPBaseActivity implements View.OnClickListener {

    @Bind(R.id.tv_github)
    TextView tv_github;
    @Bind(R.id.tv_blog)
    TextView tv_blog;

    public CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();

        tv_github.setOnClickListener(this);
        tv_blog.setOnClickListener(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about_me;
    }

    /**
     * 初始化ToolBar
     */
    private void initToolbar() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("很高兴你能看到这里");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_github:
                Intent it1 = new Intent(Intent.ACTION_VIEW, Uri.parse(tv_github.getText().toString()));
                it1.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                startActivity(it1);
                break;
            case R.id.tv_blog:
                Intent it2 = new Intent(Intent.ACTION_VIEW, Uri.parse(tv_blog.getText().toString()));
                it2.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                startActivity(it2);
                break;
        }
    }
}

package com.werb.gankwithzhihu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.ui.base.MVPBaseActivity;
import com.werb.gankwithzhihu.ui.presenter.DailyFeedPresenter;
import com.werb.gankwithzhihu.ui.view.IDailyFeedView;

import butterknife.Bind;

/**
 * Created by Werb on 2016/9/6.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 * 展示 好奇心日报 type = feed 的 item 详细内容
 */
public class DailyFeedActivity extends MVPBaseActivity<IDailyFeedView,DailyFeedPresenter> implements IDailyFeedView {

    private static final String FEED_ID = "feed_id";
    private static final String FEED_DESC = "feed_desc";
    private static final String FEED_TITLE = "feed_title";
    private static final String FEED_IMG = "feed_img";

    private String id;
    private String desc;
    private String title;
    private String img;

    @Bind(R.id.iv_feed_img)
    ImageView iv_feed_img;
    @Bind(R.id.tv_feed_title)
    TextView tv_feed_title;
    @Bind(R.id.tv_feed_desc)
    TextView tv_feed_desc;
    @Bind(R.id.feed_list)
    RecyclerView feed_list;

    private GridLayoutManager gridLayoutManager;

    @Override
    protected DailyFeedPresenter createPresenter() {
        return new DailyFeedPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_daily_feed;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntent();
        initView();
        mPresenter.getDailyFeedDetail(id,"0");
        mPresenter.scrollRecycleView();
    }

    public static Intent newIntent(Context context, String id, String desc, String title, String img) {
        Intent intent = new Intent(context, DailyFeedActivity.class);
        intent.putExtra(DailyFeedActivity.FEED_ID, id);
        intent.putExtra(DailyFeedActivity.FEED_DESC, desc);
        intent.putExtra(DailyFeedActivity.FEED_TITLE, title);
        intent.putExtra(DailyFeedActivity.FEED_IMG, img);
        return intent;
    }

    private void parseIntent() {
        id = getIntent().getStringExtra(FEED_ID);
        desc = getIntent().getStringExtra(FEED_DESC);
        title = getIntent().getStringExtra(FEED_TITLE);
        img = getIntent().getStringExtra(FEED_IMG);
    }

    private void initView(){
        tv_feed_title.setText(title);
        tv_feed_desc.setText(desc);
        Glide.with(this).load(img).centerCrop().into(iv_feed_img);

        gridLayoutManager = new GridLayoutManager(this,2);
        feed_list.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        mPresenter.getDailyFeedDetail(id,"0");
    }

    @Override
    public Boolean isSetRefresh() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.clear(iv_feed_img);
    }

    @Override
    public void setDataRefresh(Boolean refresh) {
        setRefresh(refresh);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return feed_list;
    }

    @Override
    public GridLayoutManager getLayoutManager() {
        return gridLayoutManager;
    }
}

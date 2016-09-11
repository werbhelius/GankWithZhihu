package com.werb.gankwithzhihu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.ui.base.MVPBaseActivity;
import com.werb.gankwithzhihu.ui.presenter.GankPresenter;
import com.werb.gankwithzhihu.ui.view.IGankView;

import java.util.Calendar;

import butterknife.Bind;

/**
 * Created by Werb on 2016/8/30.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 * Gank 干货详细内容
 */
public class GankActivity extends MVPBaseActivity<IGankView,GankPresenter> implements IGankView {

    private static final String DATE = "date";

    private int year;
    private int month;
    private int day;

    @Bind(R.id.gank_list)
    RecyclerView gank_list;

    @Override
    protected GankPresenter createPresenter() {
        return new GankPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_gank;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        gank_list.setLayoutManager(layoutManager);

        setTitle("Gank の 今日特供");
        parseIntent();
        setDataRefresh(true);
        mPresenter.getGankList(year,month,day);
    }

    public static Intent newIntent(Context context, long date){
        Intent intent = new Intent(context,GankActivity.class);
        intent.putExtra(GankActivity.DATE,date);
        return intent;
    }

    private void parseIntent(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getIntent().getLongExtra(DATE,0));
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public Boolean isSetRefresh() {
        return true;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    public void setDataRefresh(boolean refresh) {
        setRefresh(refresh);
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);
        mPresenter.getGankList(year,month,day);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return gank_list;
    }
}

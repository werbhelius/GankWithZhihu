package com.werb.gankwithzhihu.ui.presenter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.api.ApiService;
import com.werb.gankwithzhihu.bean.daily.DailyTimeLine;
import com.werb.gankwithzhihu.ui.adapter.DailyListAdapter;
import com.werb.gankwithzhihu.ui.base.BasePresenter;
import com.werb.gankwithzhihu.ui.view.IDailyFgView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Werb on 2016/9/2.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public class DailyFgPresenter extends BasePresenter<IDailyFgView> {

    private Context context;
    private IDailyFgView dailyFgView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private DailyTimeLine timeLine;
    private DailyListAdapter adapter;
    private int lastVisibleItem;
    private String has_more;
    private String next_pager;
    private boolean isLoadMore = false; // 是否加载过更多

    public DailyFgPresenter(Context context) {
        this.context = context;
    }

    public void getDailyTimeLine(String num){
        dailyFgView = getView();
        if(dailyFgView !=null){
            mRecyclerView = dailyFgView.getRecyclerView();
            layoutManager = dailyFgView.getLayoutManager();

            ApiService.getDailyApiSingleton().getDailyTimeLine(num)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dailyTimeLine -> {
                        if(dailyTimeLine.getMeta().getMsg().equals("success")){
                            disPlayDailyTimeLine(context,dailyTimeLine,mRecyclerView,dailyFgView);
                        }
                    },this::loadError);
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        dailyFgView.setDataRefresh(false);
        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
    }

    private void disPlayDailyTimeLine(Context context, DailyTimeLine dailyTimeLine, RecyclerView recyclerView, IDailyFgView dailyFgView){
        if(dailyTimeLine.getResponse().getLast_key()!=null){
            next_pager = dailyTimeLine.getResponse().getLast_key();
        }
        has_more = dailyTimeLine.getResponse().getHas_more();
        if (isLoadMore) {
            if (dailyTimeLine.getResponse().getFeeds() == null) {
                adapter.updateLoadStatus(adapter.LOAD_NONE);
                dailyFgView.setDataRefresh(false);
                return;
            }
            else {
                timeLine.getResponse().getFeeds().addAll(dailyTimeLine.getResponse().getFeeds());
            }
            adapter.notifyDataSetChanged();
        } else {
            timeLine = dailyTimeLine;
            adapter = new DailyListAdapter(context, timeLine.getResponse());
            recyclerView.setAdapter(adapter);
        }
        dailyFgView.setDataRefresh(false);
    }

    /**
     * recyclerView Scroll listener , maybe in here is wrong ?
     */
    public void scrollRecycleView() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = layoutManager
                            .findLastVisibleItemPosition();
                    if (layoutManager.getItemCount() == 1) {
                        adapter.updateLoadStatus(adapter.LOAD_NONE);
                        return;
                    }
                    if (lastVisibleItem + 1 == layoutManager
                            .getItemCount()) {
                        adapter.updateLoadStatus(adapter.LOAD_PULL_TO);
                        if(has_more.equals("true")) {
                            isLoadMore = true;
                        }
                        adapter.updateLoadStatus(adapter.LOAD_MORE);
                        new Handler().postDelayed(() -> getDailyTimeLine(next_pager), 1000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }
}

package com.werb.gankwithzhihu.ui.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.bean.gank.Gank;
import com.werb.gankwithzhihu.ui.adapter.GankActivityAdapter;
import com.werb.gankwithzhihu.ui.base.BasePresenter;
import com.werb.gankwithzhihu.ui.view.IGankView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Werb on 2016/8/30.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public class GankPresenter extends BasePresenter<IGankView> {

    private Context context;
    private IGankView gankView;
    private RecyclerView recyclerView;

    public GankPresenter(Context context) {
        this.context = context;
    }

    public void getGankList(int year, int month, int day) {
        gankView = getView();
        if (gankView != null) {
            recyclerView = gankView.getRecyclerView();

            gankApi.getGankData(year, month, day)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(gankData -> {
                        displayGankList(context,gankData.results.getAllResults(),gankView,recyclerView);
                    }, this::loadError);
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.gank_load_error, Toast.LENGTH_SHORT).show();
    }

    private void displayGankList(Context context, List<Gank> gankList, IGankView gankView, RecyclerView recyclerView) {
        GankActivityAdapter adapter = new GankActivityAdapter(context, gankList);
        recyclerView.setAdapter(adapter);
        gankView.setDataRefresh(false);
    }
}

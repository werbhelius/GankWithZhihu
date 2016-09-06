package com.werb.gankwithzhihu.ui.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Werb on 2016/9/6.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public interface IDailyFeedView {

    void setDataRefresh(Boolean refresh);
    RecyclerView getRecyclerView();
    GridLayoutManager getLayoutManager();
}

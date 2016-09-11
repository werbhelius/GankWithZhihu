package com.werb.gankwithzhihu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.ui.adapter.ViewPagerFgAdapter;
import com.werb.gankwithzhihu.ui.base.BasePresenter;
import com.werb.gankwithzhihu.ui.base.MVPBaseActivity;
import com.werb.gankwithzhihu.ui.base.MVPBaseFragment;
import com.werb.gankwithzhihu.ui.fragment.DailyFragment;
import com.werb.gankwithzhihu.ui.fragment.GankFragment;
import com.werb.gankwithzhihu.ui.fragment.ZhihuFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Werb on 2016/8/18.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 * 主界面，包含了三个Fragment，但是我觉得在 Fragment 的加载和销毁的处理上，做的不好，希望大家可以有好的建议提供给我
 */

public class MainActivity extends MVPBaseActivity {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.content_viewPager)
    ViewPager content_viewPager;

    private List<MVPBaseFragment> fragmentList;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTabView();
    }

    //初始化Tab滑动
    public void initTabView(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new ZhihuFragment());
        fragmentList.add(new GankFragment());
        fragmentList.add(new DailyFragment());
        content_viewPager.setOffscreenPageLimit(3);//设置至少3个fragment，防止重复创建和销毁，造成内存溢出
        content_viewPager.setAdapter(new ViewPagerFgAdapter(getSupportFragmentManager(),fragmentList,"main_view_pager"));//给ViewPager设置适配器
        tabLayout.setupWithViewPager(content_viewPager);//将TabLayout和ViewPager关联起来
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.today_github){
            String github_trending = "https://github.com/trending";
            startActivity(GankWebActivity.newIntent(this,github_trending));
            return true;
        }else if(item.getItemId() == R.id.about_me){
            startActivity(new Intent(this,AboutMeActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}

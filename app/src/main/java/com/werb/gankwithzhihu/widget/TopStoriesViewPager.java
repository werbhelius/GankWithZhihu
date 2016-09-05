package com.werb.gankwithzhihu.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.werb.gankwithzhihu.MyApp;
import com.werb.gankwithzhihu.bean.zhihu.TopStories;
import com.werb.gankwithzhihu.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TopStoriesViewPager extends RelativeLayout {

	private Context context;
	private LinearLayout dotLayout;
	private ViewPager viewPager;
	private ViewPagerClickListenner listenner;
	private List<View> dotList;
	private int currentItem = 0;// ImageViewpager当前页面的index
	private int oldItem = 0;
	private List<ImageView> images;
	// 执行周期性或定时任务
	private ScheduledExecutorService mScheduledExecutorService;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			viewPager.setCurrentItem(currentItem);
		}
	};

	public TopStoriesViewPager(Context context) {
		super(context);
		this.context = context;
		setView();
	}

	public TopStoriesViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		setView();
	}

	private void setView() {
		viewPager = new ViewPager(context);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		viewPager.setLayoutParams(params);

		dotLayout = new LinearLayout(context);
		LayoutParams dotParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		dotParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		dotParams.setMargins(0, 0, 0, ScreenUtil.instance(context).dip2px(10));
		dotLayout.setLayoutParams(dotParams);
		dotLayout.setGravity(Gravity.CENTER_HORIZONTAL);

		this.addView(viewPager);
		this.addView(dotLayout);

	}

	public void init(List<TopStories> items,TextView tv,
					 ViewPagerClickListenner clickListenner) {
		this.listenner = clickListenner;
		images = new ArrayList<>();
		dotList = new ArrayList<>();

		for (int i = 0; i < items.size(); i++) {
			final TopStories item = items.get(i);
			final ImageView mImageView = new ImageView(
					context);
			ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			mImageView.setLayoutParams(layoutParams);
			mImageView.setOnClickListener(v -> {
                if (null != listenner) {
                    listenner.onClick(item);
                }
            });

			// 得到屏幕的宽度
			ScreenUtil screenUtil = ScreenUtil.instance(context);
			int width = screenUtil.getScreenWidth();

			Glide.with(MyApp.mContext).load(item.getImage())
					.centerCrop()
					.into(mImageView);
			images.add(mImageView);
		}

		viewPager.setAdapter(new MyPagerAdapter(images));
		tv.setText(items.get(0).getTitle());
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				tv.setText(items.get(position).getTitle());
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	/**
	 * 开启定时任务
	 */
	public void startAutoRun(){
		mScheduledExecutorService = Executors
				.newSingleThreadScheduledExecutor();
		/**循环
		 * 创建并执行一个在给定初始延迟后首次启用的定期操作， 后续操作具有给定的周期；也就是将在 initialDelay 后开始执行，
		 * 然后在initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行， 依此类推
		 */
		mScheduledExecutorService.scheduleAtFixedRate(new ViewPagerTask(), 5,
				5, TimeUnit.SECONDS);
	}

	/**
	 * 关闭定时任务
	 */
	public void stopAutoRun(){
		if (mScheduledExecutorService!=null) {
			mScheduledExecutorService.shutdown();
		}
	}

	/**
	 * 发消息改变页数
	 * 
	 * @author sujingbo
	 * 
	 */
	class ViewPagerTask implements Runnable {

		@Override
		public void run() {
			if (images != null) {
				currentItem = (currentItem + 1) % images.size();
				handler.obtainMessage().sendToTarget();
			}
		}
	}

	public int getResourceId(String resourceName) {
		int resId = context.getResources().getIdentifier(resourceName,
				"drawable", context.getPackageName());
		return resId;
	}

	public class MyPagerAdapter extends PagerAdapter{
		

		private List<? extends View> views;

		public MyPagerAdapter(List<? extends View> views) {
			this.views = views;
		}

		@Override
		public int getCount() {
			// return Integer.MAX_VALUE;
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			if (views.size() > 0
					&& views.get(position % views.size()).getParent() != null) {
				((ViewPager) views.get(position % views.size()).getParent())
						.removeView(views.get(position % views.size()));
			}
			try {
				((ViewPager) container).addView(
						views.get(position % views.size()), 0);
			} catch (Exception e) {
			}
			return views.get(position % views.size());
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(views.get(position
					% views.size()));
		}

	}
	
	// 点击事件监听器接口
	public interface ViewPagerClickListenner {
		/**
		 * item点击事件监听
		 */
		void onClick(TopStories item);
	}
	
}

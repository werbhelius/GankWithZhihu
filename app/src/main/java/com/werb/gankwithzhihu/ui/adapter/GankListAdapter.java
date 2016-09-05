package com.werb.gankwithzhihu.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.bean.gank.Gank;
import com.werb.gankwithzhihu.ui.activity.GankActivity;
import com.werb.gankwithzhihu.ui.activity.PictureActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Werb on 2016/8/19.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 * Gank List Adapter
 */
public class GankListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Gank> mList;

    public GankListAdapter(Context mContext, List<Gank> mList) {
        this.context = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank_meizi,parent,false);
        return new GankMeiZhiViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GankMeiZhiViewHolder){
            GankMeiZhiViewHolder gankMeiZhiViewHolder = (GankMeiZhiViewHolder) holder;
            gankMeiZhiViewHolder.bindItem(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class GankMeiZhiViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.card_meizhi)
        CardView card_meizhi;
        @Bind(R.id.iv_meizhi)
        ImageView iv_meizhi;
        @Bind(R.id.tv_meizhi_title)
        TextView tv_meizhi_title;

        public GankMeiZhiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);


        }

        public void bindItem(Gank meizhi){
            tv_meizhi_title.setText(meizhi.getDesc());
            Glide.with(context).load(meizhi.getUrl()).centerCrop().into(iv_meizhi);

            //点击图片
            iv_meizhi.setOnClickListener(v -> {
                Intent intent = PictureActivity.newIntent(context,meizhi.getUrl(),meizhi.getDesc());
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,iv_meizhi,PictureActivity.TRANSIT_PIC);
                // Android 5.0+
                try {
                    ActivityCompat.startActivity((Activity) context,intent,optionsCompat.toBundle());
                } catch (Exception e) {
                    e.printStackTrace();
                    context.startActivity(intent);
                }
            });

            //点击card
            card_meizhi.setOnClickListener(v -> {
                Intent intent = GankActivity.newIntent(context,meizhi.getPublishedAt().getTime());
                context.startActivity(intent);
            });
        }
    }
}

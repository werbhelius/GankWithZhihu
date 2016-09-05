package com.werb.gankwithzhihu.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.bean.gank.Gank;
import com.werb.gankwithzhihu.ui.activity.GankWebActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Werb on 2016/8/30.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public class GankActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Gank> gankList;

    public GankActivityAdapter(Context context, List<Gank> gankList) {
        this.context = context;
        this.gankList = gankList;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = View.inflate(parent.getContext(), R.layout.item_gank_list, null);
        return new GankVideoViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GankVideoViewHolder) {
            GankVideoViewHolder gankVideoViewHolder = (GankVideoViewHolder) holder;
            gankVideoViewHolder.bindItem(gankList.get(position));
        } else if (holder instanceof EmptyViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return gankList.size();
    }

    /**
     * type = Empty
     */
    class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * type = Video
     */
    class GankVideoViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_gank)
        CardView card_gank;
        @Bind(R.id.iv_type)
        ImageView iv_type;
        @Bind(R.id.tv_type)
        TextView tv_type;
        @Bind(R.id.tv_desc)
        TextView tv_desc;
        @Bind(R.id.tv_who)
        TextView tv_who;
        @Bind(R.id.iv_type_bg)
        ImageView iv_type_bg;

        public GankVideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(Gank gank) {
            switch (gank.getType()){
                case "Android":
                    Glide.with(context).load(R.drawable.android).centerCrop().into(iv_type);
                    iv_type_bg.setBackgroundResource(R.color.android);
                    break;
                case "iOS":
                    Glide.with(context).load(R.drawable.ios).centerCrop().into(iv_type);
                    iv_type_bg.setBackgroundResource(R.color.ios);
                    break;
                case "休息视频":
                    Glide.with(context).load(R.drawable.video).centerCrop().into(iv_type);
                    iv_type_bg.setBackgroundResource(R.color.休息视频);
                    break;
                case "前端":
                    Glide.with(context).load(R.drawable.web).centerCrop().into(iv_type);
                    iv_type_bg.setBackgroundResource(R.color.前端);
                    break;
                case "拓展资源":
                    Glide.with(context).load(R.drawable.tuozhan).centerCrop().into(iv_type);
                    iv_type_bg.setBackgroundResource(R.color.拓展资源);
                    break;
                case "瞎推荐":
                    Glide.with(context).load(R.drawable.tuijian).centerCrop().into(iv_type);
                    iv_type_bg.setBackgroundResource(R.color.瞎推荐);
                    break;
            }

            tv_type.setText("来自话题 : "+gank.getType());
            tv_desc.setText(gank.getDesc());
            tv_who.setText("via : "+gank.getWho());

            card_gank.setOnClickListener(v -> context.startActivity(GankWebActivity.newIntent(context,gank.getUrl())));

        }
    }
}

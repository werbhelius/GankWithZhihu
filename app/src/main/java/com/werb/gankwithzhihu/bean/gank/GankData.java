package com.werb.gankwithzhihu.bean.gank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Werb on 2016/8/19.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public class GankData {

    public Result results;
    public List<String> category;

    public class Result {
        public List<Gank> Android;
        public List<Gank> iOS;
        public List<Gank> 休息视频;
        public List<Gank> 前端;
        public List<Gank> 拓展资源;
        public List<Gank> 福利;
        public List<Gank> 瞎推荐;

        //把数据添加到一起
        public List<Gank> getAllResults() {

            List<Gank> mGankList = new ArrayList<>();

            if (休息视频 != null) mGankList.addAll(0,休息视频);
            if (前端 != null) mGankList.addAll(前端);
            if (Android != null) mGankList.addAll(Android);
            if (iOS != null) mGankList.addAll(iOS);
            if (拓展资源 != null) mGankList.addAll(拓展资源);
            if (瞎推荐 != null) mGankList.addAll(瞎推荐);

            return mGankList;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "Android=" + Android +
                    ", iOS=" + iOS +
                    ", 休息视频=" + 休息视频 +
                    ", 前端=" + 前端 +
                    ", 拓展资源=" + 拓展资源 +
                    ", 福利=" + 福利 +
                    ", 瞎推荐=" + 瞎推荐 +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GankData{" +
                "results=" + results +
                ", category=" + category +
                '}';
    }
}

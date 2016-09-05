package com.werb.gankwithzhihu.bean.gank;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Werb on 2016/8/19.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public class Video implements Serializable {

    private boolean error;
    private List<Gank> results;

    public boolean isError() {
        return error;
    }

    public List<Gank> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Video{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}

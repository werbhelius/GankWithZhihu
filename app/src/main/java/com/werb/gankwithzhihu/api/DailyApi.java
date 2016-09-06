package com.werb.gankwithzhihu.api;

import com.werb.gankwithzhihu.bean.daily.DailyTimeLine;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Werb on 2016/9/2.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public interface DailyApi {

    @GET("homes/index/{num}.json")
    Observable<DailyTimeLine> getDailyTimeLine(@Path("num") String num);

    @GET("options/index/{id}/{num}.json")
    Observable<DailyTimeLine> getDailyFeedDetail(@Path("id") String id,@Path("num") String num);
}

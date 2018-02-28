
package com.example.stopgap.mydemo.api;

import com.example.stopgap.mydemo.model.LiveResult;
import com.example.stopgap.mydemo.model.RankResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LiveApi {
    @GET("app/tabs/live")
    Observable<LiveResult> getRank(@Query("platform") String platform, @Query("version") String version);
}



// (c)2016 Flipboard Inc, All Rights Reserved.

package com.example.stopgap.mydemo.api;

import com.example.stopgap.mydemo.model.TeamNewsResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TeamNewsApi {
    @GET("v2/video/team/{teamnumber}")
    Observable<TeamNewsResult> getTeamNews(@Path("teamnumber") int teamnumber,@Query("next") String next);

}

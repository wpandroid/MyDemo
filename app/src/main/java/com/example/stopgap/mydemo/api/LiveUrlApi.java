// (c)2016 Flipboard Inc, All Rights Reserved.

package com.example.stopgap.mydemo.api;

import com.example.stopgap.mydemo.model.LiveUrlResult;
import com.example.stopgap.mydemo.model.TeamNewsResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LiveUrlApi {
    @GET("data/detail/program/{roomnum}")
    Observable<LiveUrlResult> getLiveUrl(@Path("roomnum") String teamnumber, @Query("type") String type);
}

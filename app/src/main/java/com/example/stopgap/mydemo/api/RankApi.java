
package com.example.stopgap.mydemo.api;
import com.example.stopgap.mydemo.model.RankResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RankApi {
    @GET("data/v1/team_ranking/0")
    Observable<RankResult> getRank(@Query("season_id") String season_id,@Query("version") String version,@Query("refer") String refer,@Query("type") String type);
}



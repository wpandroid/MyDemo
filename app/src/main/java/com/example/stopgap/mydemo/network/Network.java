// (c)2016 Flipboard Inc, All Rights Reserved.

package com.example.stopgap.mydemo.network;


import com.example.stopgap.mydemo.api.LiveApi;
import com.example.stopgap.mydemo.api.LiveUrlApi;
import com.example.stopgap.mydemo.api.RankApi;
import com.example.stopgap.mydemo.api.TeamNewsApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private static TeamNewsApi zhuangbiApi;
    private static RankApi rankApi;
    private static LiveApi liveApi;
    private static LiveUrlApi liveUrlApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    public static TeamNewsApi getTeamNewsApi() {
        if (zhuangbiApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.dongqiudi.com/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            zhuangbiApi = retrofit.create(TeamNewsApi.class);
        }
        return zhuangbiApi;
    }

    public static RankApi getRankApi() {
        if (rankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.dongqiudi.com/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            rankApi = retrofit.create(RankApi.class);
        }
        return rankApi;
    }

    public static LiveApi getLiveApi() {
        if (liveApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.dongqiudi.com/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            liveApi = retrofit.create(LiveApi.class);
        }
        return liveApi;
    }

    public static LiveUrlApi getLiveUrlApi() {
        if (liveUrlApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.dongqiudi.com/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            liveUrlApi = retrofit.create(LiveUrlApi.class);
        }
        return liveUrlApi;
    }


}

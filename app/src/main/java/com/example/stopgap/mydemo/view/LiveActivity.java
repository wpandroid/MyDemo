package com.example.stopgap.mydemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.stopgap.mydemo.R;
import com.example.stopgap.mydemo.model.LiveResult;
import com.example.stopgap.mydemo.model.LiveUrlResult;
import com.example.stopgap.mydemo.network.Network;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class LiveActivity extends SwipeBackActivity {
    protected Disposable disposable;
    @BindView(R.id.videoplayer_live)
    JZVideoPlayerStandard videoplayerLive;
    String liveUrl,title,bgurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        ButterKnife.bind(this);



        Intent intent = getIntent();
        liveUrl = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        bgurl = intent.getStringExtra("bgurl");

        Glide.with(LiveActivity.this)
                .load(bgurl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(videoplayerLive.thumbImageView);

        getLiveUrl();
        /*videoplayerLive.setUp("http://live-hls.dongqiudi.com/dongqiudi/4dcc4e6f12a396189c3d2842822df453.m3u8"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "哈哈哈");
        videoplayerLive.startVideo();*/


    }

    void getLiveUrl() {
        disposable = Network.getLiveUrlApi()
                .getLiveUrl(liveUrl, "live")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LiveUrlResult>() {
                    @Override
                    public void accept(@NonNull LiveUrlResult liveUrlResult) throws Exception {
                        videoplayerLive.setUp(liveUrlResult.living.get(0).resource, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, title);
                        videoplayerLive.startVideo();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        System.out.println("加载失败" + throwable);
                        Toast.makeText(LiveActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();

    }

}

package com.example.stopgap.mydemo;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.CacheUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;
import com.waynell.videolist.widget.TextureVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class VideoActivity extends SwipeBackActivity {

    AgentWeb mAgentWeb;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ln_video)
    LinearLayout lnVideo;
    private Menu menu;
    String URL;

   /* @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;
    @BindView(R.id.video_view)
    TextureVideoView videoView;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//此FLAG可使状态栏透明，且当前视图在绘制时，从屏幕顶端开始即top = 0开始绘制，这也是实现沉浸效果的基础
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//可不加
        }
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (menu != null) {
            for (int i = 0; i < menu.size(); i++) {
                Drawable drawable = menu.getItem(i).getIcon();
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
            }
        }

        Intent intent = getIntent();
        URL = intent.getStringExtra("url");
        System.out.println("URL=" + URL);

        mAgentWeb = AgentWeb.with(this)//传入Activity or Fragment
                .setAgentWebParent(lnVideo, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .closeProgressBar()
                .setReceivedTitleCallback(mCallback)
                .createAgentWeb()//
                .ready()
                .go("https://api.dongqiudi.com/article/"+URL+".html");




       /* videoplayer.setUp("http://live-hls.dongqiudi.com/dongqiudi/4dcc4e6f12a396189c3d2842822df453.m3u8"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "哈哈哈");

        Glide.with(this)
                .load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640")
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(videoplayer.thumbImageView);


        videoView.setVideoPath("http://live-hls.dongqiudi.com/dongqiudi/4dcc4e6f12a396189c3d2842822df453.m3u8");
        videoView.start();
*/
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


    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (toolbar != null)
                toolbar.setTitle(title);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Drawable drawable = item.getIcon();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        if(item.getItemId() == R.id.action_share){
            Intent textIntent = new Intent(Intent.ACTION_SEND);
            textIntent.setType("text/plain");
            textIntent.putExtra(Intent.EXTRA_TEXT, "https://api.dongqiudi.com/article/"+URL+".html");
            startActivity(Intent.createChooser(textIntent, "分享"));
        }

        if(item.getItemId() == R.id.action_copy){
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            // 将文本内容放到系统剪贴板里。
            cm.setText("https://api.dongqiudi.com/article/"+URL+".html");

            Snackbar.make(lnVideo, "复制成功，可以发给朋友们了", 2000)
                    .setAction("Close", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }



}

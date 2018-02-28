package com.example.stopgap.mydemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.stopgap.mydemo.R;
import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by wp on 2017/12/20.
 */

public class SinaRefreshHeader extends FrameLayout implements IHeaderView {

    View rootView;
    TextView refreshTextView;
    AVLoadingIndicatorView loadingView;
    RelativeLayout rl;

    void init() {
        if (rootView == null) {
            rootView = View.inflate(getContext(), R.layout.view_myheader, null);
            refreshTextView = rootView.findViewById(R.id.tv);
            loadingView = rootView.findViewById(R.id.iv);
            rl= rootView.findViewById(R.id.rl);
            addView(rootView);
        }
    }

    public SinaRefreshHeader(@NonNull Context context) {
        super(context);
    }

    @Override
    public View getView() {
        init();
        return this;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        refreshTextView.setVisibility(View.VISIBLE);
        rl.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        if (fraction < 0.9) refreshTextView.setText("⚽ 下拉刷新 ⚽");
        if (fraction > 0.9) refreshTextView.setText("⚽ 松开刷新 ⚽");
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 0.9) {
            refreshTextView.setVisibility(GONE);
            rl.setVisibility(View.GONE);
            loadingView.setVisibility(GONE);
        }

    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        refreshTextView.setVisibility(View.GONE);
        rl.setVisibility(View.GONE);
        loadingView.setVisibility(VISIBLE);
    }

    @Override
    public void onFinish(OnAnimEndListener animEndListener) {
        animEndListener.onAnimEnd();
    }

    @Override
    public void reset() {

    }
}

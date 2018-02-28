package com.example.stopgap.mydemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.stopgap.mydemo.R;
import com.lcodecore.tkrefreshlayout.IBottomView;
import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by wp on 2017/12/20.
 */

public class SinaRefreshFooter extends FrameLayout implements IBottomView {

    View rootView;

    void init() {
        if (rootView == null) {
            rootView = View.inflate(getContext(), R.layout.load_more_footview_layout, null);
            addView(rootView);
        }
    }

    public SinaRefreshFooter(@NonNull Context context) {
        super(context);
    }

    @Override
    public View getView() {
        init();
        return this;
    }

    @Override
    public void onPullingUp(float fraction, float maxBottomHeight, float bottomHeight) {

    }

    @Override
    public void startAnim(float maxBottomHeight, float bottomHeight) {

    }

    @Override
    public void onPullReleasing(float fraction, float maxBottomHeight, float bottomHeight) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void reset() {

    }
}

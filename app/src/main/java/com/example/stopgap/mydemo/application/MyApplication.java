package com.example.stopgap.mydemo.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.example.stopgap.mydemo.util.DiscreteScrollViewOptions;

/**
 * Created by wp on 2017/12/22.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(instance);
        DiscreteScrollViewOptions.init(this);
    }

    public static MyApplication getInstance(){
        return instance;
    }
}

package com.aice.pausemusic;

import android.app.Application;
import android.content.Context;


import androidx.multidex.MultiDex;

/**
 * Date: 2021/2/21
 * Time: 3:36 PM
 * Author: wangxiaoyun
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

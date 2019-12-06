package com.phph.l_demo_x;

import android.app.Application;

import com.phph.network.base.NetWorkApi;

/**
 * Created by v on 2019/10/30.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkApi.init(new FFF(this));

    }
}

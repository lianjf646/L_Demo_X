package com.phph.l_demo_x;

import android.app.Application;

import com.phph.network.base.INetworkRequiredInfo;

/**
 * Created by v on 2019/10/30.
 */
public class FFF implements INetworkRequiredInfo {
    Application application;

    public FFF(Application application) {
        this.application = application;
    }

    @Override
    public String getAppVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    @Override
    public String getAppVersionCode() {
        return String.valueOf(BuildConfig.VERSION_CODE);
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public Application getApplicationContext() {
        return application;
    }
}

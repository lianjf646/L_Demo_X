package com.phph.network.base;

import android.app.Application;

/**
 * 获取网络要求信息
 */
public interface INetworkRequiredInfo {

    String getAppVersionName();// 版本信息

    String getAppVersionCode();// 版本号

    boolean isDebug(); // 是否是测试

    Application getApplicationContext();


}

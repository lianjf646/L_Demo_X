package com.phph.l_demo_x;

import android.app.Application;

import com.phph.db_lib.DBHelper;
import com.phph.network.base.NetWorkApi;

/**
 * Created by v on 2019/10/30.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkApi.init(new FFF(this));
        DBHelper.init(getApplicationContext(), "roomDb");
//        DBHelper.mContext = getApplicationContext();
//        DBHelper.mDbName = "roodDb";
    }
}

package com.phph.diarydemo;

import android.app.Application;

import com.phph.db_lib.DBHelper;

/**
 * Created by v on 2019/12/10.
 */
public class DBApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper.init(getApplicationContext(),"rijiben_db");
    }
}

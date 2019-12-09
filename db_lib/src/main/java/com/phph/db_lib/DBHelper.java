package com.phph.db_lib;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.phph.db_lib.bean.UserBean;
import com.phph.db_lib.dao.UserDao;

/**
 * Created by v on 2019/12/9.
 */
@Database(entities = {UserBean.class}, version = 1, exportSchema = false)
public abstract class DBHelper extends RoomDatabase {

    public static Context mContext;
    public static String mDbName;


    public static class Inner {
        public static DBHelper dbHelper = Room.databaseBuilder(mContext, DBHelper.class, mDbName)//
                .addMigrations(MyMigrations.getUpdateList())//
                .allowMainThreadQueries()//
                .build();
    }

    public static DBHelper getInstance() {
        return Inner.dbHelper;
    }

    public DBHelper() {

    }

    public static void init(Context context, String dbName) {
        mContext = context;
        mDbName = dbName;
    }


    public abstract UserDao userDao();
}

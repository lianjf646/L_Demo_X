package com.phph.db_lib;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.phph.db_lib.bookkeeping.type.BookkeepingTypeBean;
import com.phph.db_lib.bookkeeping.type.BookkeepingTypeDao;
import com.phph.db_lib.converter.DateConverter;
import com.phph.db_lib.converter.TagsConverter;
import com.phph.db_lib.diary.DiaryBean;
import com.phph.db_lib.diary.DiaryDao;
import com.phph.db_lib.model.BookkeepingBean;
import com.phph.db_lib.recovery.RecoverBean;
import com.phph.db_lib.recovery.RecoverDao;
import com.phph.db_lib.type.TypeBean;
import com.phph.db_lib.type.TypeDao;
import com.phph.db_lib.user.UserBean;
import com.phph.db_lib.user.UserDao;

/**
 * Created by v on 2019/12/9.
 */
@Database(entities = {UserBean.class, DiaryBean.class, TypeBean.class, RecoverBean.class, BookkeepingBean.class, BookkeepingTypeBean.class}, version = 1,
        exportSchema = false)
@TypeConverters({DateConverter.class, TagsConverter.class})
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

    public abstract DiaryDao diaryDao();

    public abstract TypeDao typeDao();

    public abstract RecoverDao recoverDao();

    public abstract BookkeepingTypeDao bookkeepingTypeDao();


}

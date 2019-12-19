package com.phph.db_lib.type;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by v on 2019/12/12.
 */
@Entity
public class TypeBean {
    @PrimaryKey(autoGenerate = true) //定义主键
    public int userId;

    @ColumnInfo()
    public String typeName;

}

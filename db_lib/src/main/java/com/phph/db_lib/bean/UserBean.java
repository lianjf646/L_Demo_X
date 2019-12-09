package com.phph.db_lib.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by v on 2019/12/9.
 */
@Entity
public class UserBean implements Serializable {

    @PrimaryKey(autoGenerate = true) //定义主键
    public int userId;

    @ColumnInfo()
    public String name;
    @ColumnInfo()
    public String phone;

}

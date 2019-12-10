package com.phph.db_lib.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

/**
 * Created by v on 2019/12/10.
 */
@Entity
public class DiaryBean {
    @PrimaryKey(autoGenerate = true) //定义主键
    public int userId;

    @ColumnInfo()
    public String title;

    @ColumnInfo()
    public String content;


    public List<String> iamgeList;
    public Date createDate;


}

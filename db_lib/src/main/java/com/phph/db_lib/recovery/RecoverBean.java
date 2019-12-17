package com.phph.db_lib.recovery;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by v on 2019/12/10.
 */
@Entity
public class RecoverBean implements Serializable {
    @PrimaryKey(autoGenerate = true) //定义主键
    public int userId;


    @ColumnInfo()
    public String title;// 标题

    @ColumnInfo()
    public String content;// 内容

    @ColumnInfo()
    public String weather;// 天气

    @ColumnInfo()
    public String typeName;

    public String huabanPathLoc;//画板路径

    public List<String> iamgeList;
    public Date createDate;

    public String year_mouth_day;

}

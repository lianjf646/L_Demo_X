package com.phph.db_lib.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * Created by v on 2019/12/19.
 */
@Entity
public class BookkeepingBean {

    @PrimaryKey(autoGenerate = true) //定义主键
    public int id;

    public Date createDate;

    public int payType;

    public int money;

}

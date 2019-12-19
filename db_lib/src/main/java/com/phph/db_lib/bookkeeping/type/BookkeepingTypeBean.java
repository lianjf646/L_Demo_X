package com.phph.db_lib.bookkeeping.type;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created by v on 2019/12/19.
 */
@Entity(indices = {@Index(value = {"typeName"}, unique = true)})
public class BookkeepingTypeBean {

    @PrimaryKey(autoGenerate = true) //定义主键
    public int typeId;

    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

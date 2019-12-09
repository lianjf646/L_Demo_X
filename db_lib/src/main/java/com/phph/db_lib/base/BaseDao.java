package com.phph.db_lib.base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;

/**
 * Created by v on 2019/12/9.
 */
@Dao
public interface BaseDao<T> {

    @Insert
    void insertItem(T item);//插入单条数据

    @Insert
    void insertItems(T... t);//插入list数据

    @Delete
    void deleteItem(T item);//删除item

    @Delete
    void deleteListItem(List<T> itemList);//删除item

    @Update
    void updateItem(T item);//更新item

}

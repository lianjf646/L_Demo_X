package com.phph.db_lib.base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

/**
 * Android ROOM Developers官方文档翻译：
 * https://kevinwu.cn/p/717ed5d8/#%E4%BD%BF%E7%94%A8Room%E7%9A%84DAOs%E8%8E%B7%E5%8F%96%E6%95%B0%E6%8D%AE
 * Created by v on 2019/12/9.
 */
@Dao
public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReplaceItems(T... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReplaceItemList(List<T> itemList);

    @Insert
    void insertItems(T... item);//插入list数据

    @Insert
    void insertItemList(List<T> itemList);//插入单条数据

    @Delete
    void deleteItems(T... items);//删除item

    @Delete
    void deleteItemList(List<T> itemList);//删除item

    @Update
    void updateItem(T item);//更新item

}

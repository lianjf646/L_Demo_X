package com.phph.db_lib.type;

import androidx.room.Dao;
import androidx.room.Query;

import com.phph.db_lib.base.BaseDao;

import java.util.List;

/**
 * Created by v on 2019/12/12.
 */
@Dao
public interface TypeDao extends BaseDao<TypeBean> {
    @Query("SELECT * FROM  typeBean")
    List<TypeBean> getAll();


}

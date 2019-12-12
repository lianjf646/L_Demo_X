package com.phph.db_lib.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.phph.db_lib.base.BaseDao;
import com.phph.db_lib.bean.TypeBean;

import java.util.List;

/**
 * Created by v on 2019/12/12.
 */
@Dao
public interface TypeDao extends BaseDao<TypeBean> {
    @Query("SELECT * FROM  typeBean")
    List<TypeBean> getAll();


}

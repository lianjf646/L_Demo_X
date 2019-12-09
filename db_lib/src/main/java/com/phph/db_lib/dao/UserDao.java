package com.phph.db_lib.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.phph.db_lib.base.BaseDao;
import com.phph.db_lib.bean.UserBean;

import java.util.List;

/**
 * Created by v on 2019/12/9.
 */
@Dao
public interface UserDao extends BaseDao<UserBean> {
    @Query("SELECT * FROM UserBean WHERE name = :name")
    UserBean selectVo(String name);

    @Query("SELECT * FROM UserBean WHERE name = :name")
    List<UserBean> selectList(String name);


}

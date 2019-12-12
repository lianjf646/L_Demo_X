package com.phph.db_lib.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.phph.db_lib.base.BaseDao;
import com.phph.db_lib.bean.DiaryBean;

import java.util.List;

/**
 * Created by v on 2019/12/10.
 */
@Dao
public interface DiaryDao extends BaseDao<DiaryBean> {
    @Query("SELECT * FROM  diaryBean")
    List<DiaryBean> getAll();


}

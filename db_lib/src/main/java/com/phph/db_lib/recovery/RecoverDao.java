package com.phph.db_lib.recovery;

import androidx.room.Dao;
import androidx.room.Query;

import com.phph.db_lib.base.BaseDao;

import java.util.List;

/**
 * Created by v on 2019/12/17.
 */
@Dao
public interface RecoverDao extends BaseDao<RecoverBean> {
    /**
     * @return 获取全部列表数据
     */
    @Query("SELECT * FROM  diaryBean")
    List<RecoverBean> getAll();

}

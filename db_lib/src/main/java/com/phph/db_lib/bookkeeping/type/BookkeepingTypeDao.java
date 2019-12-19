package com.phph.db_lib.bookkeeping.type;

import androidx.room.Dao;
import androidx.room.Query;

import com.phph.db_lib.base.BaseDao;

import java.util.List;

/**
 * Created by v on 2019/12/19.
 */
@Dao
public interface BookkeepingTypeDao extends BaseDao<BookkeepingTypeBean> {

    /**
     * @return 获取全部列表数据
     */
    @Query("SELECT * FROM  bookkeepingTypeBean")
    List<BookkeepingTypeBean> getAll();

}

package com.phph.db_lib.diary;

import androidx.room.Dao;
import androidx.room.Query;

import com.phph.db_lib.base.BaseDao;

import java.util.List;

/**
 * Created by v on 2019/12/10.
 */
@Dao
public interface DiaryDao extends BaseDao<DiaryBean> {

    /**
     * @return 获取全部列表数据
     */
    @Query("SELECT * FROM  diaryBean")
    List<DiaryBean> getAll();

    /**
     * 获取数据库表中总条数
     *
     * @return
     */
    @Query("SELECT count(*) FROM diaryBean WHERE typeName = :typeName")
    int getTypeNumber(String typeName);

    /**
     * 获取数据库表中总条数
     *
     * @return
     */
    @Query("SELECT  * FROM diaryBean WHERE typeName = :typeName")
    List<DiaryBean> getTypeNameList(String typeName);
}

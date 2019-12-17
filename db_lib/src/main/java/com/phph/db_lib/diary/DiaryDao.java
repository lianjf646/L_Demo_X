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
     * 查找DiaryBean
     * Desc 降序
     *
     * @return
     */
    @Query("SELECT * FROM diaryBean ORDER BY createDate DESC")
    List<DiaryBean> getAllDesc();

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

    /**
     * 模糊搜 与非模糊搜索 LIKE 后面添加 '%'||
     * "SELECT * FROM tb_use WHERE Name LIKE '%' || :name" || '%'
     * 或者传进来的字符串 就完成拼接 "%search%"
     *
     * @param search
     * @return
     */
    @Query("SELECT * FROM diaryBean WHERE title LIKE :search OR content LIKE :search")
    List<DiaryBean> findTitleWithContent(String search);


    /**
     * 模糊搜
     *
     * @param search
     * @return
     */
    @Query("SELECT * FROM diaryBean WHERE title LIKE '%' ||:search || '%'")
    List<DiaryBean> findTitle(String search);

}

package com.phph.diarydemo.activity;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.DBHelper;
import com.phph.db_lib.diary.DiaryBean;
import com.phph.db_lib.recovery.RecoverBean;
import com.phph.diarydemo.R;
import com.phph.diarydemo.adapter.RecoverAdapter;
import com.phph.x_support_lib.base.BaseActivity;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;
import com.phph.x_support_lib.util.ListUtils;

import java.util.List;

/**
 * Created by v on 2019/12/17.
 */
public class RecoverActivity extends BaseActivity {

    private ImageView iv_back;
    private ImageView iv_del_all;
    private ImageView iv_not_data;

    private RecyclerView recycler;
    private RecoverAdapter recoverAdapter;
    List<RecoverBean> recoverBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recover;
    }

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_del_all = findViewById(R.id.iv_del_all);
        iv_del_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示:");
                builder.setMessage("是否清空回收站?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper.getInstance().recoverDao().delAll();
                        recoverBeanList = DBHelper.getInstance().recoverDao().getAll();
                        recoverAdapter.setTList(recoverBeanList);
                        iv_not_data.setVisibility(View.VISIBLE);
                    }
                });
                builder.setNeutralButton("取消", null);
                builder.show();

            }
        });
        iv_not_data = findViewById(R.id.iv_not_data);
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        recoverAdapter = new RecoverAdapter(this);
        recycler.setAdapter(recoverAdapter);
        recoverAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                final RecoverBean bean = recoverBeanList.get(pos);
                final String[] weatherString = new String[]{"恢复", "删除"};
                AlertDialog alertDialog = new AlertDialog.Builder(context).setItems(weatherString, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {
                            DiaryBean diaryBean = new DiaryBean();
                            diaryBean.title = bean.title;
                            diaryBean.content = bean.content;
                            diaryBean.weather = bean.weather;
                            diaryBean.typeName = bean.typeName;
                            diaryBean.huabanPathLoc = bean.huabanPathLoc;
                            diaryBean.iamgeList = bean.iamgeList;
                            diaryBean.createDate = bean.createDate;
                            diaryBean.year_mouth_day = bean.year_mouth_day;
                            DBHelper.getInstance().diaryDao().insertItem(diaryBean);

                        }
                        recoverBeanList.remove(bean);
                        DBHelper.getInstance().recoverDao().deleteItem(bean);
                        recoverAdapter.setTList(recoverBeanList);
                        if (ListUtils.isEmpty(recoverBeanList)) {
                            iv_not_data.setVisibility(View.VISIBLE);
                        } else {
                            iv_not_data.setVisibility(View.GONE);
                        }

                    }
                }).setTitle("提示:").create();
                alertDialog.show();

            }
        });
    }

    @Override
    protected void initData() {
        recoverBeanList = DBHelper.getInstance().recoverDao().getAll();
        recoverAdapter.setTList(recoverBeanList);
        if (ListUtils.isEmpty(recoverBeanList)) {
            iv_not_data.setVisibility(View.VISIBLE);
        } else {
            iv_not_data.setVisibility(View.GONE);
        }
    }
}

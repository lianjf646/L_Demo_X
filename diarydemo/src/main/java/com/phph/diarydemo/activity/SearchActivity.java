package com.phph.diarydemo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.DBHelper;
import com.phph.db_lib.diary.DiaryBean;
import com.phph.diarydemo.R;
import com.phph.diarydemo.adapter.MainAdapter;
import com.phph.x_support_lib.base.BaseActivity;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;
import com.phph.x_support_lib.util.ListUtils;

import java.util.List;

/**
 * Created by v on 2019/12/17.
 */
public class SearchActivity extends BaseActivity {

    private List<DiaryBean> diaryBeans;
    private RecyclerView recycler;
    private MainAdapter mainAdapter;

    private EditText et_search;
    private ImageView iv_not_data;
    private ImageView iv_search;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        iv_search = findViewById(R.id.iv_search);
        et_search = findViewById(R.id.et_search);
        iv_not_data = findViewById(R.id.iv_not_data);
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaryBeans = DBHelper.getInstance().diaryDao().findTitleWithContent("%" + et_search.getText().toString().trim() + "%");
                mainAdapter.setTList(diaryBeans);
                if (ListUtils.isEmpty(diaryBeans)) {
                    iv_not_data.setVisibility(View.VISIBLE);
                } else {
                    iv_not_data.setVisibility(View.GONE);
                }
            }
        });


        recycler = findViewById(R.id.recycler);
        mainAdapter = new MainAdapter(context);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(mainAdapter);
        mainAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                DiaryBean bean = mainAdapter.getItemData(pos);
                startActivity(new Intent(context, LooKDiaryDetailActivity.class).putExtra("DiaryBean", bean));
            }
        });
        mainAdapter.setOnLongClickListener(new MainAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(int pos) {
//                DiaryBean bean = mainAdapter.getItemData(pos);
//                DBHelper.getInstance().diaryDao().deleteItem(bean);
//                diaryBeans.remove(bean);
//                mainAdapter.notifyItemRemoved(pos);
//                mainAdapter.notifyItemRangeRemoved(pos, mainAdapter.getItemCount());
//                if (ListUtils.isEmpty(diaryBeans)) {
//                    iv_not_data.setVisibility(View.VISIBLE);
//                } else {
//                    iv_not_data.setVisibility(View.GONE);
//                }

            }
        });
    }


    @Override
    protected void initData() {

    }
}

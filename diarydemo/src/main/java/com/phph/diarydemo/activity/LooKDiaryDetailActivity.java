package com.phph.diarydemo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.diary.DiaryBean;
import com.phph.diarydemo.R;
import com.phph.diarydemo.adapter.LooKDiaryDetailAdapter;
import com.phph.x_support_lib.base.BaseActivity;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;

/**
 * Created by v on 2019/12/13.
 */
public class LooKDiaryDetailActivity extends BaseActivity {

    private TextView tv_title;
    private String typeName;
    private RecyclerView recycler;
    private LooKDiaryDetailAdapter adapter;
    private DiaryBean bean;
    private TextView tv_go_share;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lookdiarydetail;
    }

    @Override
    protected void initView() {
        tv_title = findViewById(R.id.tv_title);
        recycler = findViewById(R.id.recycler);
        adapter = new LooKDiaryDetailAdapter(context);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                startActivity(new Intent(context, DiaryShareActivity.class).putExtra("DiaryBean",bean));
            }
        });
        recycler.setLayoutManager(new GridLayoutManager(context, 2));
        recycler.setAdapter(adapter);

        tv_go_share = findViewById(R.id.tv_go_share);
        tv_go_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, DiaryShareActivity.class).putExtra("DiaryBean",bean));
            }
        });
    }

    @Override
    protected void initData() {
        bean = (DiaryBean) getIntent().getSerializableExtra("DiaryBean");
        tv_title.setText(bean.title);
        adapter.setTList(bean.iamgeList);
    }
}

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
import com.phph.x_support_lib.helper.DateHelper;

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
    private TextView tv_year_mooth_week;
    private TextView tv_time;
    private TextView tv_content;
    private TextView tv_day;


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
                startActivity(new Intent(context, DiaryShareActivity.class).putExtra("DiaryBean", bean));
            }
        });
        recycler.setLayoutManager(new GridLayoutManager(context, 2));
        recycler.setAdapter(adapter);

        tv_go_share = findViewById(R.id.tv_go_share);
        tv_go_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, DiaryShareActivity.class).putExtra("DiaryBean", bean));
            }
        });

        tv_year_mooth_week = findViewById(R.id.tv_year_mooth_week);
        tv_time = findViewById(R.id.tv_time);
        tv_content = findViewById(R.id.tv_content);
        tv_day = findViewById(R.id.tv_day);

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        bean = (DiaryBean) getIntent().getSerializableExtra("DiaryBean");
        tv_title.setText(bean.title);
        adapter.setTList(bean.iamgeList);
        tv_year_mooth_week.setText(DateHelper.getInstance().getDateStr(bean.createDate, "yyyy年MM月"));
        tv_year_mooth_week.append("/");
        tv_year_mooth_week.append(DateHelper.getInstance().getWeekOfDate(bean.createDate));
        tv_day.setText(DateHelper.getInstance().getDateStr(bean.createDate, "dd号"));
        tv_time.setText(DateHelper.getInstance().getDateStr(bean.createDate, "HH:mm"));
        tv_content.setText(bean.content);
    }
}

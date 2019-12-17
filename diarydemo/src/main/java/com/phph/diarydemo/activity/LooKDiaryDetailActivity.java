package com.phph.diarydemo.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.DBHelper;
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
    private RecyclerView recycler;
    private LooKDiaryDetailAdapter adapter;
    private int id;
    private TextView tv_go_share;
    private TextView tv_year_mooth_week;
    private TextView tv_time;
    private TextView tv_content;
    private TextView tv_day;
    private ImageView iv_copy;
    private ImageButton ibtn_updata;
    DiaryBean bean;

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
                startActivity(new Intent(context, DiaryShareActivity.class).putExtra("id", bean.userId));
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
        iv_copy = findViewById(R.id.iv_copy);
        iv_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", bean.content);
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                Toast.makeText(activity, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
        ibtn_updata = findViewById(R.id.ibtn_go_updata);
        ibtn_updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, WriteDiaryActivity.class).putExtra("id", bean.userId));
            }
        });
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        id = getIntent().getIntExtra("id", 0);
        bean = DBHelper.getInstance().diaryDao().findId(id);
        if (bean == null) return;
        tv_title.setText(bean.title);
        if (!TextUtils.isEmpty(bean.huabanPathLoc)) {
            bean.iamgeList.add(bean.huabanPathLoc);
        }
        adapter.setTList(bean.iamgeList);
        tv_year_mooth_week.setText(DateHelper.getInstance().getDateStr(bean.createDate, "yyyy年MM月"));
        tv_year_mooth_week.append("/");
        tv_year_mooth_week.append(DateHelper.getInstance().getWeekOfDate(bean.createDate));
        tv_day.setText(DateHelper.getInstance().getDateStr(bean.createDate, "dd号"));
        tv_time.setText(DateHelper.getInstance().getDateStr(bean.createDate, "HH:mm"));
        tv_content.setText(bean.content);
    }
}

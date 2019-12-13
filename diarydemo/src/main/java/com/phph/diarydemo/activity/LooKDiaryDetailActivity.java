package com.phph.diarydemo.activity;

import android.widget.TextView;

import com.phph.diarydemo.R;
import com.phph.x_support_lib.base.BaseActivity;

/**
 * Created by v on 2019/12/13.
 */
public class LooKDiaryDetailActivity extends BaseActivity {

    private TextView tv_title;
    private String typeName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lookdiarydetail;
    }

    @Override
    protected void initView() {
        tv_title = findViewById(R.id.tv_title);
    }

    @Override
    protected void initData() {
        typeName = getIntent().getStringExtra("typeName");
        tv_title.setText(typeName);
    }
}

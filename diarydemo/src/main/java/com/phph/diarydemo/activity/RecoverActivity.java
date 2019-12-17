package com.phph.diarydemo.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.phph.db_lib.DBHelper;
import com.phph.diarydemo.R;
import com.phph.x_support_lib.base.BaseActivity;

/**
 * Created by v on 2019/12/17.
 */
public class RecoverActivity extends BaseActivity {

    private ImageView iv_back;

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
    }

    @Override
    protected void initData() {
        Toast.makeText(activity, "" + DBHelper.getInstance().recoverDao().getAll().size(), Toast.LENGTH_SHORT).show();

    }
}

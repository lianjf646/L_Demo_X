package com.phph.diarydemo.activity;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.diarydemo.R;
import com.phph.x_support_lib.base.BaseActivity;

/**
 * Created by v on 2019/12/10.
 */
public class MainDbActivity extends BaseActivity {


    private DrawerLayout drawerLayout;
    private ImageView ivMy;

    private RecyclerView recycler;
    private ImageView ibtn_go_write;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_maindb;
    }

    @Override
    protected void initView() {
        recycler = findViewById(R.id.recycler);
        drawerLayout = findViewById(R.id.dl);

        ivMy = findViewById(R.id.iv_my);
        ivMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        ibtn_go_write = findViewById(R.id.ibtn_go_write);
        ibtn_go_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, WriteDiaryActivity.class));
            }
        });
    }

    @Override
    protected void initData() {

    }

}

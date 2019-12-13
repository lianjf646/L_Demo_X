package com.phph.diarydemo.activity;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.DBHelper;
import com.phph.diarydemo.R;
import com.phph.diarydemo.adapter.MainAdapter;
import com.phph.x_support_lib.base.BaseActivity;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;

public class TypeListActivity extends BaseActivity {

    private TextView tv_title;
    private String typeName;

    private RecyclerView recycler;
    private MainAdapter mainAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_type_list;
    }

    @Override
    protected void initView() {
        tv_title = findViewById(R.id.tv_title);

        recycler = findViewById(R.id.recycler);
        initAnim();
        mainAdapter = new MainAdapter(this);
        mainAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                startActivity(new Intent(context, LooKDiaryDetailActivity.class).putExtra("typeName", typeName));
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(mainAdapter);
    }

    @Override
    protected void initData() {
        typeName = getIntent().getStringExtra("typeName");
        tv_title.setText(typeName);

        mainAdapter.setTList(DBHelper.getInstance().diaryDao().getTypeNameList(typeName));

    }

    private void initAnim() {
        //通过加载XML动画设置文件来创建一个Animation对象；
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.list_animation_in);
        //得到一个LayoutAnimationController对象；
        LayoutAnimationController lac = new LayoutAnimationController(animation);
        //设置控件显示的顺序；
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //设置控件显示间隔时间；
        lac.setDelay(0.5f);
        //为ListView设置LayoutAnimationController属性；
        recycler.setLayoutAnimation(lac);

    }
}

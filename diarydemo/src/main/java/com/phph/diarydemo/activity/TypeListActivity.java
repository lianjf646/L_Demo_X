package com.phph.diarydemo.activity;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

public class TypeListActivity extends BaseActivity {

    private TextView tv_title;
    private String typeName;

    private RecyclerView recycler;
    private MainAdapter mainAdapter;

    private ImageButton ibtn_go_write;

    private ImageView iv_not_data;
    private ImageView iv_back;


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
                DiaryBean bean = mainAdapter.getItemData(pos);
                startActivity(new Intent(context, LooKDiaryDetailActivity.class).putExtra("id", bean.userId));
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(mainAdapter);
        ibtn_go_write = findViewById(R.id.ibtn_go_write);
        ibtn_go_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, WriteDiaryActivity.class));
            }
        });
        iv_not_data = findViewById(R.id.iv_not_data);
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
        typeName = getIntent().getStringExtra("typeName");
        tv_title.setText(typeName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<DiaryBean> diaryBeanList = DBHelper.getInstance().diaryDao().getTypeNameList(typeName);
        mainAdapter.setTList(diaryBeanList);
        if (ListUtils.isEmpty(diaryBeanList)) {
            iv_not_data.setVisibility(View.VISIBLE);
        } else {
            iv_not_data.setVisibility(View.GONE);
        }

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

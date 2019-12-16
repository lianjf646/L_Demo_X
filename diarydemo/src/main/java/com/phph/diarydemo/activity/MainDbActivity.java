package com.phph.diarydemo.activity;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.DBHelper;
import com.phph.db_lib.diary.DiaryBean;
import com.phph.diarydemo.R;
import com.phph.diarydemo.adapter.MainAdapter;
import com.phph.x_support_lib.base.BaseActivity;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by v on 2019/12/10.
 */
public class MainDbActivity extends BaseActivity {


    private DrawerLayout drawerLayout;
    private ImageView ivMy;

    private RecyclerView recycler;
    private MainAdapter mainAdapter;

    private ImageView ibtn_go_write;
    private TextView tv_type;

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

        tv_type = findViewById(R.id.tv_type);
        tv_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, TypeActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        initAnim();
        mainAdapter = new MainAdapter(this);
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
                DiaryBean bean = mainAdapter.getItemData(pos);
                DBHelper.getInstance().diaryDao().deleteItem(bean);
                diaryBeans.remove(bean);

                mainAdapter.notifyItemRemoved(pos);
                mainAdapter.notifyItemRangeRemoved(pos,mainAdapter.getItemCount());
            }
        });

    }
    List<DiaryBean> diaryBeans;
    @Override
    protected void onResume() {
        super.onResume();
      diaryBeans = DBHelper.getInstance().diaryDao().getAll();
        if (diaryBeans != null) {
            mainAdapter.setTList(diaryBeans);
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

    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}

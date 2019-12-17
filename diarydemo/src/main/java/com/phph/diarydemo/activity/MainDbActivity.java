package com.phph.diarydemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.DBHelper;
import com.phph.db_lib.diary.DiaryBean;
import com.phph.db_lib.recovery.RecoverBean;
import com.phph.diarydemo.R;
import com.phph.diarydemo.adapter.MainAdapter;
import com.phph.x_support_lib.base.BaseActivity;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;
import com.phph.x_support_lib.helper.DateHelper;
import com.phph.x_support_lib.util.ListUtils;

import java.util.Date;
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
    private ImageView iv_search;
    private ImageView iv_not_data;
    private ImageView iv_set;
    private TextView tv_date;
    private TextView tv_time;

    private TextView tv_recover;

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

        iv_search = findViewById(R.id.iv_search);
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, SearchActivity.class));
            }
        });
        iv_not_data = findViewById(R.id.iv_not_data);

        iv_set = findViewById(R.id.iv_set);
        iv_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] weatherString = new String[]{"按时间顺序", "按时间逆序", "打码日记内容", "显示日记内容"};
                AlertDialog alertDialog = new AlertDialog.Builder(context).setItems(weatherString, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        weather = weatherString[which];
//                        Toast.makeText(activity, "" + weather, Toast.LENGTH_SHORT).show();
                    }
                }).setTitle("设置").create();
                alertDialog.show();
            }
        });
        tv_date = findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);
        tv_recover = findViewById(R.id.tv_recover);
        tv_recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, RecoverActivity.class));
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
                startActivity(new Intent(context, LooKDiaryDetailActivity.class).putExtra("id", bean.userId));
            }
        });
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        mainAdapter.setOnLongClickListener(new MainAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(final int pos) {


                dialog.setTitle("提示:")//
                        .setMessage("是否放入回收站")//
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DiaryBean bean = mainAdapter.getItemData(pos);
                                DBHelper.getInstance().diaryDao().deleteItem(bean);
                                RecoverBean recoverBean = new RecoverBean();
                                recoverBean.title = bean.title;
                                recoverBean.content = bean.content;
                                recoverBean.weather = bean.weather;
                                recoverBean.typeName = bean.typeName;
                                recoverBean.huabanPathLoc = bean.huabanPathLoc;
                                recoverBean.iamgeList = bean.iamgeList;
                                recoverBean.createDate = bean.createDate;
                                recoverBean.year_mouth_day = bean.year_mouth_day;
                                DBHelper.getInstance().recoverDao().insertItem(recoverBean);
                                diaryBeans.remove(bean);
                                mainAdapter.notifyItemRemoved(pos);
                                mainAdapter.notifyItemRangeRemoved(pos, mainAdapter.getItemCount());
                                if (ListUtils.isEmpty(diaryBeans)) {
                                    iv_not_data.setVisibility(View.VISIBLE);
                                } else {
                                    iv_not_data.setVisibility(View.GONE);
                                }
                            }
                        })//
                        .setNeutralButton("取消", null)//
                        .show();

            }
        });

    }

    List<DiaryBean> diaryBeans;


    @Override
    protected void onResume() {
        super.onResume();
        Date date = new Date(System.currentTimeMillis());
        tv_date.setText(DateHelper.getInstance().getDateStr(date, "MM月dd日"));
        tv_time.setText(DateHelper.getInstance().getDateStr(date, "HH:mm"));
        diaryBeans = DBHelper.getInstance().diaryDao().getAllDesc();
        if (diaryBeans != null) {
            mainAdapter.setTList(diaryBeans);
        }
        if (ListUtils.isEmpty(diaryBeans)) {
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

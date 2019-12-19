package com.phph.diarydemo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.viewpager2.widget.ViewPager2;

import com.phph.diarydemo.R;
import com.phph.diarydemo.adapter.BookkeepingAdapter;
import com.phph.x_support_lib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v on 2019/12/18.
 */


public class BookkeepingActivity extends BaseActivity {
    private ImageView iv_back;
    private ViewPager2 viewpager2;
    private Button btn_add_go_type;

    private BookkeepingAdapter bookkeepingAdapter;
    private List<String> stringList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_booking;
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
        viewpager2 = findViewById(R.id.viewpager2);
        viewpager2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        bookkeepingAdapter = new BookkeepingAdapter(context);
        viewpager2.setAdapter(bookkeepingAdapter);
        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (bookkeepingAdapter.getItemCount() - 1 == position) {
                    stringList.add("12");
                    bookkeepingAdapter.notifyDataSetChanged();
                }
            }
        });

        btn_add_go_type = findViewById(R.id.btn_add_go_type);
        btn_add_go_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, BookkeepingTypeListActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        bookkeepingAdapter.setTList(stringList);
    }
}

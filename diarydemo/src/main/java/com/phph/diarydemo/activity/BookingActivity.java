package com.phph.diarydemo.activity;

import android.view.View;
import android.widget.ImageView;

import androidx.viewpager2.widget.ViewPager2;

import com.phph.diarydemo.R;
import com.phph.diarydemo.adapter.ViewPagerFragmentStateAdapter;
import com.phph.x_support_lib.base.BaseActivity;

/**
 * Created by v on 2019/12/18.
 */


public class BookingActivity extends BaseActivity {
    private ImageView iv_back;
    private ViewPager2 viewpager2;
    private ViewPagerFragmentStateAdapter adapter;

    private int count = 1;

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
        adapter = new ViewPagerFragmentStateAdapter(getSupportFragmentManager());
        viewpager2.setAdapter(adapter);
        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position == 0) {
                    adapter.setCount(count++);
//                    adapter.registerAdapterDataObserver(new Obser);
//                    adapter.notifyDataSetChanged();
//                    adapter.no
                }

            }
        });

    }

    @Override
    protected void initData() {

    }
}

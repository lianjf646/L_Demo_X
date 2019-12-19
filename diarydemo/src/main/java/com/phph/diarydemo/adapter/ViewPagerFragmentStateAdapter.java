package com.phph.diarydemo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.phph.diarydemo.fragment.BookingFragment;

/**
 * Created by v on 2019/12/18.
 */
public class ViewPagerFragmentStateAdapter extends FragmentStateAdapter {

    private int count = 1;


    public void setCount(int count) {
        this.count = count;
    }

    public ViewPagerFragmentStateAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return BookingFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return count;
    }
}

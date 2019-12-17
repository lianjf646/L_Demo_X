package com.phph.x_support_lib.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by v on 2019/12/13.
 */
public class SSRecyclerView  extends RecyclerView {

    public SSRecyclerView(@NonNull Context context) {
        super(context);
    }

    public SSRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    /**
     * 重写该方法、达到使ListView适应ScrollView的效果
     */ protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

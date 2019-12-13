package com.phph.x_support_lib.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by v on 2019/12/13.
 */
public abstract class BaseRecyclerViewHolder<T extends Object> extends RecyclerView.ViewHolder {

    public BaseRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    protected abstract  void showView(T t, int position);


}

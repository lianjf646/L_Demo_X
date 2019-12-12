package com.phph.diarydemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.bean.TypeBean;
import com.phph.diarydemo.R;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;

/**
 * Created by v on 2019/12/12.
 */
public class TypeAdapter extends BaseRecyclerAdapter<TypeBean, TypeAdapter.ViewHolder> {

    public TypeAdapter(Context context) {
        super(context);
    }

    @Override
    protected TypeAdapter.ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_type_adapter;
    }

    @Override
    protected void onBindChildViewHolder(ViewHolder childHolder, TypeBean bean, int position) {
        childHolder.tv_title.setText(bean.typeName);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}

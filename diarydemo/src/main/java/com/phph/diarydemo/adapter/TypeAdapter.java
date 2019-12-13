package com.phph.diarydemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.diarydemo.R;
import com.phph.diarydemo.bean.TypeListBean;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;

/**
 * Created by v on 2019/12/12.
 */
public class TypeAdapter extends BaseRecyclerAdapter<TypeListBean, TypeAdapter.ViewHolder> {

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
    protected void onBindChildViewHolder(ViewHolder childHolder, TypeListBean bean, final int position) {
        childHolder.tv_title.setText(bean.typeName);
        childHolder.tv_num11.setText(String.valueOf(bean.total));
        childHolder.tv_num11.append("篇");
        childHolder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener == null) return;
                onItemClickListener.onClick(position);
            }
        });


    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public TextView tv_num11;
        public LinearLayout linear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_num11 = itemView.findViewById(R.id.tv_num11);
            linear = itemView.findViewById(R.id.linear);
        }
    }
}

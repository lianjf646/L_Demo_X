package com.phph.diarydemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.phph.diarydemo.R;
import com.phph.diarydemo.bean.TypeListBean;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;
import com.phph.x_support_lib.base.BaseRecyclerViewHolder;

/**
 * Created by v on 2019/12/12.
 */
public class TypeAdapter extends BaseRecyclerAdapter<TypeListBean, TypeAdapter.ViewHolder> {
    public TypeAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_type_adapter;
    }

    @Override
    protected TypeAdapter.ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }


    class ViewHolder extends BaseRecyclerViewHolder<TypeListBean> {
        public TextView tv_title;
        public TextView tv_num11;
        public LinearLayout linear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_num11 = itemView.findViewById(R.id.tv_num11);
            linear = itemView.findViewById(R.id.linear);
        }

        @Override
        protected void showView(TypeListBean typeListBean, final int position) {
            tv_title.setText(typeListBean.typeName);
            tv_num11.setText(String.valueOf(typeListBean.total));
            tv_num11.append("篇");
            linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener == null) return;
                    onItemClickListener.onClick(position);

                }
            });
        }
    }
}




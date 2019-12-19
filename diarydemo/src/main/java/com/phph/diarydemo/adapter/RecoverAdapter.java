package com.phph.diarydemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.phph.db_lib.recovery.RecoverBean;
import com.phph.diarydemo.R;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;
import com.phph.x_support_lib.base.BaseRecyclerViewHolder;
import com.phph.x_support_lib.helper.DateHelper;

/**
 * Created by v on 2019/12/18.
 */
public class RecoverAdapter extends BaseRecyclerAdapter<RecoverBean, RecoverAdapter.ViewHolder> {


    public RecoverAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_recover_adapter;
    }

    @Override
    protected ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseRecyclerViewHolder<RecoverBean> {
        private TextView tv_year_mooth_day;
        private TextView tv_title;
        private LinearLayout linear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_year_mooth_day = itemView.findViewById(R.id.tv_year_mooth_day);
            tv_title = itemView.findViewById(R.id.tv_title);
            linear = itemView.findViewById(R.id.linear);
        }

        @Override
        protected void showView(RecoverBean recoverBean, final int position) {
            tv_year_mooth_day.setText(DateHelper.getInstance().getDateStr(recoverBean.createDate, "yyyy-MM-dd HH:mm"));
            tv_title.setText(recoverBean.title);
            linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(position);
                }
            });

        }
    }
}

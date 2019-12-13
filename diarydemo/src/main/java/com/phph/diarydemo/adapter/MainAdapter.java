package com.phph.diarydemo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.diary.DiaryBean;
import com.phph.diarydemo.R;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;
import com.phph.x_support_lib.base.BaseRecyclerViewHolder;
import com.phph.x_support_lib.helper.DateHelper;

/**
 * Created by v on 2019/12/10.
 */
public class MainAdapter extends BaseRecyclerAdapter<DiaryBean, MainAdapter.ViewHolder> {


    public MainAdapter(Context context) {
        super(context);
    }


    @Override
    protected int getLayout() {
        return R.layout.item_main_adapter;
    }

    @Override
    protected ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }


    public class ViewHolder extends BaseRecyclerViewHolder<DiaryBean> {

        public TextView tvLeftTop;
        public TextView tvLeftBottom;
        public TextView tvTitle;
        public TextView tvContent;
        public RecyclerView recycler;
        public TextView tvTime;

        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvLeftTop = (TextView) itemView.findViewById(R.id.tv_left_top);
            this.tvLeftBottom = (TextView) itemView.findViewById(R.id.tv_left_bottom);
            this.tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            this.tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            this.recycler = itemView.findViewById(R.id.recycler);
            this.tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            linearLayout = itemView.findViewById(R.id.linear);
            recycler.setLayoutManager(new GridLayoutManager(context, 3));
        }

        @Override
        protected  void showView(DiaryBean o, final int pos) {
            if (TextUtils.isEmpty(o.title)) {
                tvTitle.setVisibility(View.GONE);
            } else {
                tvTitle.setVisibility(View.VISIBLE);
            }

            if (TextUtils.isEmpty(o.content)) {
                tvContent.setVisibility(View.GONE);
            } else {
                tvContent.setVisibility(View.VISIBLE);
            }
            tvTitle.setText(o.title);
            tvContent.setText(o.content);
            tvLeftTop.setText(DateHelper.getInstance().getDateStr(o.createDate, "dd"));
            tvLeftBottom.setText(DateHelper.getInstance().getDateStr(o.createDate, "MM月"));
            tvLeftBottom.append("/");
            tvLeftBottom.append(DateHelper.getInstance().getWeekOfDate(o.createDate));
            tvTime.setText(DateHelper.getInstance().getDateStr(o.createDate, "HH:mm"));
            MainChildAdapter adapter = new MainChildAdapter(context);
            adapter.setStringList(o.iamgeList);
            recycler.setAdapter(adapter);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener == null) return;
                    onItemClickListener.onClick(pos);
                }
            });
        }
    }
}

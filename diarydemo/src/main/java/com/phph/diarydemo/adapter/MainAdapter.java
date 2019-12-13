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
import com.phph.x_support_lib.helper.DateHelper;

/**
 * Created by v on 2019/12/10.
 */
public class MainAdapter extends BaseRecyclerAdapter<DiaryBean, MainAdapter.ViewHolder> {


    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindChildViewHolder(ViewHolder childHolder, DiaryBean bean, final int position) {

        if (TextUtils.isEmpty(bean.title)) {
            childHolder.tvTitle.setVisibility(View.GONE);
        } else {
            childHolder.tvTitle.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(bean.content)) {
            childHolder.tvContent.setVisibility(View.GONE);
        } else {
            childHolder.tvContent.setVisibility(View.VISIBLE);
        }
        childHolder.tvTitle.setText(bean.title);
        childHolder.tvContent.setText(bean.content);
        childHolder.tvLeftTop.setText(DateHelper.getInstance().getDateStr(bean.createDate, "dd"));
        childHolder.tvLeftBottom.setText(DateHelper.getInstance().getDateStr(bean.createDate, "MM月"));
        childHolder.tvLeftBottom.append("/");
        childHolder.tvLeftBottom.append(DateHelper.getInstance().getWeekOfDate(bean.createDate));
        childHolder.tvTime.setText(DateHelper.getInstance().getDateStr(bean.createDate, "HH:mm"));
        MainChildAdapter adapter = new MainChildAdapter(context);
        adapter.setStringList(bean.iamgeList);
        childHolder.recycler.setAdapter(adapter);
        childHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener == null) return;
                onItemClickListener.onClick(position);
            }
        });
    }

    @Override
    protected ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_main_adapter;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

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
    }
}

package com.phph.diarydemo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.bean.DiaryBean;
import com.phph.diarydemo.R;
import com.phph.x_support_lib.helper.DateHelper;

import java.util.List;

/**
 * Created by v on 2019/12/10.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private List<DiaryBean> diaryBeans;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void setDiaryBeans(List<DiaryBean> diaryBeans) {
//        Collections.reverse(diaryBeans);
        this.diaryBeans = diaryBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiaryBean diaryBean = diaryBeans.get(position);
        if (TextUtils.isEmpty(diaryBean.title)) {
            holder.tvTitle.setVisibility(View.GONE);
        } else {
            holder.tvTitle.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(diaryBean.content)) {
            holder.tvContent.setVisibility(View.GONE);
        } else {
            holder.tvContent.setVisibility(View.VISIBLE);
        }
        holder.tvTitle.setText(diaryBean.title);
        holder.tvContent.setText(diaryBean.content);
        holder.tvLeftTop.setText(DateHelper.getInstance().getDateStr(diaryBean.createDate, "dd"));
        holder.tvLeftBottom.setText(DateHelper.getInstance().getDateStr(diaryBean.createDate, "MM月"));
        holder.tvLeftBottom.append("/");
        holder.tvLeftBottom.append(DateHelper.getInstance().getWeekOfDate(diaryBean.createDate));
        holder.tvTime.setText(DateHelper.getInstance().getDateStr(diaryBean.createDate, "HH:mm"));
        MainChildAdapter adapter = new MainChildAdapter(context);
        adapter.setStringList(diaryBean.iamgeList);
        holder.recycler.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return diaryBeans != null ? diaryBeans.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvLeftTop;
        public TextView tvLeftBottom;
        public TextView tvTitle;
        public TextView tvContent;
        public RecyclerView recycler;
        public TextView tvTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvLeftTop = (TextView) itemView.findViewById(R.id.tv_left_top);
            this.tvLeftBottom = (TextView) itemView.findViewById(R.id.tv_left_bottom);
            this.tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            this.tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            this.recycler = itemView.findViewById(R.id.recycler);
            this.tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            recycler.setLayoutManager(new GridLayoutManager(context, 3));
        }
    }
}

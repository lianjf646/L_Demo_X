package com.phph.diarydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.bean.DiaryBean;
import com.phph.diarydemo.R;

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
        this.diaryBeans = diaryBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_adapter, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiaryBean diaryBean = diaryBeans.get(position);
        holder.tvTitle.setText(diaryBean.title);

    }

    @Override
    public int getItemCount() {
        return diaryBeans != null ? diaryBeans.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);

        }
    }


}

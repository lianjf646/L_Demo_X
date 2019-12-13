package com.phph.diarydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.phph.diarydemo.R;

import java.util.List;

/**
 * Created by v on 2019/12/12.
 */
public class MainChildAdapter extends RecyclerView.Adapter<MainChildAdapter.ViewHolder> {
    private Context context;
    private List<String> stringList;


    public MainChildAdapter(Context context) {
        this.context = context;

    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_main, parent, false);
        return new MainChildAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainChildAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(stringList.get(position)).into(holder.item_iv);
    }


    @Override
    public int getItemCount() {
        return stringList == null ? 0 : stringList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView item_iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_iv = itemView.findViewById(R.id.item_iv);
        }
    }
}

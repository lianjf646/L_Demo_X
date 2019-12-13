package com.phph.x_support_lib.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by v on 2019/12/12.
 */
public abstract class BaseRecyclerAdapter<T extends Object, RV extends BaseRecyclerViewHolder> extends RecyclerView.Adapter<RV> {

    public Context context;
    public List<T> tList;
    public OnItemClickListener onItemClickListener;

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setTList(List<T> tList) {
        this.tList = tList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return tList != null ? tList.size() : 0;
    }

    @NonNull
    @Override
    public RV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getLayout(), parent, false);
        return getViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RV holder, int position) {
        T t = tList.get(position);
        holder.showView(t, position);
    }


    public T getItemData(int position) {
        return tList.get(position);
    }

    protected abstract int getLayout();

    protected abstract RV getViewHolder(View view);

    public interface OnItemClickListener {
        void onClick(int pos);
    }
}

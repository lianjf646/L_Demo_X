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
 * Created by v on 2019/12/11.
 */
public class WriteDiaryAdapter extends RecyclerView.Adapter<WriteDiaryAdapter.ViewHolder> {

    private Context context;
    private List<String> stringList ;
    private int MAX = 3;
    private GoPriceListener goPriceListener;

    public WriteDiaryAdapter(Context context, GoPriceListener goPriceListener) {
        this.context = context;
        this.goPriceListener = goPriceListener;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (stringList != null && position < getItemCount() && position < stringList.size()) {
            holder.ibtn_xiangce.setVisibility(View.GONE);
            Glide.with(context).load(stringList.get(position)).into(holder.item_iv);
            holder.item_iv.setVisibility(View.VISIBLE);

        } else {
            holder.ibtn_xiangce.setVisibility(View.VISIBLE);
            holder.item_iv.setVisibility(View.GONE);
        }

        holder.ibtn_xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPriceListener.goPrice();
            }
        });
    }


    @Override
    public int getItemCount() {
        return stringList == null ? 1 : stringList.size() < MAX ? stringList.size() + 1 : MAX;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ibtn_xiangce;
        ImageView item_iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ibtn_xiangce = itemView.findViewById(R.id.ibtn_xiangce);
            item_iv = itemView.findViewById(R.id.item_iv);
        }
    }

    public interface GoPriceListener {
        void goPrice();
    }
}

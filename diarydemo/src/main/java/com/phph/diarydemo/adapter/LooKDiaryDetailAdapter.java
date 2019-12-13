package com.phph.diarydemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.phph.diarydemo.R;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;
import com.phph.x_support_lib.base.BaseRecyclerViewHolder;

/**
 * Created by v on 2019/12/13.
 */
public class LooKDiaryDetailAdapter extends BaseRecyclerAdapter<String, LooKDiaryDetailAdapter.ViewHolder> {

    public LooKDiaryDetailAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_iamge_lookdetail;
    }

    @Override
    protected ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseRecyclerViewHolder<String> {
        private ImageView iv_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);

        }

        @Override
        protected void showView(String s, final int position) {
            Glide.with(context).load(s).into(iv_image);
            iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(position);
                }
            });
        }
    }
}

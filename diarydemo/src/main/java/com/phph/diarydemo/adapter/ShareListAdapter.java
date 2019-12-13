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
public class ShareListAdapter extends BaseRecyclerAdapter<String, ShareListAdapter.ViewHolder> {

    public ShareListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_iamge_share_adapter;
    }

    @Override
    protected ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseRecyclerViewHolder<String> {

        private ImageView iv_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_item = itemView.findViewById(R.id.iv_item);
        }

        @Override
        protected void showView(String s, int position) {
            Glide.with(context).load(s).into(iv_item);
        }
    }
}

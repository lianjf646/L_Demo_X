package com.phph.diarydemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.phph.diarydemo.R;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;
import com.phph.x_support_lib.base.BaseRecyclerViewHolder;

/**
 * Created by v on 2019/12/18.
 */
public class BookkeepingAdapter extends BaseRecyclerAdapter<String, BookkeepingAdapter.ViewHolder> {


    public BookkeepingAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_booking;
    }


    @Override
    protected ViewHolder getViewHolder(View view) {
        view.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        return new ViewHolder(view);
    }


    class ViewHolder extends BaseRecyclerViewHolder<String> {

        private ListView lv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lv = itemView.findViewById(R.id.lv);
        }

        @Override
        protected void showView(String s, int position) {

        }
    }

}

package com.phph.diarydemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.phph.db_lib.bookkeeping.type.BookkeepingTypeBean;
import com.phph.diarydemo.R;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;
import com.phph.x_support_lib.base.BaseRecyclerViewHolder;

/**
 * Created by v on 2019/12/19.
 */
public class BookkeepingTypeListAdapter extends BaseRecyclerAdapter<BookkeepingTypeBean, BookkeepingTypeListAdapter.ViewHolder> {


    public BookkeepingTypeListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_bookkeepingtype_adapter;
    }

    @Override
    protected ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseRecyclerViewHolder<BookkeepingTypeBean> {
        private TextView tv_type_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_type_name = itemView.findViewById(R.id.tv_type_name);
        }

        @Override
        protected void showView(BookkeepingTypeBean bookkeepingBean, int position) {
            tv_type_name.setText(bookkeepingBean.getTypeName());
        }
    }
}

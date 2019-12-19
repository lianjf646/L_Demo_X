package com.phph.diarydemo.activity;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.DBHelper;
import com.phph.db_lib.bookkeeping.type.BookkeepingTypeBean;
import com.phph.diarydemo.R;
import com.phph.diarydemo.adapter.BookkeepingTypeListAdapter;
import com.phph.x_support_lib.base.BaseActivity;

import java.util.List;

/**
 * Created by v on 2019/12/19.
 */
public class BookkeepingTypeListActivity extends BaseActivity {

    private RecyclerView recycler;
    private BookkeepingTypeListAdapter adapter;
    private List<BookkeepingTypeBean> typeBeanList;

    private Button btn_add_type;

    private String typeName = "typeName";
    private int i = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bookkeepingtypelist;
    }

    @Override
    protected void initView() {
        btn_add_type = findViewById(R.id.btn_add_type);
        btn_add_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookkeepingTypeBean bean = new BookkeepingTypeBean();
                bean.setTypeName(typeName + i++);
                DBHelper.getInstance().bookkeepingTypeDao().insertReplaceItems(bean);
                typeBeanList = DBHelper.getInstance().bookkeepingTypeDao().getAll();
                adapter.setTList(typeBeanList);
            }
        });

        recycler = findViewById(R.id.recycler_type_list);
        adapter = new BookkeepingTypeListAdapter(context);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        typeBeanList = DBHelper.getInstance().bookkeepingTypeDao().getAll();
        adapter.setTList(typeBeanList);

    }
}

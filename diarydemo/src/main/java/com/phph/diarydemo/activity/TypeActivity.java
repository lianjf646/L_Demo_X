package com.phph.diarydemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.DBHelper;
import com.phph.db_lib.type.TypeBean;
import com.phph.diarydemo.R;
import com.phph.diarydemo.adapter.TypeAdapter;
import com.phph.diarydemo.bean.TypeListBean;
import com.phph.x_support_lib.base.BaseActivity;
import com.phph.x_support_lib.base.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v on 2019/12/12.
 */
public class TypeActivity extends BaseActivity {
    private ImageView iv_back;
    private ImageView ibtn_add_type;
    private TypeAdapter typeAdapter;
    private RecyclerView recycler;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_type;
    }

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ibtn_add_type = findViewById(R.id.ibtn_add_type);
        ibtn_add_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildEditDialog();
            }
        });
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(context));
    }

    List<TypeListBean> typeListBeanList;

    @Override
    protected void initData() {
        typeAdapter = new TypeAdapter(context);
        List<TypeBean> typeBeanList = DBHelper.getInstance().typeDao().getAll();
        typeListBeanList = new ArrayList<>();
        for (TypeBean typeBean : typeBeanList) {
            TypeListBean typeListBean = new TypeListBean();
            typeListBean.typeName = typeBean.typeName;
            int cont = DBHelper.getInstance().diaryDao().getTypeNumber(typeBean.typeName);
            typeListBean.total = cont;
            typeListBeanList.add(typeListBean);
        }
        typeAdapter.setTList(typeListBeanList);
        typeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {

                String typeName = typeAdapter.getItemData(pos).typeName;
                startActivity(new Intent(context, TypeListActivity.class).putExtra("typeName", typeName));
            }
        });
        recycler.setAdapter(typeAdapter);
    }

    private void buildEditDialog() {
        final EditText text = new EditText(context);
        text.setHint("添加分类");
        text.setBackground(null);
        new AlertDialog.Builder(context).setTitle("是否添加分类?").setView(text).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String string = text.getText().toString().trim();
                TypeBean typeBean = new TypeBean();
                typeBean.typeName = string;
                DBHelper.getInstance().typeDao().insertItems(typeBean);
                typeListBeanList.add(new TypeListBean(string, 0));

            }
        }).setNegativeButton("取消", null).show();
    }
}

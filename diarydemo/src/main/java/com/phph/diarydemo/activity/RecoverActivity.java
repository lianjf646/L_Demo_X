package com.phph.diarydemo.activity;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

import com.phph.db_lib.DBHelper;
import com.phph.db_lib.recovery.RecoverBean;
import com.phph.diarydemo.R;
import com.phph.x_support_lib.base.BaseActivity;
import com.phph.x_support_lib.util.ListUtils;

import java.util.List;

/**
 * Created by v on 2019/12/17.
 */
public class RecoverActivity extends BaseActivity {

    private ImageView iv_back;
    private ImageView iv_del_all;
    private ImageView iv_not_data;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_recover;
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

        iv_del_all = findViewById(R.id.iv_del_all);
        iv_del_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示:");
                builder.setMessage("是否清空回收站?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper.getInstance().recoverDao().delAll();
                        iv_not_data.setVisibility(View.VISIBLE);
                    }
                });
                builder.setNeutralButton("取消", null);
                builder.show();

            }
        });
        iv_not_data = findViewById(R.id.iv_not_data);
    }

    @Override
    protected void initData() {


        List<RecoverBean> recoverBeanList = DBHelper.getInstance().recoverDao().getAll();
        if (ListUtils.isEmpty(recoverBeanList)) {
            iv_not_data.setVisibility(View.VISIBLE);
        } else {
            iv_not_data.setVisibility(View.GONE);
        }
    }
}

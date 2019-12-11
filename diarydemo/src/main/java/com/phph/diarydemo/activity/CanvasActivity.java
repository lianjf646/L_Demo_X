package com.phph.diarydemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

import com.phph.diarydemo.R;
import com.phph.x_support_lib.base.BaseActivity;
import com.yinghe.whiteboardlib.fragment.WhiteBoardFragment;

import java.io.File;

/**
 * Created by v on 2019/12/11.
 */
public class CanvasActivity extends BaseActivity {
    private ImageView ivBack;

    AlertDialog.Builder alertDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_canvas;
    }

    @Override
    protected void initView() {


        //添加到界面中
        getSupportFragmentManager().beginTransaction().add(R.id.frame, WhiteBoardFragment.newInstance(new WhiteBoardFragment.SendBtnCallback() {
            @Override
            public void onSendBtnClick(File filePath) {
                Log.e(">>>>", "onSendBtnClick: " + filePath.getAbsolutePath());
                Intent intent = new Intent();
                intent.putExtra("fileLocPath",filePath.getAbsolutePath());
                setResult(300,intent);
                finish();
            }
        })).commit();
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });


    }

    @Override
    protected void initData() {
        alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle("提示");
        alertDialog.setMessage("退出将清除画板");
        alertDialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        alertDialog.show();
    }
}

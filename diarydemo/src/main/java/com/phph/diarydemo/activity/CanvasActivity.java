package com.phph.diarydemo.activity;

import androidx.fragment.app.FragmentTransaction;

import com.phph.diarydemo.R;
import com.phph.diarydemo.fragment.CanvasFragment;
import com.phph.x_support_lib.base.BaseActivity;
import com.yinghe.whiteboardlib.fragment.WhiteBoardFragment;

/**
 * Created by v on 2019/12/11.
 */
public class CanvasActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_canvas;
    }

    @Override
    protected void initView() {
        //获取Fragment管理器
        FragmentTransaction ts = getSupportFragmentManager().beginTransaction();
        //获取WhiteBoardFragment实例
        WhiteBoardFragment whiteBoardFragment = WhiteBoardFragment.newInstance();
        //添加到界面中
        ts.add(R.id.frame, new CanvasFragment(), "wb").commit();

    }

    @Override
    protected void initData() {

    }
}

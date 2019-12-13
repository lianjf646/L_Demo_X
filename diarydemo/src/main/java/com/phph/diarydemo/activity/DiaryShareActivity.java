package com.phph.diarydemo.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phph.db_lib.diary.DiaryBean;
import com.phph.diarydemo.R;
import com.phph.diarydemo.adapter.ShareListAdapter;
import com.phph.x_support_lib.base.BaseActivity;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by v on 2019/12/13.
 */
public class DiaryShareActivity extends BaseActivity {

    private DiaryBean bean;
    private TextView tv_title;
    private TextView tv_content;
    private RecyclerView recycler;

    private ShareListAdapter adapter;
    private ScrollView NestedScrollView;
    private TextView tv_share_go;


    private String path = Environment.getExternalStorageDirectory() + "/FFFDDDDFFD.png";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_diaryshare;
    }

    @Override
    protected void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        recycler = findViewById(R.id.recycler);
        adapter = new ShareListAdapter(context);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        NestedScrollView = findViewById(R.id.NestedScrollView);
        tv_share_go = findViewById(R.id.tv_share_go);
        tv_share_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(path, getBitmapByView(NestedScrollView));
                Toast.makeText(activity, "?????" + path, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {
        bean = (DiaryBean) getIntent().getSerializableExtra("DiaryBean");
        tv_title.setText(bean.title);
        tv_content.setText(bean.content);
        adapter.setTList(bean.iamgeList);
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);

    }


    public static void saveImage(String path, Bitmap bitmap) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);
        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }

    public static Bitmap getBitmapByView(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }
}

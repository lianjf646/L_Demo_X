package com.phph.diarydemo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.phph.db_lib.DBHelper;
import com.phph.db_lib.diary.DiaryBean;
import com.phph.diarydemo.BuildConfig;
import com.phph.diarydemo.R;
import com.phph.diarydemo.adapter.ShareListAdapter;
import com.phph.x_support_lib.base.BaseActivity;
import com.phph.x_support_lib.helper.DateHelper;
import com.phph.x_support_lib.view.SSRecyclerView;

import java.io.BufferedOutputStream;
import java.io.File;
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
    private SSRecyclerView recycler;

    private ShareListAdapter adapter;
    private ScrollView NestedScrollView;
    private TextView tv_share_go;
    private TextView tv_year_mooth_day;
    private TextView tv_week;
    private ImageView iv_save;


    private String path = Environment.getExternalStorageDirectory() + "/xuan_wo" + System.currentTimeMillis() + ".png";

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
                shareMsg("DiaryShareActivity", "", "", path);
            }
        });
        tv_year_mooth_day = findViewById(R.id.tv_year_mooth_day);
        tv_week = findViewById(R.id.tv_week);
        iv_save = findViewById(R.id.iv_save);
        iv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(path, getBitmapByView(NestedScrollView));
                Toast.makeText(activity, "以保存到SD卡", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 分享功能
     * <p>
     * <p>
     * 上下文
     *
     * @param activityTitle Activity的名字
     * @param msgTitle      消息标题
     * @param msgText       消息内容
     * @param imgPath       图片路径，不分享图片则传null
     */
    public void shareMsg(String activityTitle, String msgTitle, String msgText, String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/png");
                Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", f);
                intent.putExtra(Intent.EXTRA_STREAM, contentUri);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, activityTitle));
    }


    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id",-1);
        bean = DBHelper.getInstance().diaryDao().findId(id);
        tv_title.setText(bean.title);
        tv_content.setText(bean.content);
        if (TextUtils.isEmpty(bean.huabanPathLoc)){
            bean.iamgeList.add(bean.huabanPathLoc);
        }
        adapter.setTList(bean.iamgeList);
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);
        tv_year_mooth_day.setText(DateHelper.getInstance().getDateStr(bean.createDate, "yyyy/MM/dd"));
        tv_week.setText(DateHelper.getInstance().getWeekOfDate(bean.createDate));
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

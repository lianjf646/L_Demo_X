package com.phph.diarydemo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.phph.diarydemo.R;
import com.phph.diarydemo.adapter.WriteDiaryAdapter;
import com.phph.x_support_lib.base.BaseActivity;
import com.phph.x_support_lib.util.GlideLoader4ImagePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ren.qinc.edit.PerformEdit;

/**
 * Created by v on 2019/12/11.
 */
public class WriteDiaryActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivBack;
    private TextView tvDate;
    private TextView tvTime;
    private EditText etContent;

    private TextView tvWeather;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ImageView iv5;
    private ImageView iv6;

    private RecyclerView recycler;
    private WriteDiaryAdapter adapter;
    private ImagePicker imagePicker;
    private ArrayList<ImageItem> imageItemArrayList;
    private PerformEdit performEdit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wtitediary;
    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvTime = (TextView) findViewById(R.id.tv_time);
        etContent = (EditText) findViewById(R.id.et_content);
        recycler = (RecyclerView) findViewById(R.id.recycler);

        tvWeather = (TextView) findViewById(R.id.tv_weather);
        iv1 = (ImageView) findViewById(R.id.iv_1);
        iv2 = (ImageView) findViewById(R.id.iv_2);
        iv3 = (ImageView) findViewById(R.id.iv_3);
        iv4 = (ImageView) findViewById(R.id.iv_4);
        iv5 = (ImageView) findViewById(R.id.iv_5);
        iv6 = (ImageView) findViewById(R.id.iv_6);

        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        iv6.setOnClickListener(this);
        tvWeather.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        performEdit = new PerformEdit(etContent);
        adapter = new WriteDiaryAdapter(context, new WriteDiaryAdapter.GoPriceListener() {
            @Override
            public void goPrice() {
                Intent intent1 = new Intent(context, ImageGridActivity.class);
                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, imageItemArrayList);
                activity.startActivityForResult(intent1, 300);
            }
        });
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(context, 3);

        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(adapter);

        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideLoader4ImagePicker());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(false);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setMultiMode(true);
        imagePicker.setSelectLimit(3);    //选中数量限制
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (300 == requestCode && resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            imageItemArrayList = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (imageItemArrayList == null) return;
            if (imageItemArrayList.isEmpty()) return;

            List<String> strings = new ArrayList<>();
            for (int i = 0; i < imageItemArrayList.size(); i++) {
                strings.add(imageItemArrayList.get(i).path);
            }
            adapter.setStringList(strings);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_1:
                startActivity(new Intent(context, CanvasActivity.class));
                break;
            case R.id.iv_2:
                performEdit.undo();
                break;
            case R.id.iv_3:
                etContent.append("\n");
                etContent.append("•");
                break;
            case R.id.iv_4:
                etContent.append("\n");
                break;
            case R.id.iv_5: {
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                Date date = new Date(System.currentTimeMillis());
                String str = formatter.format(date);
                etContent.append(str);
            }

            break;
            case R.id.iv_6: {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
                Date date = new Date(System.currentTimeMillis());
                String str = formatter.format(date);
                etContent.append(str);
            }

            break;
            case R.id.tv_weather:
                break;

        }
    }
}

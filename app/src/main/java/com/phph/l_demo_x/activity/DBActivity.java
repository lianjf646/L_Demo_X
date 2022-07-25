package com.phph.l_demo_x.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.phph.db_lib.DBHelper;
import com.phph.db_lib.user.UserBean;
import com.phph.db_lib.user.UserDao;
import com.phph.l_demo_x.R;

import java.util.List;


/**
 * Created by v on 2019/12/9.
 */
public class DBActivity extends AppCompatActivity {
    private TextView tv;
    //进行测试 数据
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        tv = findViewById(R.id.tv);
        final UserDao userDao = DBHelper.getInstance().userDao();
        UserBean userBean = new UserBean();
        userBean.name = "mm123";
        userBean.phone = "4567897891111111";
        userDao.insertItems(userBean, userBean, userBean);

        UserBean userBean1 = userDao.selectVo("mm123");
        tv.setText(userBean1.phone);

        List<UserBean> userBeanList = userDao.getAll();
        Toast.makeText(this, "" + userBeanList.size(), Toast.LENGTH_SHORT).show();
    }
}

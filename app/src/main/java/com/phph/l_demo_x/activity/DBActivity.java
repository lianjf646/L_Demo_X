package com.phph.l_demo_x.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.phph.db_lib.DBHelper;
import com.phph.db_lib.bean.UserBean;
import com.phph.db_lib.dao.UserDao;
import com.phph.l_demo_x.R;


/**
 * Created by v on 2019/12/9.
 */
public class DBActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        UserDao userDao = DBHelper.getInstance().userDao();
        UserBean userBean = new UserBean();
        userBean.name = "mm123";
        userBean.phone = "456789789";
        userDao.insertItem(userBean);

        UserBean userBean1 = userDao.selectVo("mm123");
        Toast.makeText(this, "" + userBean1.phone, Toast.LENGTH_SHORT).show();
    }
}

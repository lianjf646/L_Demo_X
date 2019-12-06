package com.phph.l_demo_x.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.phph.l_demo_x.R;
import com.phph.l_demo_x.httpservice.TCSNetWorkApi;
import com.phph.l_demo_x.request.DDDRequest;
import com.phph.l_demo_x.response.FFFResponse;
import com.phph.network.errorhander.ExceptionHandle;
import com.phph.network.observer.BaseObserver;
import com.phph.network.rxjavaschedulers.ComposeObservableHelper;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);

        TCSNetWorkApi.getInstance().getService().getImageCode("15636899875").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                byte[] dd = new byte[0];
                try {
                    dd = response.body().bytes();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(dd, 0, dd.length);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


//        TCSNetWorkApi.getService(TCSService.class)//
//                .registerUserExistsO("15636899875/1234")//
//                .compose(ComposeObservableHelper.getInstance().applySchedulers())//
//                .subscribe(new BaseObserver<FFFResponse>() {
//                    @Override
//                    public void success(FFFResponse fffResponse) {
//                        Toast.makeText(MainActivity.this, "" + fffResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void fail(Throwable e) {
//                        Toast.makeText(MainActivity.this, "??????", Toast.LENGTH_SHORT).show();
//                        if (e instanceof ExceptionHandle.ServerException) {
//                            ExceptionHandle.ServerException exception = (ExceptionHandle.ServerException) e;
//                            Toast.makeText(MainActivity.this, "" + ((ExceptionHandle.ServerException) e).code, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });



        TCSNetWorkApi.getInstance()
                .getService()//
                .registerUserExistsO("15636899875/1234")//
                .compose(ComposeObservableHelper.getInstance().applySchedulers())//
                .subscribe(new BaseObserver<FFFResponse>() {
                    @Override
                    public void success(FFFResponse fffResponse) {
                        Toast.makeText(MainActivity.this, "" + fffResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void fail(Throwable e) {
                        Toast.makeText(MainActivity.this, "??????", Toast.LENGTH_SHORT).show();
                        if (e instanceof ExceptionHandle.ServerException) {
                            ExceptionHandle.ServerException exception = (ExceptionHandle.ServerException) e;
                            Toast.makeText(MainActivity.this, "" + ((ExceptionHandle.ServerException) e).message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });



        TCSNetWorkApi.getInstance()
                .getService()//
                .registerUserExistsO1(TCSNetWorkApi.createRequestBody(new DDDRequest()))//
                .compose(ComposeObservableHelper.getInstance().applySchedulers())//
                .subscribe(new BaseObserver<FFFResponse>() {
                    @Override
                    public void success(FFFResponse fffResponse) {
                        Toast.makeText(MainActivity.this, "" + fffResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void fail(Throwable e) {
                        Toast.makeText(MainActivity.this, "??????", Toast.LENGTH_SHORT).show();
                        if (e instanceof ExceptionHandle.ServerException) {
                            ExceptionHandle.ServerException exception = (ExceptionHandle.ServerException) e;
                            Toast.makeText(MainActivity.this, "" + ((ExceptionHandle.ServerException) e).message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}

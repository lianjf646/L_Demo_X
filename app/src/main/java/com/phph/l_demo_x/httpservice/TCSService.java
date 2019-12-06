package com.phph.l_demo_x.httpservice;

import com.phph.l_demo_x.response.FFFResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by v on 2019/10/30.
 */
public interface TCSService {

    @GET("tlcuser/login/getImageCode/{path}")
    Call<ResponseBody> getImageCode(@Path(value = "path", encoded = true) String path);

    @GET("tlcuser/login/registerUserExists/{path}")
    Call<FFFResponse> registerUserExists(@Path(value = "path", encoded = true) String path);

    @GET("tlcuser/login/registerUserExists/{path}")
    Observable<FFFResponse> registerUserExistsO(@Path(value = "path", encoded = true) String path);


    @POST("tlcuser/login/registerUserExists/{path}")
    Observable<FFFResponse> registerUserExistsO1(@Body RequestBody body);


    // TODO 更改成统一模式
    @POST()
    Observable<ResponseBody> registerUserExistsO2(@Url String url, @Body RequestBody body);
}

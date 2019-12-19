package com.phph.l_demo_x.httpservice;

import com.google.gson.Gson;
import com.phph.l_demo_x.request.BaseRequest;
import com.phph.l_demo_x.response.FFFResponse;
import com.phph.network.base.NetWorkApi;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by v on 2019/10/30.
 */
public class TCSNetWorkApi extends NetWorkApi {
    public static TCSNetWorkApi getInstance() {

        return TCSNetWorkInner.tcsNetWorkApi;
    }

    static class TCSNetWorkInner {

        static TCSNetWorkApi tcsNetWorkApi = new TCSNetWorkApi();
    }

    private TCSNetWorkApi() {

    }

    @Override
    protected <T> Function<T, T> getAppErrorHandler() {
        return new Function<T, T>() {
            @Override
            public T apply(T response) throws Exception {
                //response中code码不会0 出现错误
//                if (response instanceof FFFResponse) {
//                    if (((FFFResponse) response).getCode() == 200) {
//                        return response;
//                    }
//                    ExceptionHandle.ServerException exception = new ExceptionHandle.ServerException();
//                    exception.code = ((FFFResponse) response).getCode();
//                    exception.message = ((FFFResponse) response).getMessage() != null ? ((FFFResponse) response).getMessage() : "";
//                    throw exception;
//                }
                return response;
            }
        };
    }

    @Override
    protected Interceptor getInterceptor() {
        return null;
    }

    @Override
    public String getFormal() {
        return "https://39.96.132.92:32443/";
    }

    @Override
    public String getTest() {
        return "https://39.96.132.92:32443/";
    }

    public TCSService getService() {
        return getRetrofit().create(TCSService.class);

    }
    public Observable<FFFResponse> getService(BaseRequest request) {

        return getRetrofit().create(TCSService.class).registerUserExistsO1(createRequestBody(request));
    }


    public static RequestBody createRequestBody(BaseRequest request) {

        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(request));
    }
}

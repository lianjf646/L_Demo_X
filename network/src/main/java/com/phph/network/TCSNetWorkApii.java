//package com.phph.network;
//
//import com.phph.l_demo_x.response.FFFResponse;
//import com.phph.network.base.NetWorkApi;
//import com.phph.network.errorhander.ExceptionHandle;
//
//import io.reactivex.functions.Function;
//
///**
// * Created by v on 2019/10/30.
// */
//public class TCSNetWorkApii extends NetWorkApi {
//    public static TCSNetWorkApi getInstance() {
//        return TCSNetWorkInner.tcsNetWorkApi;
//    }
//
//    static class TCSNetWorkInner {
//        static TCSNetWorkApi tcsNetWorkApi = new TCSNetWorkApi();
//    }
//
//    private TCSNetWorkApii() {
//
//    }
//
//    @Override
//    protected <T> Function<T, T> getAppErrorHandler() {
//        return new Function<T, T>() {
//            @Override
//            public T apply(T response) throws Exception {
//                //response中code码不会0 出现错误
//                if (response instanceof FFFResponse  ) {
//                    if (((FFFResponse) response).getCode() == 200){
//                        return response;
//                    }
//
//                    ExceptionHandle.ServerException exception = new ExceptionHandle.ServerException();
//                    exception.code = ((FFFResponse) response).getCode();
//                    exception.message = ((FFFResponse) response).getMessage() != null ? ((FFFResponse) response).getMessage() : "";
//                    throw exception;
//
//                }
//                return response;
//            }
//        };
//    }
//
//    /**
//     * @param t
//     * @param <T>
//     * @return
//     */
//    public <T> T getService(Class<T> t) {
//        return getInstance().getRetrofit().create(t);
//    }
//
//    @Override
//    public String getFormal() {
//        return "https://39.96.132.92:32443/";
//    }
//
//    @Override
//    public String getTest() {
//        return "https://39.96.132.92:32443/";
//    }
//}

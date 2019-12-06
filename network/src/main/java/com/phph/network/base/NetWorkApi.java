package com.phph.network.base;

import com.google.gson.Gson;
import com.phph.network.environment.IEnvironment;
import com.phph.network.https.HttpsUtils;
import com.phph.network.https.SafeHostnameVerifier;

import java.io.IOException;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by v on 2019/10/30.
 */
// TODO  想办法配置信息通过外部进行传递进来 实现与okGo相似的网络请求框架
public abstract class NetWorkApi implements IEnvironment {

    private static INetworkRequiredInfo iNetworkRequiredInfo;
    //  实现多域名的方式  应为子类是属于单例模式 多个子类创建 父类内部的Retrofit创建也是不同的
//    private static HashMap<String, Retrofit> retrofitHashMap = new HashMap<>();

    private static boolean mIsFormal = true;
    public static Function function;

    private String mBaseUrl;
    private OkHttpClient mOkHttpClient;


    public NetWorkApi() {
        // TODO  2个不同传入
        function = getAppErrorHandler();
        if (mIsFormal) {
            mBaseUrl = getFormal();
        } else {
            mBaseUrl = getTest();
        }
        initOkClient();
    }

    public static void init(INetworkRequiredInfo iNetworkRequired) {
        iNetworkRequiredInfo = iNetworkRequired;
        mIsFormal = iNetworkRequired.isDebug();


    }

    public Retrofit getRetrofit() {
//        if (retrofitHashMap.get(mBaseUrl + service.getName()) != null) {
//            return retrofitHashMap.get(mBaseUrl + service.getName());
//        }
        Retrofit retrofit = null;
        if (retrofit != null) {
            return retrofit;
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        // 添加解析
        builder.addConverterFactory(GsonConverterFactory.create(new Gson()));
        //为了支持rxjava,需要添加下面这个把 Retrofit 转成RxJava可用的适配类
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        // 添加okhttpClient配置
//        builder.client(initOkClient());
        builder.client(mOkHttpClient);
        builder.baseUrl(mBaseUrl);
        retrofit = builder.build();
//        retrofitHashMap.put(mBaseUrl + service.getName(), builder.build());
        return retrofit;
    }

    //TODO  想办法证书验证方式通过外部进行传递进来
    private OkHttpClient initOkClient() {

        // log添加日志打印
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);

        //https相关设置，以下几种方案根据需要自己设置
        //方法一：信任所有证书,不安全有风险
        //HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        //方法二：自定义信任规则，校验服务端证书
        //HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
        //方法三：使用预埋证书，校验服务端证书（自签名证书）
        HttpsUtils.SSLParams sslParams1 = null;
        try {
            sslParams1 = HttpsUtils.getSslSocketFactory(iNetworkRequiredInfo.getApplicationContext().getAssets().open("server.cer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
        builder.hostnameVerifier(new SafeHostnameVerifier());
        mOkHttpClient = builder.build();
        return mOkHttpClient;
    }

    /**
     * 获取网络服务端自定义code 码状态
     *
     * @param <T>
     * @return
     */
    protected abstract <T> Function<T, T> getAppErrorHandler();

    /**
     * 获取自定义拦截器
     *
     * @return
     */
    protected abstract Interceptor getInterceptor();

}

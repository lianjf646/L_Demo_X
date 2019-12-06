package com.phph.network.rxjavaschedulers;

import com.phph.network.base.NetWorkApi;
import com.phph.network.errorhander.HttpErrorHandler;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava操作符compose对Observable进行二次操作
 * <p>
 * Created by v on 2019/10/31.
 */
public final class ComposeObservableHelper {

    public static ComposeObservableHelper getInstance() {
        return ComposeObservableHelperInner.helper;
    }

    private static class ComposeObservableHelperInner {
        static ComposeObservableHelper helper = new ComposeObservableHelper();

    }

    private ComposeObservableHelper() {

    }

    /**
     * //  TODO onErrorResumeNext 了解这个操作符的意义与作用
     *
     * @return 返回被处理过后的observable
     */
    public <T> ObservableTransformer applySchedulers() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                Observable observable = upstream//
                        .subscribeOn(Schedulers.io())//
                        .unsubscribeOn(Schedulers.io())//
                        .observeOn(AndroidSchedulers.mainThread())//
                        .map(NetWorkApi.function)//对服务器返回异常进行处理
                        // TODO 异常处理需要弄明白 逻辑顺序
                        .onErrorResumeNext(new HttpErrorHandler<T>());
                return observable;
            }
        };
    }
}

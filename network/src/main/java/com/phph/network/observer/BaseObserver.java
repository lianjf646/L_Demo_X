package com.phph.network.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by v on 2019/10/31.
 */
public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        success(t);
    }

    @Override
    public void onError(Throwable e) {

        fail(e);
    }

    @Override
    public void onComplete() {

    }

    public abstract void success(T t);

    public abstract void fail(Throwable e);
}

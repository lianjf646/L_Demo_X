package com.phph.l_demo_x;

import com.phph.l_demo_x.response.FFFResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *TODO 回调处理
 *
 * Created by v on 2019/10/31.
 */
public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        FFFResponse f = (FFFResponse) t;
        if (f.getCode()==200){
            success(t);
        }
        if (f.getCode()==300){

        }

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

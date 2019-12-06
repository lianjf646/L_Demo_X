package com.phph.network.environment;

import io.reactivex.functions.Function;

/**
 * 获取子类自定义异常实例
 * Created by v on 2019/10/31.
 */
public interface GetCustomError {
    <T> Function<T, T> getAppErrorHandlers();
}

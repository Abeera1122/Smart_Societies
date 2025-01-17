package com.abidingtech.base.callback;

public interface ResultCallback<T> extends BaseCallback<String> {
    void onResult(T result);
}

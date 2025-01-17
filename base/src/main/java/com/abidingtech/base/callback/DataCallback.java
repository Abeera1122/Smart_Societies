package com.abidingtech.base.callback;

public interface DataCallback<T> extends BaseCallback<String> {
    void onData(T data);
}

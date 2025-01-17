package com.abidingtech.base.callback;

public interface DataChangeCallback<T> extends BaseCallback<String>{
    void onChange(T data);
}

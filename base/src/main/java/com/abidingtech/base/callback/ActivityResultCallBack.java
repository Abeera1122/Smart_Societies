package com.abidingtech.base.callback;

public interface ActivityResultCallBack<T> {
    public void onResultOk(T data);

    public void onResultFailure();
}

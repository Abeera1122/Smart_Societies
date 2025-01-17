package com.abidingtech.base.callback;

public interface ImageUploadCallback extends BaseCallback<String> {
    void onComplete(String imagePath);

    void onProgress(int percentage);
}

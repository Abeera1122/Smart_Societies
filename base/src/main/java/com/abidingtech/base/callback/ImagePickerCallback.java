package com.abidingtech.base.callback;

import android.net.Uri;

import java.io.File;

public interface ImagePickerCallback extends BaseCallback<String> {
    void onImage(Uri uri);
}

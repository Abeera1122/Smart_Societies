package com.abidingtech.base.callback;

import java.io.File;

public interface ImagePickerCallbackV2 extends BaseCallback<String> {
    void onImage(File file);

}

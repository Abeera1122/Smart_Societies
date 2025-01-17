package com.abidingtech.base.dao;

import android.net.Uri;
import android.util.Log;

import com.abidingtech.base.callback.ImageUploadCallback;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FirebaseImageDao {
    private static FirebaseImageDao _this;

    private FirebaseImageDao() {
    }


    public static FirebaseImageDao getInstance() {
        if (_this == null)
            _this = new FirebaseImageDao();
        return _this;
    }

    public void uploadImage(String folder, Uri uri, ImageUploadCallback imageUploadCallback) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(folder).child(System.currentTimeMillis() + ".jpg");
        UploadTask task = storageReference.putFile(uri);
        task.continueWithTask(task12 -> {
            if (!task12.isSuccessful()) {
                throw task12.getException();
            }
            double uploadedBytes = task12.getResult().getBytesTransferred();
            double totalBytes = task12.getResult().getTotalByteCount();
            int percentage = (int) ((uploadedBytes / totalBytes) * 100);
            Log.e("Upload Image:", percentage + "%");

            if (imageUploadCallback != null)
                imageUploadCallback.onProgress(percentage);
            return storageReference.getDownloadUrl();
        }).addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {
                if (imageUploadCallback != null) {
                    imageUploadCallback.onComplete(task1.getResult().toString());
                    Log.e("Upload Image: ", "Uploaded");

                }
            } else {
                if (imageUploadCallback != null) {
                    imageUploadCallback.onError(task1.getException().getLocalizedMessage() + "");
                    Log.e("Upload Image: ", task1.getException().getLocalizedMessage());

                }
            }
        });
    }
}

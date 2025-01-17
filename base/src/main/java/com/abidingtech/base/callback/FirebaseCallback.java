package com.abidingtech.base.callback;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

 public abstract class FirebaseCallback<T> implements OnCompleteListener, DataCallback<T> {
    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful()) {
            onData((T) task.getResult());
        } else onError(task.getException().getLocalizedMessage());
    }

}

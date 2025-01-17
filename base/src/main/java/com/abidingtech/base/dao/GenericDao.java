package com.abidingtech.base.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.abidingtech.base.callback.DataCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericDao {
    private CollectionReference collectionReference;

    protected <T> void getList(String ref, Class clazz, DataCallback<List<T>> dataCallback) {
        collectionReference = FirebaseFirestore.getInstance().collection(ref);
        if (dataCallback == null)
            return;
        collectionReference.get().addOnCompleteListener(task -> {
//            Log.e(TAG, "getList: ", );
            if (task.isSuccessful()) {
                List<Object> list = new ArrayList<>();
                List<DocumentSnapshot> documentSnapshotList = task.getResult().getDocuments();
                for (DocumentSnapshot snapshots : documentSnapshotList) {
                    Log.e("getList: ", snapshots.toString()+"");
                    list.add(snapshots.toObject(clazz));
                }
                dataCallback.onData((List<T>) list);
            } else {
                dataCallback.onError(task.getException().getLocalizedMessage() + "");
            }
        });
    }

    protected <T> void getList(String param, String value, String ref, Class clazz, DataCallback<List<T>> dataCallback) {
        collectionReference = FirebaseFirestore.getInstance().collection(ref);
        if (dataCallback == null)
            return;
        collectionReference.whereEqualTo(param, value).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Object> list = new ArrayList<>();
                List<DocumentSnapshot> documentSnapshotList = task.getResult().getDocuments();
                for (DocumentSnapshot snapshots : documentSnapshotList) {
                    list.add(snapshots.toObject(clazz));
                }
                dataCallback.onData((List<T>) list);
            } else {
                dataCallback.onError(task.getException().getLocalizedMessage() + "");
            }
        });
    }
}

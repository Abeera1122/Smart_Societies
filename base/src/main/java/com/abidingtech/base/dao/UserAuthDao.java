package com.abidingtech.base.dao;

import androidx.annotation.NonNull;

import com.abidingtech.base.callback.DataCallback;
import com.abidingtech.base.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class UserAuthDao extends GenericDao {
    private static UserAuthDao userAuthDao;

    private UserAuthDao() {
    }

    public static UserAuthDao getInstance() {
        if (userAuthDao == null)
            userAuthDao = new UserAuthDao();
        return userAuthDao;
    }

    public void login(String email, String password, DataCallback<String> callback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (callback == null)
                    return;
                if (task.isSuccessful()) {
                    callback.onData("Login Successfully");
                } else callback.onError(task.getException().getLocalizedMessage() + "");
            }
        });

    }

    public void getCurrentUser(DataCallback<User> dataCallback) {
        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Users");
        collectionReference.document(UserDao.getInstance().getUserId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    User user = task.getResult().toObject(User.class);
                    if (user == null) {
                        user = new User();
                        user.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        user.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());

                    }
                    UserDao.getInstance().setUser(user);
                    dataCallback.onData(user);
                } else {
                    dataCallback.onError(task.getException().getLocalizedMessage() + "");
                }
            }
        });
    }

    public void getUser(String id, DataCallback<User> dataCallback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    dataCallback.onData(snapshot.getValue(User.class));
                } else {
                    dataCallback.onError("User does not ecist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dataCallback.onError(error.getMessage() + "");
            }
        });
//        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Users");
//        collectionReference.document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    User user = task.getResult().toObject(User.class);
//                    if (user == null) {
//                        user = new User();
//                        user.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                        user.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
//
//                    }
//                    dataCallback.onData(user);
//                } else {
//                    dataCallback.onError(task.getException().getLocalizedMessage() + "");
//                }
//            }
//        });
    }


    public void createUser(User user, DataCallback<String> dataCallback) {
        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Users");
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        user.setId(id);
        collectionReference.document(id).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (dataCallback == null)
                    return;
                if (task.isSuccessful()) {
                    UserDao.getInstance().setUser(user);

                    dataCallback.onData("User Created");
                } else dataCallback.onError(task.getException().getLocalizedMessage() + "");
            }
        });
    }

    public void createUserV2(User user, DataCallback<String> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        user.setId(id);
        ref.child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    callback.onData("User Registered");
                } else {
                    callback.onError(task.getException().getLocalizedMessage() + "");
                }
            }
        });
    }

    public void register(User user, DataCallback<String> callback) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    createUserV2(user, callback);

//                    createUser(user, callback);
                } else callback.onError(task.getException().getLocalizedMessage() + "");
//                if (callback == null)
//                    return;
//                if (task.isSuccessful()) {
//                    callback.onData("Login Successfully");
//                } else callback.onError(task.getException().getLocalizedMessage() + "");
            }
        });

    }

    public void getAllUsers(DataCallback<List<User>> orders) {
        FirebaseDatabase.getInstance().getReference("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<User> list = new ArrayList<>();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        list.add(snapshot1.getValue(User.class));
                    }
                    orders.onData(list);
                } else {
                    orders.onError("Users not found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                orders.onError(error + "");
            }
        });
    }

}

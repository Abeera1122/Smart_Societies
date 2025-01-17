package com.abidingtech.base.dao;

import com.abidingtech.base.callback.LogoutCallback;
import com.abidingtech.base.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDao {
    private String userId;
    private static UserDao _this;
    private LogoutCallback logoutCallback;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public static UserDao getInstance() {
        if (_this == null) {
            _this = new UserDao();
        }
        return _this;
    }

    public void setLogoutCallback(LogoutCallback logoutCallback) {
        this.logoutCallback = logoutCallback;
    }

    public void logout() {
        if (logoutCallback != null)
            logoutCallback.logout();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return FirebaseAuth.getInstance().getUid();
    }


}

package com.abidingtech.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.abidingtech.base.callback.DataCallback;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;

public class Utils {
    static ProgressDialog loading;

    public static String getDateTime(long timeMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        return simpleDateFormat.format(timeMillis);
    }

    public static void showConfirmationDialog(Context context, String title, String message, DataCallback<String> dataCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage("\n" + message + "\n");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            dataCallback.onData("Yes");
        }).setNegativeButton("No", (dialogInterface, i) -> {
            dataCallback.onError("No");

        });
        builder.create().show();
    }

    public static void loading(Context context) {
        if (loading != null && loading.isShowing()) {
            return;
        }
        loading = new ProgressDialog(context);
        loading.setCancelable(false);
        loading.setMessage("Please wait...");
        loading.show();
    }

    public static void dismiss() {
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
        loading = null;
    }

    public static <T> String getStringFromObject(T data) {
        return new Gson().toJson(data);
    }

    public static <T> T getObject(String json, Class clazz) {
        return (T) new Gson().fromJson(json, clazz);
    }

    public static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}

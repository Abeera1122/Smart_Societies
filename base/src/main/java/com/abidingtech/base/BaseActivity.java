package com.abidingtech.base;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.abidingtech.base.callback.ActivityResultCallBack;
import com.abidingtech.base.callback.DatePickCallback;
import com.abidingtech.base.callback.ImagePickerCallback;
import com.abidingtech.base.callback.ImagePickerCallbackV2;
import com.abidingtech.base.callback.ImageUploadCallback;
import com.abidingtech.base.callback.ResultCallback;
import com.abidingtech.base.dao.FirebaseImageDao;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class BaseActivity extends AppCompatActivity {
    ProgressDialog loading;
    private ResultCallback<Boolean> permission;
    private ImagePickerCallback imagePickerCallback;
    private ImagePickerCallbackV2 imagePickerCallbackV2;

    DatePickCallback datePickCallback = null;
    Calendar calendar = Calendar.getInstance();

    private DatePickerDialog.OnDateSetListener onDateSetListener = (datePicker, i, i1, i2) -> {
        calendar.set(i, i1, i2);

        if (datePickCallback != null)
            datePickCallback.onDate(i + "/" + i1 + "/" + i2);
    };




    public void pickDate(DatePickCallback datePickCallback) {
        this.datePickCallback = datePickCallback;

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private ActivityResultLauncher<String> imagePicker = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if (result != null) {
                if (imagePickerCallbackV2 != null) {
                    File folder = getExternalCacheDir();
                    try {
                        File file = File.createTempFile("TempFile", ".jpg", folder);
                        InputStream inputStream = getContentResolver().openInputStream(result);
                        FileUtils.copyInputStreamToFile(inputStream, file);
                        imagePickerCallbackV2.onImage(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                        imagePickerCallbackV2.onError(e.getLocalizedMessage() + "");
                    }
                }
                if (imagePickerCallback != null) {
                    imagePickerCallback.onImage(result);
                }
            } else {
                if (imagePickerCallback != null) {
                    imagePickerCallback.onError("Some thing went wrong");
                }
                if (imagePickerCallbackV2 != null) {
                    imagePickerCallback.onError("Some thing went wrong");

                }
            }
        }
    });
    private ActivityResultLauncher<String> permissionPicker = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            String message = result ? "Permission Granted" : "Some thing went wrong";
            Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
            if (permission != null) {
                permission.onResult(result);
                return;
            }
//            permission.onError("Please register ResultCallback");
        }
    });

    public void getImage(ImagePickerCallback imagePickerCallback) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionPicker.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return;
        }
        this.imagePickerCallback = imagePickerCallback;
        imagePicker.launch("image/*");
    }

    public void getImage(ImagePickerCallbackV2 imagePickerCallback) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionPicker.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return;
        }
        this.imagePickerCallbackV2 = imagePickerCallback;
        imagePicker.launch("image/*");
    }

    public void uploadImage(Uri uri, ImageUploadCallback imageUploadCallback) {
        FirebaseImageDao.getInstance().uploadImage("Categories", uri, imageUploadCallback);
    }

    public void showLoading() {
        if (loading != null && loading.isShowing())
            return;
        loading = new ProgressDialog(this);
        loading.setMessage("Please wait ...");
        loading.setCancelable(false);
        loading.show();
    }

    public void startActivityForResult(Intent intent, ActivityResultCallBack<Intent> intentActivityResultCallBack) {
        this.activityResultCallBackForIntent = intentActivityResultCallBack;
        this.intent.launch(intent);
    }

    private ActivityResultLauncher<Intent> intent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (activityResultCallBackForIntent == null)
                return;
            if (result.getResultCode() == RESULT_OK)
                activityResultCallBackForIntent.onResultOk(result.getData());
            else activityResultCallBackForIntent.onResultFailure();
        }
    });
    private ActivityResultCallBack<Intent> activityResultCallBackForIntent;


    public void dismiss() {
        if (loading == null)
            return;
        if (!loading.isShowing())
            return;

        loading.dismiss();
    }

    public void replaceFragment(int containerId, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(containerId, fragment).commit();
    }

}
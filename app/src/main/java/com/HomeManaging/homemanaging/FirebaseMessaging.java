package com.HomeManaging.homemanaging;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

public class FirebaseMessaging extends FirebaseMessagingService {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
//        Map<String, String> map = new HashMap<>();
        Map<String, String> map = message.getData();

        if (message.getNotification() != null) {
            map = new HashMap<>();
            map.put("title", message.getNotification().getTitle() + "");
            map.put("body", message.getNotification().getBody() + "");
        }
        FirebaseMessaging.createNotification(getApplicationContext(), map.get("title") + "", map.get("body") + "");
    }


    public static void initChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(context);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void createNotificationChannel(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "FOREGROUND_SERVICE_CHANNEL_ID";
        String channelName = "Foreground Service Channel";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    private static void createNotification(Context context, String title, String message) {
        NotificationCompat.Builder notificationBuilder =
                Build.VERSION.SDK_INT < Build.VERSION_CODES.O ? new NotificationCompat.Builder(context) : new NotificationCompat.Builder(context, "FOREGROUND_SERVICE_CHANNEL_ID");

        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(message);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(1, notificationBuilder.build());

//        notificationBuilder.setCustomContentView(remoteViews);


//        startForeground(1000, notificationBuilder.build());
    }

}

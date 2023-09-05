package com.example.weatherv2.WeatherUtilities;

import static android.content.ContentValues.TAG;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.weatherv2.Activities.MainActivity;
import com.example.weatherv2.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.concurrent.TimeUnit;

public class WeatherNotification {
    public static final String NOTIFICATION_CHANNEL_ID = "Weather Channel";
    public static final int NOTIFICATION_ID = 0;
    public static final int PENDING_INTENT_CODE = 1;
    public static long currentTime = 0 ;

    public static void sendNotificationAfterQuery(Context context) {
        String imageUrl = MainActivity.imageUrl;

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Forecast Notification",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setStyle(new NotificationCompat.BigTextStyle())
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setContentTitle("Today's Forecast")
                .setContentText(formatNotificationContentText())
                .setContentIntent(openActivityFromNotification(context))
                .setSmallIcon(R.drawable.weather)
                .setColorized(true)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setAutoCancel(true);

        Picasso.get().load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                notification.setLargeIcon(bitmap);
                currentTime = System.currentTimeMillis();
                if (TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis()) > MainActivity.DaysElapsed) {
                    notificationManager.notify(NOTIFICATION_ID, notification.build());
                }
                else{
                    Log.i(TAG, "onBitmapLoaded: Error in notificatioooooooooooon");

                }
            }


            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


    }


    private static PendingIntent openActivityFromNotification(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        return PendingIntent.
                getActivity(context,
                        PENDING_INTENT_CODE,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

    }

    private static String formatNotificationContentText() {
        return MainActivity.currentWeatherForecast.getWeatherDescription() + "  " +
                MainActivity.currentWeatherForecast.getMaxTemp() + "/"+
                MainActivity.currentWeatherForecast.getMinTemp() + " C";

    }


}

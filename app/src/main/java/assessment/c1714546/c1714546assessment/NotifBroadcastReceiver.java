package assessment.c1714546.c1714546assessment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import assessment.c1714546.c1714546assessment.updateWaterContent.UpdateWaterContentActivity;

/**
 * Created by c1714546 on 5/4/2018.
 */

public class NotifBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Following adapted from :
        // https://developer.android.com/training/notify-user/build-notification
        // Create an explicit intent for an Activity in your app
        String content = "Don't forget to make an entry for your water content today!";

        createNotificationChannel(context);

        Intent i = new Intent(context, UpdateWaterContentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(context, "Channel One")
                .setSmallIcon(R.drawable.ic_water_content_24dp)
                .setContentTitle("Water Life Daily Consumption")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, notifBuilder.build());
    }

    // Method adapted from :
    // https://developer.android.com/training/notify-user/build-notification
    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Water Life Channel One";
            String description = "Water Life Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Channel One", name, importance);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}

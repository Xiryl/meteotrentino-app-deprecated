package it.chiarani.meteotrentinoapp.services;

import android.util.Log;
import com.google.firebase.messaging.RemoteMessage;

// https://www.codementor.io/flame3/send-push-notifications-to-android-with-firebase-du10860kb
// https://medium.com/@mateuslb91/firebase-cloud-messaging-1cc8f1fbb4cf
// https://firebase.google.com/docs/cloud-messaging/android/receive

public class MessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
  private static final String TAG = "FCMService";
  @Override
  public void onMessageReceived(RemoteMessage remoteMessage) {
    // TODO: Handle FCM messages here.
    Log.d(TAG, "From: " + remoteMessage.getFrom());

    // Check if message contains a data payload.
    if (remoteMessage.getData().size() > 0) {
      Log.d(TAG, "Message data payload: " + remoteMessage.getData());
      //TODO: https://stackoverflow.com/questions/37407366/firebase-fcm-notifications-click-action-payload
    }

    // Check if message contains a notification payload.
    if (remoteMessage.getNotification() != null) {
      Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
      sendNotification(remoteMessage.getNotification().getBody());
    }
  }

  /**
   * Create and show a simple notification containing the received FCM message.
   *
   * @param messageBody FCM message body received.
   */
  private void sendNotification(String messageBody) {
/*
    // TODO: change activity to start here
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code , intent,
        PendingIntent.FLAG_ONE_SHOT);

    String channelId = "canale";
    Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    NotificationCompat.Builder notificationBuilder =
        new NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_w_rain)
            .setContentTitle("tutolo")
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent);

    NotificationManager notificationManager =
        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    // Since android Oreo notification channel is needed.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(channelId,
          "Channel human readable title",
          NotificationManager.IMPORTANCE_DEFAULT);
      notificationManager.createNotificationChannel(channel);
    }

    notificationManager.notify(0 /* ID of notification , notificationBuilder.build()); */
  }
}
package it.chiarani.meteotrentinoapp.servicies;

import android.app.Notification;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationDisplayedResult;
import com.onesignal.OSNotificationReceivedResult;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class NotificationExtenderExample extends NotificationExtenderService {
  @Override
  protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
    OverrideSettings overrideSettings = new OverrideSettings();

    WeatherReportRepository repo = new WeatherReportRepository(getApplication());
    repo.getAll().observeForever(entries -> {
      // TODO: check if is null
      overrideSettings.extender = new NotificationCompat.Extender() {
        @Override
        public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {

          SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
          Boolean servername = settings.getBoolean("pref_key_notifica_mattina", true);


          if(servername == true) {
            String x = entries.get(entries.size() - 1).getPrevisione().getGiorni().get(0).getDescIcona();
            return builder.setContentTitle("Buongiorno!")
                .setColor(getResources().getColor(R.color.colorAccent))
                .setGroup("gg")
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Il cielo oggi sarà " +
                    entries.get(entries.size() - 1).getPrevisione().getGiorni().get(0).getDescIcona()))
                .setPriority(Notification.PRIORITY_DEFAULT);
          }
          else
            return builder;
        }
      };
    });

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);

    Log.d("OneSignalExample", "Notification displayed with id: " + displayedResult.androidNotificationId);

    return true;
  }

}
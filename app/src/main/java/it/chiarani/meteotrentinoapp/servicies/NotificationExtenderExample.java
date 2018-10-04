package it.chiarani.meteotrentinoapp.servicies;

import android.app.Notification;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationDisplayedResult;
import com.onesignal.OSNotificationReceivedResult;
import com.onesignal.OneSignal;

import java.io.IOError;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class NotificationExtenderExample extends NotificationExtenderService {
  public boolean y = false;
  @Override
  protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    Boolean pref_notifica_mattina = settings.getBoolean("pref_key_notifica_mattina", true);

    OverrideSettings overrideSettings = new OverrideSettings();
    if(pref_notifica_mattina == false){
      OneSignal.clearOneSignalNotifications();
      OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);
      OneSignal.cancelNotification(displayedResult.androidNotificationId);
      return false;
    }

    WeatherReportRepository repo = new WeatherReportRepository(getApplication());
    repo.getAll().observeForever(entries -> {
      // TODO: check if is null
      overrideSettings.extender = new NotificationCompat.Extender() {
        @Override
        public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {

          String x = entries.get(entries.size() - 1).getPrevisione().getGiorni().get(0).getDescIcona();

          builder.setContentTitle("Buongiorno!")
              .setColor(getResources().getColor(R.color.colorAccent))
              .setContentText("Il cielo oggi sarà " +
                  entries.get(entries.size() - 1).getPrevisione().getGiorni().get(0).getDescIcona())
              .setGroup("meteotrentinoapp")
              .setAutoCancel(true)
              .setStyle(new NotificationCompat.BigTextStyle().bigText("Il cielo oggi sarà " +
                  entries.get(entries.size() - 1).getPrevisione().getGiorni().get(0).getDescIcona()))
              .setPriority(Notification.PRIORITY_HIGH);

          y = true;
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
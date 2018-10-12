package it.chiarani.meteotrentinoapp.servicies;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationDisplayedResult;
import com.onesignal.OSNotificationReceivedResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.io.IOError;
import java.util.Calendar;
import java.util.TimeZone;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.CustomAlertEntity;
import it.chiarani.meteotrentinoapp.repositories.CustomAlertRepository;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;
import it.chiarani.meteotrentinoapp.views.MessageActivity;

public class NotificationExtenderExample extends NotificationExtenderService {

  private static final String HIGH_PRIORITY_URGENT_ALARM_TAG = "HIGH_PRIORITY_URGENT_ALARM";
  private static final String ALARM_TAG = "BACKGROUND-DATA";
  boolean alarm = false;

  @Override
  protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    Boolean pref_notifica_mattina = settings.getBoolean("pref_key_notifica_mattina", true);


    JSONObject data = receivedResult.payload.additionalData;
    String customKey = data.optString(HIGH_PRIORITY_URGENT_ALARM_TAG);
    if(!customKey.isEmpty()) {
      Calendar start = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
      long now = start.getTimeInMillis();

      CustomAlertRepository repository = new CustomAlertRepository(getApplication());
      repository.insert(new CustomAlertEntity(customKey,now));

      Intent message_alert_i = new Intent(this, MessageActivity.class);
      message_alert_i.putExtra("payload", customKey);
      startActivity(message_alert_i);
    }

    String allerta = data.optString(ALARM_TAG);
    if(!allerta.isEmpty()) {
      OverrideSettings overrideSettings = new OverrideSettings();
      OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);
      return true;
    }

    OverrideSettings overrideSettings = new OverrideSettings();
    if(pref_notifica_mattina == false){
      alarm=true;
    }

    WeatherReportRepository repo = new WeatherReportRepository(getApplication());
    repo.getAll().observeForever(entries -> {
      // TODO: check if is null
      overrideSettings.extender = new NotificationCompat.Extender() {
        @Override
        public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {

          if(alarm) {
            return builder;
          }

          Calendar start = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
          long now = start.getTimeInMillis();

          if((entries.get(entries.size()-1).getDataInserimentoDb() <= now && entries.get(entries.size()-1).getDataInserimentoDb() >= (now - 25200000)) ||
              entries.get(entries.size()-1).getDataInserimentoDb() >= now)
          {
            // se la data dell'inserimento nel db è compresa tra la mezzanotte di oggi e le sette di mattina
            builder.setContentTitle("Buongiorno!")
                .setColor(getResources().getColor(R.color.colorAccent))
                .setContentText("Il cielo oggi sarà " +
                    entries.get(entries.size() - 1).getPrevisione().getGiorni().get(0).getDescIcona())
                .setGroup("meteotrentinoapp")
                .setAutoCancel(true)
                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.blue_main))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Il cielo oggi sarà " +
                    entries.get(entries.size() - 1).getPrevisione().getGiorni().get(0).getDescIcona()))
                .setPriority(Notification.PRIORITY_HIGH);
            return builder;
          }
          {
            // sennò prendo il giorno[2] del database che punta ad oggi
            builder.setContentTitle("Buongiorno!")
                .setColor(getResources().getColor(R.color.colorAccent))
                .setContentText("Il cielo oggi sarà " +
                    entries.get(entries.size() - 1).getPrevisione().getGiorni().get(1).getDescIcona())
                .setGroup("meteotrentinoapp")
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Il cielo oggi sarà " +
                    entries.get(entries.size() - 1).getPrevisione().getGiorni().get(1).getDescIcona()))
                .setPriority(Notification.PRIORITY_HIGH);
            return builder;
          }
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
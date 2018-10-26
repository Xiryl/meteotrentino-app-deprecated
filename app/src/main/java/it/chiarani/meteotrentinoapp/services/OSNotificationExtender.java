package it.chiarani.meteotrentinoapp.services;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationDisplayedResult;
import com.onesignal.OSNotificationReceivedResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.TimeZone;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.CustomAlertEntity;
import it.chiarani.meteotrentinoapp.repositories.CustomAlertRepository;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;
import it.chiarani.meteotrentinoapp.views.MessageActivity;

public class OSNotificationExtender extends NotificationExtenderService {

  // #region private fields
  private static final String HIGH_PRIORITY_URGENT_ALARM_TAG = "HIGH_PRIORITY_URGENT_ALARM";
  private static final String ALARM_TAG = "BACKGROUND-DATA";
  private static final String SETTINGS_MORNING_MESSAGE_KEY = "pref_key_notifica_mattina";
  private static final String SETTINGS_ALLERTA_KEY = "pref_key_notifica_allerta";
  private static final String CLASS_TAG = "OSNotificationExtender";
  boolean show_notification = true;
  // #endregion


  /**
   * Override Notification Processor
   */
  @Override
  protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {

    // Ottengo dalle preferenze la scelta dell'utente
    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    Boolean pref_notifica_mattina = settings.getBoolean(SETTINGS_MORNING_MESSAGE_KEY, true);
    Boolean pref_notifica_allerta = settings.getBoolean(SETTINGS_ALLERTA_KEY, true);

    // Rimuovo le notifiche precedenti
    OneSignal.clearOneSignalNotifications();

    if (!pref_notifica_allerta) {
      show_notification = false;
    }
    else {

      try {

        JSONObject data = receivedResult.payload.additionalData;
        String notification_hp_payload = data.optString(HIGH_PRIORITY_URGENT_ALARM_TAG);

        if (notification_hp_payload != null && !notification_hp_payload.isEmpty()) {
          // ----------------
          // NOTIFICA URGENTE
          // ----------------

          Calendar start = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
          long now = start.getTimeInMillis();

          // Salvo nel database il payload
          CustomAlertRepository repository = new CustomAlertRepository(getApplication());
          repository.insert(new CustomAlertEntity(notification_hp_payload, now));

          // Lancio l'activity di allerta
          Intent message_alert_i = new Intent(this, MessageActivity.class);
          message_alert_i.putExtra("payload", notification_hp_payload);
          startActivity(message_alert_i);
        }

        String notification_normal_alarm_payload = data.optString(ALARM_TAG);
        if (!notification_normal_alarm_payload.isEmpty()) {
          // -------------------------
          // NOTIFICA ALLERTA CLASSICA
          // -------------------------

          OverrideSettings overrideSettings = new OverrideSettings();
          OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);
          return true;
        }
      } catch (Exception ex) {
        Log.d(CLASS_TAG, ex.getMessage());
      }
    }

    OverrideSettings overrideSettings = new OverrideSettings();

    if(!pref_notifica_mattina){
      // Skip
      show_notification = false;
    }
    else {
      // Mostro meteo giornaliero

      WeatherReportRepository repo = new WeatherReportRepository(getApplication());
      repo.getAll().observeForever(entries -> {
        // TODO: check if is null
        overrideSettings.extender = new NotificationCompat.Extender() {
          @Override
          public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {

            Calendar start = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
            long now = start.getTimeInMillis();

            if ((entries.get(entries.size() - 1).getDataInserimentoDb() <= now && entries.get(entries.size() - 1).getDataInserimentoDb() >= (now - 25200000)) ||
                entries.get(entries.size() - 1).getDataInserimentoDb() >= now) {

              // se la data dell'inserimento nel db è compresa tra la mezzanotte di oggi e le sette di mattina
              builder.setContentTitle(getApplicationContext().getResources().getString(R.string.notification_title))
                  .setColor(getResources().getColor(R.color.colorAccent))
                  .setContentText(getApplicationContext().getResources().getString(R.string.notification_descr) +
                      entries.get(entries.size() - 1).getPrevisione().getGiorni().get(0).getDescIcona())
                  .setGroup("meteotrentinoapp")
                  .setAutoCancel(true)
                  .setColor(ContextCompat.getColor(getApplicationContext(), R.color.blue_main))
                  .setStyle(new NotificationCompat.BigTextStyle().bigText(getApplicationContext().getResources().getString(R.string.notification_descr) +
                      entries.get(entries.size() - 1).getPrevisione().getGiorni().get(0).getDescIcona()))
                  .setPriority(Notification.PRIORITY_HIGH);
              return builder;
            }
            {

              // sennò prendo il giorno[2] del database che punta ad oggi
              builder.setContentTitle(getApplicationContext().getResources().getString(R.string.notification_title))
                  .setColor(getResources().getColor(R.color.colorAccent))
                  .setContentText(getApplicationContext().getResources().getString(R.string.notification_descr) +
                      entries.get(entries.size() - 1).getPrevisione().getGiorni().get(1).getDescIcona())
                  .setGroup("meteotrentinoapp")
                  .setAutoCancel(true)
                  .setStyle(new NotificationCompat.BigTextStyle().bigText(getApplicationContext().getResources().getString(R.string.notification_descr) +
                      entries.get(entries.size() - 1).getPrevisione().getGiorni().get(1).getDescIcona()))
                  .setPriority(Notification.PRIORITY_HIGH);
              return builder;
            }
          }
        };
      });
    }

    try {
      Thread.sleep(5000);
    }
    catch (InterruptedException e) {
      Log.e(CLASS_TAG, e.getMessage());
    }

    if (show_notification) {
      OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);
      Log.d(OSNotificationExtender.class.getSimpleName(), "Notification displayed with id: " + displayedResult.androidNotificationId);
    }

    return true;
  }

}
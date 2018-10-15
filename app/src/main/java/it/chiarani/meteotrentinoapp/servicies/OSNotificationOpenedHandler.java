package it.chiarani.meteotrentinoapp.servicies;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.TimeZone;

import it.chiarani.meteotrentinoapp.database.entity.CustomAlertEntity;
import it.chiarani.meteotrentinoapp.repositories.CustomAlertRepository;
import it.chiarani.meteotrentinoapp.views.MessageActivity;

public class OSNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {

  private Application application;

  public OSNotificationOpenedHandler(Application application) {
    this.application = application;
  }

  private final static String BACKGROUND_TAG = "BACKGROUND-DATA";

  @Override
  public void notificationOpened(OSNotificationOpenResult result) {

    JSONObject data = result.notification.payload.additionalData;
    String customKey = data.optString(BACKGROUND_TAG);
    if(!customKey.isEmpty()) {
      Calendar start = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
      long now = start.getTimeInMillis();

      CustomAlertRepository repository = new CustomAlertRepository(application);
      repository.insert(new CustomAlertEntity(customKey,now));
    }
  }
}
package it.chiarani.meteotrentinoapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;
import com.onesignal.OneSignal;
import com.takusemba.spotlight.OnSpotlightStateChangedListener;
import com.takusemba.spotlight.OnTargetStateChangedListener;
import com.takusemba.spotlight.Spotlight;
import com.takusemba.spotlight.shape.Circle;
import com.takusemba.spotlight.target.SimpleTarget;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import hotchemi.android.rate.AppRate;
import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.WeatherSevenDayAdapter;
import it.chiarani.meteotrentinoapp.api.API_endpoint;
import it.chiarani.meteotrentinoapp.database.entity.CustomAlertEntity;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForWeekEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.databinding.ActivityMainBinding;
import it.chiarani.meteotrentinoapp.helper.CustomDialog;
import it.chiarani.meteotrentinoapp.helper.DialogShower;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;
import it.chiarani.meteotrentinoapp.repositories.CustomAlertRepository;
import it.chiarani.meteotrentinoapp.repositories.OpenWeatherDataRepository;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;
import it.chiarani.meteotrentinoapp.services.OSNotificationOpenedHandler;

public class MainActivity extends SampleActivity {

  // #region private fields
  private final static String MAINACTIVITY_TAG = "MAINACTIVITY";
  private final static String KEY_FIRST_POS = "first_pos";
  private final static String KEY_SECOND_POS = "second_pos";
  private ActivityMainBinding binding;
  String first_pos;
  String second_pos;
  // #endregion

  @Override
  protected int getLayoutID() {
    return R.layout.activity_main;
  }

  @Override
  protected void setActivityBinding() {
    binding = DataBindingUtil.setContentView(this, getLayoutID());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Log.d(MAINACTIVITY_TAG, "Start mainactivity");

    // tips
    SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    sendTips(getPrefs);

    // rate app
    rateApp();

    // build repository
    WeatherReportRepository repository = new WeatherReportRepository(this.getApplication());

    // OneSignal Initialization
    initOnesignal();

    // animate icon
    Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
    binding.activityMainIcWeatherIcon.startAnimation(pulse);

    // set toolbar color
    Window window = this.getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

    // get user pref
    first_pos = getPrefs.getString(KEY_FIRST_POS,   getResources().getString(R.string.first_pref));
    second_pos = getPrefs.getString(KEY_SECOND_POS, getResources().getString(R.string.second_pref));

    // Update menu values
    Menu menu = binding.mainActivityNavView.getMenu();
    MenuItem first_pref  = menu.findItem(R.id.drawer_view_first_pref);
    MenuItem second_pref = menu.findItem(R.id.drawer_view_second_pref);
    MenuItem app_version = menu.findItem(R.id.drawer_view_app_version);

    app_version.setTitle("3.3-stabile");
    first_pref. setTitle(first_pos);
    second_pref.setTitle(second_pos);

    // build left menu
    initLeftMenuDrawer();

      binding.mainActivityBtnBullettin.setOnClickListener(v -> {
          Intent intentbullet = new Intent(MainActivity.this, ProbBulletActivity.class);
          startActivity(intentbullet);
      });


    // speak button
    binding.mainActivityBtnSpeak.setOnClickListener( v ->{
      Intent faqintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://content.meteotrentino.it/Bollettini/Audio/sintetico.mp3"));
      startActivity(faqintent);
    });

    // show weather data
    repository.getAll().observe(this, entries -> {

      if (entries == null || entries.isEmpty()) {
        Toast.makeText(this, getResources().getString(R.string.main_no_wether_data), Toast.LENGTH_SHORT).show();
        return;
      }

      CustomAlertRepository alertRepository = new CustomAlertRepository(getApplication());

      // check for alerts
      alertRepository.getAll().observe(this, alertEntities -> {

        if(alertEntities != null && alertEntities.size() > 0 ) {
          Calendar start = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
          long now = start.getTimeInMillis();
          CustomAlertEntity last_alert = alertEntities.get(alertEntities.size() -1);

          if(last_alert.getAlertTime() <= (now + 3600000)) {
            Intent message_alert_i = new Intent(this, MessageActivity.class);
            message_alert_i.putExtra("payload", last_alert.getAlertDescription());
            startActivity(message_alert_i);
            this.finish();
          }
        }
      });
      // -- end alert repo --

      WeatherReportEntity wfr   = entries.get(entries.size() - 1);
      WeatherForWeekEntity wfw  = wfr.getPrevisione();
      WeatherForDayEntity wfd   = wfw.getGiorni().get(0);

      // set values
      binding.activityMainTxtMaxTemperature.setText(String.format("%s", wfd.gettMaxGiorno())); // Temp. MAX
      binding.activityMainTxtMinTemperature.setText(String.format("%s", wfd.gettMinGiorno())); // Temp. MIN
      binding.activityMainTxtRain.setText(String.format("%s%%",wfd.getFasce().get(0).getDescPrecProb()));
      binding.activityMainTxtPosition.setText(String.format("%s (%s m)", wfw.getLocalita(), wfw.getQuota()));
      binding.activityMainTxtWeatherDescription.setText(wfd.getDescIcona());

      // set week forecast
      binding.fragmentMainRvWeatherSlot.setHasFixedSize(true);
      LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
      binding.fragmentMainRvWeatherSlot.setLayoutManager(horizontalLayoutManagaer);
      WeatherSevenDayAdapter adapter = new WeatherSevenDayAdapter(wfr);
      binding.fragmentMainRvWeatherSlot.setAdapter(adapter);

      // show alert
      if(!wfd.getDescIconaAllerte().isEmpty()) {
        binding.activityMainTxtAllerta.setText(String.format("ALLERTA: %s",wfd.getDescIconaAllerte()));
      }

      // show today bulletin if weather icon is clicked
      binding.activityMainIcWeatherIcon.setOnClickListener(v -> {
        String txt = wfd.getTestoGiorno();

        CustomDialog cdd = new CustomDialog(MainActivity.this, "Previsione di oggi:\n" + txt);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
      });


      // opwen weather values
      OpenWeatherDataRepository repository_op = new OpenWeatherDataRepository(getApplication());
      repository_op.getAll().observe(this, (List<OpenWeatherDataEntity> od_entries) -> {
        if (od_entries.isEmpty() || od_entries.size() == 0) {
          Toast.makeText(this, getResources().getString(R.string.main_no_wether_data), Toast.LENGTH_SHORT);
          return;
        }


        OpenWeatherDataEntity opw = od_entries.get(od_entries.size() - 1);
        NumberFormat formatter_w = new DecimalFormat("#0.00");
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Double w = Double.parseDouble(opw.getWindSpeed()) * 3.6;

        // set values
        binding.activityMainTxtActTemperature .setText(String.format("%s",opw.getActualTemperature()));
        binding.activityMainTxtHumidity       .setText(String.format("%s%%",opw.getHumidity()));
        binding.activityMainTxtWind           .setText(String.format("%s km/h", formatter_w.format(w)));

        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        Calendar start = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));

        binding.mainActivityTxtUltimoAgg.setText(String.format("Alba: %s - Tramonto %s\nAggiornato alle: %s", formatter.format(opw.getSunrise()), formatter.format(opw.getSunset()), formatter.format(entries.get(entries.size()-1).getDataInserimentoDb())));

        // ------ ------ ------
        // SET BACKGROUND IMAGE
        // ------ ------ ------

        Boolean isNight = false;
        long now      = start.getTimeInMillis();
        long sunset   = opw.getSunset();
        long sunrise  = opw.getSunrise();

        // day
          binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_snow_day);
          window.setStatusBarColor(Color.parseColor("#5EB6E2"));


        if(now <= (sunrise - 1800000)) {
          // night
          binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_snow_night);
          window.setStatusBarColor(Color.parseColor("#29486B"));
          isNight = true;
        }
        if(now > (sunrise - 1800000) && now < (sunrise + 1800000)) {
          // sunrise
          binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_snow_sunrise);
          window.setStatusBarColor(Color.parseColor("#E8AF43"));
          binding.activityMainTxtAllerta.setTextColor(Color.parseColor("#12329B"));
        }
        if(now >= (sunset - 900000) && now < (sunset + 900000)  ){
          // sunset
            binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_snow_sunset);
            window.setStatusBarColor(Color.parseColor("#C95941"));
        }
        if(  now >= (sunset + 900000)) {
          // night
          binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_snow_night);
          window.setStatusBarColor(Color.parseColor("#345A7B"));
          isNight = true;
        }


        // -------------
        // SET BIG IMAGE
        // -------------
        switch (WeatherIconDescriptor.getWeatherType(wfd.getIcona())) {
          case COPERTO:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_cloud);
            break;

          case COPERTO_CON_PIOGGIA:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_light_rain);
            binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_snow_rain);
            window.setStatusBarColor(getResources().getColor(R.color.toolbar_cloud));
            break;

          case COPERTO_CON_PIOGGIA_ABBONDANTE:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_rain);
            binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_snow_rain);
            window.setStatusBarColor(getResources().getColor(R.color.toolbar_cloud));
            break;

          case COPERTO_CON_PIOGGIA_E_NEVE:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_snow_rain);
            binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_snow_rain);
            window.setStatusBarColor(getResources().getColor(R.color.toolbar_cloud));
            break;

          case NEVICATA:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_snow);
            break;

          case SOLE:
            if(isNight)
              binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_moon);
            else
              binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_sun);
            break;

          case SOLEGGIATO:
            if(isNight)
              binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_moon);
            else
              binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_sun_cloud);
            break;

          case SOLEGGIATO_CON_PIOGGIA:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_sun_cloud_rain);
            binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_snow_rain);
            window.setStatusBarColor(getResources().getColor(R.color.toolbar_cloud));
            break;

          case SOLEGGIATO_CON_PIOGGIA_E_NEVE:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_sun_cloud_rain_snow);
            binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_snow_rain);
            window.setStatusBarColor(getResources().getColor(R.color.toolbar_cloud));
            break;

          case TEMPORALE:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_thunderstorm);
            binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_snow_rain);
            window.setStatusBarColor(getResources().getColor(R.color.toolbar_cloud));
            break;

          case UNDEFINED:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_sun_cloud);
            break;
        }

        callHelperGuide(getPrefs);
      });
    });


  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
  }

  @Override
  protected void onResume() {
    super.onResume();

    // preferences
    SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

    // get user pref
    first_pos = getPrefs.getString(KEY_FIRST_POS,   getResources().getString(R.string.first_pref));
    second_pos = getPrefs.getString(KEY_SECOND_POS, getResources().getString(R.string.second_pref));

    Menu menu             = binding.mainActivityNavView.getMenu();
    MenuItem first_pref   = menu.findItem(R.id.drawer_view_first_pref);
    MenuItem second_pref  = menu.findItem(R.id.drawer_view_second_pref);

    first_pref.setTitle(first_pos);
    second_pref.setTitle(second_pos);

    CustomAlertRepository alertRepository = new CustomAlertRepository(getApplication());
    alertRepository.getAll().observe(this, alertEntities -> {

      if(alertEntities != null && alertEntities.size() > 0 ) {
          // if got an alert start message activity

          Intent message_alert_i = new Intent(this, MessageActivity.class);
          message_alert_i.putExtra("payload", alertEntities.get(alertEntities.size()-1).getAlertDescription());
          startActivity(message_alert_i);
          this.finish();
      }
      else
      {
        WeatherReportRepository repository = new WeatherReportRepository(this.getApplication());
        repository.getAll().observe(this, entries -> {
          if (entries == null || entries.isEmpty()) {
            // No loc
            DialogShower.ShowDialog(this,new Intent(getApplicationContext(), ChooseLocationActivity.class),"Località non trovata", "Non sono riuscito a rilevare la tua posizione dal GPS.\nProva a ricercala manualmente!", "Ricerca", "Annulla");
            return;
          }

          binding.activityMainIcWeatherIcon.setOnClickListener(v -> {
            String txt = entries.get(entries.size() - 1).getPrevisione().getGiorni().get(0).getTestoGiorno();
            CustomDialog cdd = new CustomDialog(MainActivity.this, "Previsione di oggi:\n" + txt);
            cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cdd.show();
          });

          // check time for reload weather meteo ::= 2h
          if(System.currentTimeMillis() >= (entries.get(entries.size() - 1).getDataInserimentoDb() + (7200000) ) ) {
            Intent i = new Intent(MainActivity.this, LoaderActivity.class);
            i.putExtra("POSITION", entries.get(entries.size()-1).getPrevisione().getLocalita());
            this.startActivity(i);
          }
        });
      }
    });
  }

  private void sendTips(SharedPreferences getPrefs) {
    boolean isMsgNotifiche = getPrefs.getBoolean("msg_reminder", true);
      boolean isMsgBollettino = getPrefs.getBoolean("msg_bollettino", true);
      boolean isMsggps = getPrefs.getBoolean("msg_gps", true);

      if (isMsggps) {
          //  Make a new preferences editor
          SharedPreferences.Editor e = getPrefs.edit();

          //  Edit preference to make it false because we don't want this to run again
          e.putBoolean("msg_gps", false);

          //  Apply changes
          e.apply();

          CustomDialog cdd = new CustomDialog(MainActivity.this, "TIP: Dalle impostazioni è possibile disattivare la richiesta del GPS per sempre!");
          cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
          cdd.show();
      }

      if (isMsgNotifiche) {
          //  Make a new preferences editor
          SharedPreferences.Editor e = getPrefs.edit();

          //  Edit preference to make it false because we don't want this to run again
          e.putBoolean("msg_reminder", false);

          //  Apply changes
          e.apply();

          CustomDialog cdd = new CustomDialog(MainActivity.this, "TIP: Dalle impostazioni puoi attivare/disattivare le notifiche mattutine!");
          cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
          cdd.show();
      }

    if (isMsgBollettino) {
      //  Make a new preferences editor
      SharedPreferences.Editor e = getPrefs.edit();

      //  Edit preference to make it false because we don't want this to run again
      e.putBoolean("msg_bollettino", false);

      //  Apply changes
      e.apply();

      CustomDialog cdd = new CustomDialog(MainActivity.this, "TIP: Premi sull'immagine del sole/nuvola per leggere direttamente il bollettino!");
      cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      cdd.show();
    }
  }

    private void rateApp() {
      AppRate.with(this)
          .setInstallDays(4)
          .setLaunchTimes(10)
          .setRemindInterval(2)
          .setShowLaterButton(true)
          .setDebug(false)
          .setOnClickButtonListener(which -> Log.d(MainActivity.class.getName(), Integer.toString(which)))
          .monitor();

      // Show a dialog if meets conditions
      AppRate.showRateDialogIfMeetsConditions(this);
    }

    private void initOnesignal() {
      OneSignal.startInit(this)
          .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
          .unsubscribeWhenNotificationsAreDisabled(true)
          .setNotificationOpenedHandler(new OSNotificationOpenedHandler(getApplication()))
          .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
          .autoPromptLocation(true)
          .init();
  }

  private void initLeftMenuDrawer() {

    binding.mainActBtnMenu.setOnClickListener(v -> binding.mainActivityDrawerLayout.openDrawer(Gravity.LEFT));


    binding.mainActivityNavView.setNavigationItemSelectedListener(
        menuItem -> {
          // set item as selected to persist highlight
          menuItem.setChecked(true);

          switch (menuItem.getItemId()){
              case R.id.drawer_view_widget :
                  Intent widget_intent = new Intent(MainActivity.this, WidgetDescriptionActivity.class);
                  startActivity(widget_intent);
                  break;

              case R.id.drawer_view_dighe :
                  Intent bacini_intent = new Intent(this, DigheBaciniActivity.class);
                  startActivity(bacini_intent);
                  break;

              case R.id.drawer_view_bollettino_prob:
                  Intent bollet_prob = new Intent(this, ProbBulletActivity.class);
                  startActivity(bollet_prob);
                  break;
            case R.id.drawer_view_faq:
              Intent faqintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Xiryl/MeteoTrentino-App/blob/master/README.md#faq"));
              startActivity(faqintent);
              break;
            case R.id.drawer_view_app_version:
              CustomDialog cdd = new CustomDialog(MainActivity.this, "Versione v3.3-stabile\n-Migliorata esperienza utente\n-Preparazione dei Widget");
              cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
              cdd.show();
              break;
            case R.id.drawer_view_first_pref:
              if(first_pos.isEmpty() || first_pos.equals("Aggiungi 1° Preferito")) {
                Intent chooseloc_intent = new Intent(MainActivity.this, ChooseLocationActivity.class);
                chooseloc_intent.putExtra("PREF_NUMBER", 1);
                startActivity(chooseloc_intent);
              }
              else
              {
                Intent myIntent = new Intent(this, LoaderActivity.class);
                myIntent.putExtra("POSITION", first_pos);
                startActivity(myIntent);
              }
              break;
            case R.id.drawer_view_second_pref:
              if(second_pos.isEmpty()  || second_pos.equals("Aggiungi 2° Preferito")) {
                Intent chooseloc_intent = new Intent(MainActivity.this, ChooseLocationActivity.class);
                chooseloc_intent.putExtra("PREF_NUMBER", 2);
                startActivity(chooseloc_intent);
              }
              else
              {
                Intent myIntent = new Intent(this, LoaderActivity.class);
                myIntent.putExtra("POSITION", second_pos);
                startActivity(myIntent);
              }
              break;

            case R.id.drawer_view_search:
              Intent chooseloc_intent = new Intent(MainActivity.this, ChooseLocationActivity.class);
              startActivity(chooseloc_intent);
              break;

            case R.id.drawer_view_radar:
              Intent radar_intent = new Intent(MainActivity.this, RadarActivity.class);
              startActivity(radar_intent);
              break;

            case R.id.drawer_view_staz_meteorologiche:
              Intent staz_intent = new Intent(MainActivity.this, WeatherStationActivity.class);
              startActivity(staz_intent);
              break;

            case R.id.drawer_view_staz_neve:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://avalanche.report"));
                startActivity(browserIntent);
              break;

            case R.id.drawer_view_montagna:
                Intent montagnaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(API_endpoint.URL_BOLLETTINO_MONTAGNA));
                startActivity(montagnaIntent);
              break;

            case R.id.drawer_view_allerte:
              Intent allerte_intent = new Intent(MainActivity.this, AllerteActivity.class);
              startActivity(allerte_intent);
              break;

            case R.id.drawer_view_webcam:
              Intent webcam_intent = new Intent(MainActivity.this, WebcamActivity.class);
              startActivity(webcam_intent);
              break;

            case R.id.drawer_view_telegram:
              Intent telegram_intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.t.me/MeteoTrentinoBot"));
              startActivity(telegram_intent);
              break;

            case R.id.drawer_view_settings:
              Intent settings_intent =  new Intent(MainActivity.this, SettingsActivity.class);
              startActivity(settings_intent);
              break;

            case R.id.drawer_view_info:
              Intent info_intent =  new Intent(MainActivity.this, InfoActivity.class);
              startActivity(info_intent);
              break;
          }
          return true;
        });
  }

  private void callHelperGuide(SharedPreferences getPrefs) {

    //  Create a new boolean and preference and set it to true
    boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

    //  If the activity has never started before...
    if (isFirstStart) {
      SimpleTarget simpleTarget = new SimpleTarget.Builder(this)
          .setPoint(binding.mainActBtnMenu)
          .setShape(new Circle(200f))
          .setTitle("Maggiori Funzionalità")
          .setDescription("Apri il menù laterale per ottenere più funzionalità")
          .setOnSpotlightStartedListener(new OnTargetStateChangedListener<SimpleTarget>() {
            @Override
            public void onStarted(SimpleTarget target) {
            }
            @Override
            public void onEnded(SimpleTarget target) {
            }
          })
          .build();

      SimpleTarget simpleTarget1 = new SimpleTarget.Builder(this)
          .setPoint(binding.activityMainLlSlideUp)
          .setShape(new Circle(200f))
          .setTitle("Scopri il meteo settimanale")
          .setDescription("Alza la tendina e premi sul giorno per ottenere il bollettino metereologico")
          .setOnSpotlightStartedListener(new OnTargetStateChangedListener<SimpleTarget>() {
            @Override
            public void onStarted(SimpleTarget target) {
            }

            @Override
            public void onEnded(SimpleTarget target) {
            }
          })
          .build();

      Spotlight.with(this)
          .setOverlayColor(R.color.background)
          .setDuration(10)
          .setAnimation(new DecelerateInterpolator(1f))
          .setTargets(simpleTarget, simpleTarget1)
          .setClosedOnTouchedOutside(true)
          .setOnSpotlightStateListener(new OnSpotlightStateChangedListener() {
            @Override
            public void onStarted() {
            }

            @Override
            public void onEnded() {
            }
          })
          .start();

      //  Make a new preferences editor
      SharedPreferences.Editor e = getPrefs.edit();

      //  Edit preference to make it false because we don't want this to run again
      e.putBoolean("firstStart", false);

      //  Apply changes
      e.apply();
    }
  }
}
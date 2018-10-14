package it.chiarani.meteotrentinoapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import hotchemi.android.rate.OnClickButtonListener;
import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.WeatherSevenDayAdapter;
import it.chiarani.meteotrentinoapp.database.entity.CustomAlertEntity;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForWeekEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.databinding.ActivityMainBinding;
import it.chiarani.meteotrentinoapp.helper.DialogShower;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;
import it.chiarani.meteotrentinoapp.models.WeatherReport;
import it.chiarani.meteotrentinoapp.repositories.CustomAlertRepository;
import it.chiarani.meteotrentinoapp.repositories.OpenWeatherDataRepository;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;
import it.chiarani.meteotrentinoapp.servicies.NotificationOpenedHandler;

public class MainActivity extends SampleActivity{

  // #region private fields
  private final static String MAINACTIVITY_TAG = "MAINACTIVITY";
  private ActivityMainBinding binding;
  private WeatherReport _report;
  private final static String INTENT_USER_LOCATION_TAG = "user_location";
  private boolean flocation = false;
  private boolean slocation = false;
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

    AppRate.with(this)
        .setInstallDays(4) // default 10, 0 means install day.
        .setLaunchTimes(10) // default 10
        .setRemindInterval(2) // default 1
        .setShowLaterButton(true) // default true
        .setDebug(false) // default false
        .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
          @Override
          public void onClickButton(int which) {
            Log.d(MainActivity.class.getName(), Integer.toString(which));
          }
        })
        .monitor();

    // Show a dialog if meets conditions
    AppRate.showRateDialogIfMeetsConditions(this);

    // get repository
    WeatherReportRepository repository = new WeatherReportRepository(this.getApplication());


    // OneSignal Initialization
    OneSignal.startInit(this)
        .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
        .unsubscribeWhenNotificationsAreDisabled(true)
        .setNotificationOpenedHandler(new NotificationOpenedHandler(getApplication()))
        .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
        .autoPromptLocation(true)
        .init();

    binding.mainActBtnMenu.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        binding.mainActivityDrawerLayout.openDrawer(Gravity.LEFT);
      }
    });

    // set toolbar color
    Window window = this.getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

    // preferences
    SharedPreferences getPrefs = PreferenceManager
        .getDefaultSharedPreferences(getBaseContext());

    // create a new boolean and preference
    first_pos = getPrefs.getString("first_pos", "Aggiungi 1° Preferito");
    second_pos = getPrefs.getString("second_pos", "Aggiungi 2° Preferito");

    Menu menu = binding.mainActivityNavView.getMenu();
    MenuItem first_pref = menu.findItem(R.id.drawer_view_first_pref);
    MenuItem second_pref = menu.findItem(R.id.drawer_view_second_pref);

    first_pref.setTitle(first_pos);
    second_pref.setTitle(second_pos);

    binding.mainActivityNavView.setNavigationItemSelectedListener(
        menuItem -> {
          // set item as selected to persist highlight
          menuItem.setChecked(true);

          switch (menuItem.getItemId()){

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
              Toast.makeText(this, "Servizio attivo solo d'inverno", Toast.LENGTH_LONG).show();
              break;

            case R.id.drawer_view_bollettini:
              Intent bulletin_intent = new Intent(MainActivity.this, BulletinActivity.class);
              startActivity(bulletin_intent);
              break;

            case R.id.drawer_view_allerte:
              Intent allerte_intent = new Intent(MainActivity.this, AllerteActivity.class);
              startActivity(allerte_intent);
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

    repository.getAll().observe(this, entries -> {
      if (entries == null || entries.isEmpty() || entries.size() == 0) {
        Toast.makeText(this, "Dati meteo non disponibili, riprova più tardi", Toast.LENGTH_SHORT).show();
        return;
      }

      CustomAlertRepository alertRepository = new CustomAlertRepository(getApplication());
      alertRepository.getAll().observe(this, alertEntities -> {
        if(alertEntities.size() > 0 ) {
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

      WeatherReportEntity wfr = entries.get(entries.size() - 1);
      WeatherForWeekEntity wfw = wfr.getPrevisione();
      WeatherForDayEntity wfd = wfw.getGiorni().get(0);

      binding.activityMainTxtMaxTemperature.setText(wfd.gettMaxGiorno() + ""); // Temp. MAX
      binding.activityMainTxtMinTemperature.setText(wfd.gettMinGiorno() + ""); // Temp. MIN

      // Lista Meteo 7 giorni
      binding.fragmentMainRvWeatherSlot.setHasFixedSize(true);
      LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
      binding.fragmentMainRvWeatherSlot.setLayoutManager(horizontalLayoutManagaer);
      WeatherSevenDayAdapter adapter = new WeatherSevenDayAdapter(wfr);
      binding.fragmentMainRvWeatherSlot.setAdapter(adapter);

      binding.activityMainTxtRain.setText(wfd.getFasce().get(0).getDescPrecProb() + "%");

      binding.activityMainTxtPosition.setText(wfw.getLocalita());
      binding.activityMainTxtWeatherDescription.setText(wfd.getDescIcona());

      if(!wfd.getDescIconaAllerte().isEmpty()) {
        binding.activityMainTxtAllerta.setText("Allerta: " + wfd.getDescIconaAllerte());
      }

      OpenWeatherDataRepository repository_op = new OpenWeatherDataRepository(getApplication());
      repository_op.getAll().observe(this, (List<OpenWeatherDataEntity> od_entries) -> {
        if (od_entries.isEmpty() || od_entries.size() == 0) {
          Toast.makeText(this, "Dati meteo non disponibili, riprova più tardi", Toast.LENGTH_SHORT);
          return;
        }
        OpenWeatherDataEntity opw = od_entries.get(od_entries.size() - 1);
        binding.activityMainTxtActTemperature.setText(opw.getActualTemperature() + "");
        binding.activityMainTxtHumidity.setText(opw.getHumidity() + "%");
        NumberFormat formatter_w = new DecimalFormat("#0.00");
        Double w = Double.parseDouble(opw.getWindSpeed()) * 3.6;
        binding.activityMainTxtWind.setText(formatter_w.format(w) + " km/h");

        DateFormat formatter = new SimpleDateFormat("HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));

        Calendar start = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
        String actual_date = formatter.format(start.getTime());

        binding.mainActivityTxtUltimoAgg.setText("Alba "+ formatter.format(opw.getSunrise()) +" - Tramonto " + formatter.format(opw.getSunset()) + "\nAggiornato alle: " + formatter.format(entries.get(entries.size()-1).getDataInserimentoDb()));

        // ------ ------ ------
        // SET BACKGROUND IMAGE
        // ------ ------ ------


        Boolean isNight = false;
        long now = start.getTimeInMillis();
        long sunset = opw.getSunset();
        long sunrise = opw.getSunrise();

        if(now >= (sunrise - 900000) ) {
          // sunrise
          binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_new_sunsire);
          window.setStatusBarColor(Color.parseColor("#EF7942"));
        }

        binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_main_day);
        window.setStatusBarColor(Color.parseColor("#7AA9C3"));

        if(now >= sunset){
          // sunset
          binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_main_sunset);
          window.setStatusBarColor(Color.parseColor("#BA725A"));
        }
        if( (now >= sunset + 3600000) && (now <= sunrise + 79200000)) {
          // night
          binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_main_night);
          window.setStatusBarColor(Color.parseColor("#345A7B"));
          isNight = true;
        }

        switch (WeatherIconDescriptor.getWeatherType(wfd.getIcona())) {
          case COPERTO:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_cloud);
            break;

          case COPERTO_CON_PIOGGIA:

            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_light_rain);
            binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_main_cloud);
            window.setStatusBarColor(Color.parseColor("#4B4C4C"));
            break;

          case COPERTO_CON_PIOGGIA_ABBONDANTE:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_rain);
            binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_main_cloud);
            window.setStatusBarColor(Color.parseColor("#4B4C4C"));
            break;

          case COPERTO_CON_PIOGGIA_E_NEVE:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_snow_rain);
            binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_main_cloud);
            window.setStatusBarColor(Color.parseColor("#4B4C4C"));
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
            binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_main_cloud);
            window.setStatusBarColor(Color.parseColor("#4B4C4C"));
            break;

          case SOLEGGIATO_CON_PIOGGIA_E_NEVE:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_sun_cloud_rain_snow);
            binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_main_cloud);
            window.setStatusBarColor(Color.parseColor("#4B4C4C"));
            break;

          case TEMPORALE:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_thunderstorm);
            binding.activityMainLinearLayoutBg.setBackgroundResource(R.drawable.bg_main_cloud);
            window.setStatusBarColor(Color.parseColor("#4B4C4C"));
            break;

          case UNDEFINED:
            binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_sun_cloud);
            break;
        }

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
                  // do something
                }
                @Override
                public void onEnded(SimpleTarget target) {
                  // do something
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
                  // do something
                }

                @Override
                public void onEnded(SimpleTarget target) {
                  // do something
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
    SharedPreferences getPrefs = PreferenceManager
        .getDefaultSharedPreferences(getBaseContext());

    // create a new boolean and preference
    first_pos = getPrefs.getString("first_pos", "Aggiungi 1° Preferito");
    second_pos = getPrefs.getString("second_pos", "Aggiungi 2° Preferito");

    Menu menu = binding.mainActivityNavView.getMenu();
    MenuItem first_pref = menu.findItem(R.id.drawer_view_first_pref);
    MenuItem second_pref = menu.findItem(R.id.drawer_view_second_pref);

    first_pref.setTitle(first_pos);
    second_pref.setTitle(second_pos);

    WeatherReportRepository repository = new WeatherReportRepository(this.getApplication());
    repository.getAll().observe(this, entries -> {
      if (entries == null || entries.isEmpty() || entries.size() == 0) {
        // Build alert dialog
        DialogShower.ShowDialog(this,new Intent(getApplicationContext(), ChooseLocationActivity.class),"Località non trovata", "Non sono riuscito a rilevare la tua posizione dal GPS.\nProva a ricercala manualmente!", "Ricerca", "Annulla");
        return;
      }

      // check time for reload weather meteo
      if(System.currentTimeMillis() >= (entries.get(entries.size() - 1).getDataInserimentoDb() + (600000) ) ) {
        // passate 2 ore
        Intent i = new Intent(MainActivity.this, LoaderActivity.class);
        i.putExtra("POSITION", entries.get(entries.size()-1).getPrevisione().getLocalita());
        this.startActivity(i);
      }
    });
  }
}
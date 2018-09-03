package it.chiarani.meteotrentinoapp.views;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.WeatherSlotAdapter;
import it.chiarani.meteotrentinoapp.api.API_locality;
import it.chiarani.meteotrentinoapp.api.API_locality_response;
import it.chiarani.meteotrentinoapp.api.API_weatherReport;
import it.chiarani.meteotrentinoapp.api.API_weatherReport_response;
import it.chiarani.meteotrentinoapp.database.entity.LocalityEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForWeekEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.databinding.ActivityMainBinding;
import it.chiarani.meteotrentinoapp.helper.Converter;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;
import it.chiarani.meteotrentinoapp.helper.WeatherTypes;
import it.chiarani.meteotrentinoapp.models.Locality;
import it.chiarani.meteotrentinoapp.models.WeatherForDay;
import it.chiarani.meteotrentinoapp.models.WeatherReport;
import it.chiarani.meteotrentinoapp.repositories.LocalityRepository;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class MainActivity extends SampleActivity implements API_weatherReport_response{

  // #REGION PRIVATE FIELDS
  private final static String MAINACTIVITY_TAG = "MAINACTIVITY";
  ActivityMainBinding binding;
  private WeatherReport _report;
  // #ENDREGION

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

    // log start activity
    Log.d( MAINACTIVITY_TAG, "Start mainactivity");
    launchIsFirstThread();

    Intent intent = getIntent();
    String user_location = "TRENTO";

    if(intent.hasExtra("POSITION")) {
      user_location = intent.getExtras().getString("POSITION");
    }
    else
    {
      Intent myIntent = new Intent(MainActivity.this, ChooseLocationActivity.class);
      startActivity(myIntent);
    }

    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    binding.activityMainRvWeatherSlot.setHasFixedSize(true);

    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
    binding.activityMainRvWeatherSlot.setLayoutManager(horizontalLayoutManagaer);




    new API_weatherReport(getApplication(),this, this::processFinish, user_location).execute();

    binding.activityMainBtnBollettino.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent myIntent = new Intent(MainActivity.this, WeatherReportActivity.class);
        startActivity(myIntent);
      }
    });
  }

  private void launchIsFirstThread() {
    Thread t = new Thread(new Runnable() {
      @Override
      public void run() {
        // Initialize SharedPreferences
        SharedPreferences getPrefs = PreferenceManager
            .getDefaultSharedPreferences(getBaseContext());

        // Create a new boolean and preference and set it to true
        boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

        // If the activity has never started before...
        if (isFirstStart) {
          Intent myIntent = new Intent(MainActivity.this, ChooseLocationActivity.class);

          runOnUiThread(new Runnable() {
            @Override public void run() {
              startActivity(myIntent);
            }
          });

          // Make a new preferences editor
          SharedPreferences.Editor e = getPrefs.edit();

          // Edit preference to make it false because we don't want this to run again
          e.putBoolean("firstStart", false);

          // Apply changes
          e.apply();
        }
      }
    });

    t.start();
  }

  /**
   * Called after API termination
   */
  @Override
  public void processFinish() {
    WeatherReportRepository repository = new WeatherReportRepository(getApplication());

    repository.getAll().observe(this, entries -> {

      WeatherReportEntity wfr  = entries.get(entries.size()-1);
      WeatherForWeekEntity wfw = wfr.getPrevisione();
      WeatherForDayEntity wfd  = wfw.getGiorni().get(0);

      binding.activityMainTxtPosition.setText(wfw.getLocalita());            // Locality
      binding.activityMainTxtTemperature.setText(wfd.gettMaxGiorno() + "°");  // Actual temperature
      binding.activityMainTxtPrev.setText(wfd.getDescIcona());               // Previsione

      if( wfd.getDescIconaAllerte().isEmpty() || wfd.getDescIconaAllerte() == null ) {
        binding.activityMainTxtAllerta.setText(" ");                    // Allerta
      }
      else
      {
        binding.activityMainTxtAllerta.setText(wfd.getDescIconaAllerte());
        //binding.activityMainTxtAllerta.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wind, 0, 0, 0);
      }

      if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.COPERTO)) {
        binding.activityMainRl.setBackgroundResource(R.drawable.bg_day);
        binding.activityMainImgWeather.setImageResource(R.drawable.ic_w_cloud);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.COPERTO_CON_PIOGGIA)) {
        binding.activityMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.activityMainImgWeather.setImageResource(R.drawable.ic_w_light_rain);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.COPERTO_CON_PIOGGIA_ABBONDANTE)) {
        binding.activityMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.activityMainImgWeather.setImageResource(R.drawable.ic_w_rain);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.COPERTO_CON_PIOGGIA_E_NEVE)) {
        binding.activityMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.activityMainImgWeather.setImageResource(R.drawable.ic_w_snow_rain);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.NEVICATA)) {
        binding.activityMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.activityMainImgWeather.setImageResource(R.drawable.ic_w_sun);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.SOLE)) {
        binding.activityMainRl.setBackgroundResource(R.drawable.bg_day);
        binding.activityMainImgWeather.setImageResource(R.drawable.ic_w_sun);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.SOLEGGIATO)) {
        binding.activityMainRl.setBackgroundResource(R.drawable.bg_day);
        binding.activityMainImgWeather.setImageResource(R.drawable.ic_w_sun_cloud);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.SOLEGGIATO_CON_PIOGGIA)) {
        binding.activityMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.activityMainImgWeather.setImageResource(R.drawable.ic_w_sun_cloud_rain);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.SOLEGGIATO_CON_PIOGGIA_E_NEVE)) {
        binding.activityMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.activityMainImgWeather.setImageResource(R.drawable.ic_w_sun_cloud_rain_snow);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.TEMPORALE)) {
        binding.activityMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.activityMainImgWeather.setImageResource(R.drawable.ic_w_thunderstorm);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.UNDEFINED)) {
        binding.activityMainRl.setBackgroundResource(R.drawable.bg_day);
        binding.activityMainImgWeather.setImageResource(R.drawable.ic_w_sun_cloud);
      }

      WeatherSlotAdapter adapter = new WeatherSlotAdapter(wfr);
      binding.activityMainRvWeatherSlot.setAdapter(adapter);            // Fasce

    });
  }

  @Override
  public void onBackPressed() {
    // do noting
  }
}

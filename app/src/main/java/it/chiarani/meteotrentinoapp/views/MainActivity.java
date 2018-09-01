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
import it.chiarani.meteotrentinoapp.databinding.ActivityMainBinding;
import it.chiarani.meteotrentinoapp.helper.Converter;
import it.chiarani.meteotrentinoapp.models.Locality;
import it.chiarani.meteotrentinoapp.models.WeatherReport;
import it.chiarani.meteotrentinoapp.repositories.LocalityRepository;

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

    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    binding.activityMainRvWeatherSlot.setHasFixedSize(true);

    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
    binding.activityMainRvWeatherSlot.setLayoutManager(horizontalLayoutManagaer);

    // specify an adapter (see also next example)
    // String[] tmp = {" Fascia oraria: 0-6 ", " Fascia oraria: 6-12 ", " Fascia oraria: 12-18 ", " Fascia oraria: 8-24 "};


    launchIsFirstThread();

    new API_weatherReport(getApplication(),this, this::processFinish, "TRENTO").execute();

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

  @Override
  public void processFinish(WeatherReport report) {
    _report = report;

    WeatherSlotAdapter adapter = new WeatherSlotAdapter(report);
    binding.activityMainRvWeatherSlot.setAdapter(adapter);
    //binding.activityMainTxtPosition.setText(report.getPrevisione().getLocalita());
    binding.activityMainTxtTemperature.setText(report.getPrevisione().getGiorni().get(0).gettMaxGiorno() + "°");
    binding.activityMainTxtPrev.setText(report.getPrevisione().getGiorni().get(0).getDescIcona());
    binding.activityMainTxtAllerta.setText(report.getPrevisione().getGiorni().get(0).getDescIconaAllerte());
   // binding.activityMainTxtAllerta.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wind, 0, 0, 0);


    LocalityRepository repository = new LocalityRepository(getApplication());

    List<Locality> x = new ArrayList<>();
    repository.getAll().observe(this, entries -> {
      binding.activityMainTxtPosition.setText(entries.get(0).getLoc());
      Toast.makeText(this, entries.get(0).getLoc(), Toast.LENGTH_SHORT).show();

    });
  }
}

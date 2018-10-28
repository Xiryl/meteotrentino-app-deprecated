package it.chiarani.meteotrentinoapp.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.WeatherReportAdapter;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.databinding.ActivityWeatherReportBinding;
import it.chiarani.meteotrentinoapp.repositories.OpenWeatherDataRepository;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class WeatherReportActivity extends SampleActivity {

  // #region private fields
  private ActivityWeatherReportBinding binding;
  private int report_day = 0;
  private final String INTENT_DAY_TAG = "DAY";
  // #endregion

  @Override
  protected int getLayoutID() {
    return R.layout.activity_weather_report;
  }

  @Override
  protected void setActivityBinding() {
    binding = DataBindingUtil.setContentView(this, getLayoutID());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent intent = getIntent();
    if(intent.hasExtra(INTENT_DAY_TAG)) {
      report_day = intent.getExtras().getInt(INTENT_DAY_TAG);
    }

    // set toolbar color
    Window window = this.getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setStatusBarColor(Color.parseColor("#65A8D9"));


    WeatherReportRepository repository = new WeatherReportRepository(getApplication());
    repository.getAll().observe(this, entries -> {
      WeatherReportEntity report = entries.get(entries.size()-1);

      binding.weatherReportRvWeather.setHasFixedSize(true);

      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
      linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
      binding.weatherReportRvWeather.setLayoutManager(linearLayoutManager);


      OpenWeatherDataRepository repository_op = new OpenWeatherDataRepository(getApplication());
      repository_op.getAll().observe(this, entries_op -> {
        if(entries_op == null) {
          return;
        }

        WeatherReportAdapter adapter = new WeatherReportAdapter(this, report, entries_op.get(entries_op.size()-1), report_day);
        binding.weatherReportRvWeather.setAdapter(adapter);


        binding.activityWeatherReportTxtPrevisione.setText(report.getPrevisione().getGiorni().get(report_day).getTestoGiorno());
        binding.activityWeatherReportTxtPosition.setText(report.getPrevisione().getLocalita());

      });

    });

    binding.fragmentRadarDayBtnMenu.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

  }
}

package it.chiarani.meteotrentinoapp.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.WeatherReportAdapter;
import it.chiarani.meteotrentinoapp.adapters.WeatherSlotAdapter;
import it.chiarani.meteotrentinoapp.databinding.ActivityWeatherReportBinding;
import it.chiarani.meteotrentinoapp.models.WeatherForDay;
import it.chiarani.meteotrentinoapp.models.WeatherForSlot;
import it.chiarani.meteotrentinoapp.models.WeatherForWeek;
import it.chiarani.meteotrentinoapp.models.WeatherReport;

public class WeatherReportActivity extends SampleActivity {

  // #REGION PRIVATE FIELDS
  ActivityWeatherReportBinding binding;

  // #ENDREGION


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

    WeatherForSlot slot = (WeatherForSlot) getIntent().getParcelableExtra("report");

    WeatherForDay d = new WeatherForDay();

    List<WeatherForSlot> fasce = new ArrayList<>();
    fasce.add(slot);
    d.setFasce(fasce);



    WeatherForWeek w = new WeatherForWeek();

    List<WeatherForDay> giorni = new ArrayList<>();
    giorni.add(d);

    w.setGiorni(giorni);

    WeatherReport _report = new WeatherReport();
    _report.setPrevisione(w);

    WeatherReportAdapter adapter = new WeatherReportAdapter(_report);
    binding.weatherReportRvWeather.setAdapter(adapter);
  }
}

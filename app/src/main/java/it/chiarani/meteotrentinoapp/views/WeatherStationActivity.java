package it.chiarani.meteotrentinoapp.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.databinding.ActivityWeatherStationBinding;

public class WeatherStationActivity extends SampleActivity {

  ActivityWeatherStationBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected int getLayoutID() {
    return R.layout.activity_weather_station;
  }

  @Override
  protected void setActivityBinding() {
    binding = DataBindingUtil.setContentView(this, getLayoutID());
  }
}

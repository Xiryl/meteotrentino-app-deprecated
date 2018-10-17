package it.chiarani.meteotrentinoapp.views;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.WeatherStationAdapter;
import it.chiarani.meteotrentinoapp.api.API_stationWeatherData;
import it.chiarani.meteotrentinoapp.api.API_stationWeatherData_response;
import it.chiarani.meteotrentinoapp.databinding.ActivityWeatherStationBinding;
import it.chiarani.meteotrentinoapp.helper.CustomDialog;
import it.chiarani.meteotrentinoapp.helper.WeatherStation;
import it.chiarani.meteotrentinoapp.xml_parser.XmlDatiOggi;
import it.chiarani.meteotrentinoapp.xml_parser.XmlTemperaturaAria;

public class WeatherStationActivity extends SampleActivity implements API_stationWeatherData_response, AdapterView.OnItemSelectedListener {

  ActivityWeatherStationBinding binding;
  XmlDatiOggi data = null;

  @Override
  protected int getLayoutID() {
    return R.layout.activity_weather_station;
  }

  @Override
  protected void setActivityBinding() {
    binding = DataBindingUtil.setContentView(this, getLayoutID());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // set toolbar color
    Window window = this.getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setStatusBarColor(Color.parseColor("#65A8D9"));

    binding.fragmentRadarDayBtnMenu.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    Spinner spinner = (Spinner) findViewById(R.id.activity_weather_station_spinner);

    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<String> adapter = new ArrayAdapter<String>
        (this, android.R.layout.simple_spinner_item, WeatherStation.getWeatherStationList());
    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(this);

  }


  @Override
  public void processFinish(XmlDatiOggi dataaa) {
    List<XmlTemperaturaAria> tmp_temperature = dataaa.getTemperature().get(0).getTemperature();
    data = dataaa;

    if(data.getTemperature().get(0).getTemperature() == null || data.getTemperature()== null || data == null || data.getTemperature().isEmpty()) {

      CustomDialog cdd = new CustomDialog(this, "Stazione meteo non attiva.");
      cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      cdd.show();

      if(binding.activityWeatherStationRv.getAdapter() != null) {
        WeatherStationAdapter adapter1 = (WeatherStationAdapter) binding.activityWeatherStationRv.getAdapter();
        adapter1.clear();
      }
      return;
    }

    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    binding.activityWeatherStationRv.setHasFixedSize(true);

    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    binding.activityWeatherStationRv.setLayoutManager(horizontalLayoutManagaer);
    WeatherStationAdapter adapter1 = new WeatherStationAdapter(data);
    binding.activityWeatherStationRv.setAdapter(adapter1);
  }

  public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    // An item was selected. You can retrieve the selected item using
    // parent.getItemAtPosition(pos)
    if(pos == 0) return;
    try {
      String code = WeatherStation.getStationFromPos(pos+1);
      API_stationWeatherData task = new API_stationWeatherData(this::processFinish, code);
      task.execute();
    }
    catch (Exception ex ) {
      Log.d("d","d");
    }
  }

  public void onNothingSelected(AdapterView<?> parent) {
    // Another interface callback
  }
}

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
import android.widget.CompoundButton;
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
  XmlDatiOggi mData;
  String staton_code = "";

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
    window.setStatusBarColor(getResources().getColor(R.color.toolbar_color));

    binding.fragmentRadarDayBtnMenu.setOnClickListener(v -> onBackPressed());


    // fill spinner
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, WeatherStation.getWeatherStationList());
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    binding.activityWeatherStationSpinner.setAdapter(adapter);
    binding.activityWeatherStationSpinner.setOnItemSelectedListener(this);


    /**
     * Checkbox pioggia
     */
    binding.activityWeatherStationRdbtnPioggia.setOnCheckedChangeListener((buttonView, isChecked) -> {
      if(!staton_code.isEmpty()) {
        buildRecyclervier(1, mData);
      }
    });

    /**
     * Checkbox temperatura
     */
    binding.activityWeatherStationRdbtnTemperatura.setOnCheckedChangeListener((buttonView, isChecked) -> {
      if(!staton_code.isEmpty()) {
        buildRecyclervier(0, mData);
      }
    });
  }

  @Override
  public void processFinish(XmlDatiOggi data) {
    mData = data;
    if (data == null || data.getTemperature().get(0).getTemperature() == null || data.getTemperature() == null || data.getTemperature().isEmpty()) {

      CustomDialog cdd = new CustomDialog(this, getResources().getString(R.string.inactive_station));
      cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      cdd.show();

      if (binding.activityWeatherStationRv.getAdapter() != null) {
        WeatherStationAdapter adapter1 = (WeatherStationAdapter) binding.activityWeatherStationRv.getAdapter();
        adapter1.clear();
      }

      binding.activityWeatherStationRain.setText("- °C");
      binding.activityWeatherStationTemp.setText("- mm");
      staton_code = "";
      return;
    }

    buildRecyclervier(1, data);

    if (data.getTemperature() != null &&
        data.getPrecipitazioni() != null &&
        data.getTemperature().get(0).getTemperature() != null &&
        data.getPrecipitazioni().get(0).getPrecipitazione() != null) {
      binding.activityWeatherStationRain.setText(data.getTemperature().get(0).getTemperature().get(0).getTemperatura() + "°C");
      binding.activityWeatherStationTemp.setText(data.getPrecipitazioni().get(0).getPrecipitazione().get(0).getPioggia() + "mm");
    }
  }

  private void buildRecyclervier(int weather, XmlDatiOggi data){
    binding.activityWeatherStationRv.setHasFixedSize(true);

    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
    binding.activityWeatherStationRv.setLayoutManager(horizontalLayoutManagaer);
    WeatherStationAdapter adapter1 = new WeatherStationAdapter(data, weather);
    binding.activityWeatherStationRv.setAdapter(adapter1);
  }


  public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    if(pos == 0) return;

    try {
      String code = WeatherStation.getStationFromPos(pos+1);
      staton_code = code;
      API_stationWeatherData task = new API_stationWeatherData(this::processFinish, staton_code);
      task.execute();
    }
    catch (Exception ex ) {
      Log.d("selected-item",ex.getMessage());
      staton_code = "";
    }
  }

  public void onNothingSelected(AdapterView<?> parent) {
    // do nothing
  }
}

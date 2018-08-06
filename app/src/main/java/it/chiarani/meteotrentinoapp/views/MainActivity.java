package it.chiarani.meteotrentinoapp.views;

import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.WeatherSlotAdapter;
import it.chiarani.meteotrentinoapp.api.API_locality;
import it.chiarani.meteotrentinoapp.api.API_locality_response;
import it.chiarani.meteotrentinoapp.databinding.ActivityMainBinding;
import it.chiarani.meteotrentinoapp.models.Locality;

public class MainActivity extends SampleActivity implements API_locality_response{

  // #REGION PRIVATE FIELDS
  private final static String MAINACTIVITY_TAG = "MAINACTIVITY";
  ActivityMainBinding binding;
  public ArrayList<Locality> mylocs;
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

    Log.d( MAINACTIVITY_TAG, "Start mainactivity");

    // Launch asynctask for get locality
    new API_locality(this, this::processFinish).execute();

    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    binding.activityMainRvWeatherSlot.setHasFixedSize(true);

    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
    binding.activityMainRvWeatherSlot.setLayoutManager(horizontalLayoutManagaer);

    // specify an adapter (see also next example)
    String[] tmp = {" Fascia oraria: 0-6 ", " Fascia oraria: 6-12 ", " Fascia oraria: 12-18 ", " Fascia oraria: 8-24 "};
    WeatherSlotAdapter adapter = new WeatherSlotAdapter(tmp);
    binding.activityMainRvWeatherSlot.setAdapter(adapter);

/*
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
    LayoutInflater inflater = this.getLayoutInflater();
    View dialogView = inflater.inflate(R.layout.today_weather_dialog, null);
    dialogBuilder.setView(dialogView);

    AlertDialog alertDialog = dialogBuilder.create();
    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    alertDialog.show();*/
  }

  @Override
  public void processFinish(ArrayList<Locality> output) {
    mylocs = output;
  }
}

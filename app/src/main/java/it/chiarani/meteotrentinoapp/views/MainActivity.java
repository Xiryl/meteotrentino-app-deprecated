package it.chiarani.meteotrentinoapp.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.WeatherSlotAdapter;
import it.chiarani.meteotrentinoapp.databinding.ActivityMainBinding;

public class MainActivity extends SampleActivity {

  // Private Fields
  private ActivityMainBinding binding;
  private RecyclerView mRecyclerView;
  // End

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

    mRecyclerView = (RecyclerView) findViewById(R.id.activity_main_rv_weather_slot);

    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    mRecyclerView.setHasFixedSize(true);

    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
    mRecyclerView.setLayoutManager(horizontalLayoutManagaer);

    // specify an adapter (see also next example)
    String[] tmp = {"Fascia 0-6", "Fascia 6-12", "Fascia 12-18", "Fascia 18-24"};
    WeatherSlotAdapter adapter = new WeatherSlotAdapter(tmp);
    mRecyclerView.setAdapter(adapter);
  }
}

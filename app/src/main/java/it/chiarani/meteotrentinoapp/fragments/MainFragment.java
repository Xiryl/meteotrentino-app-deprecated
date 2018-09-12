package it.chiarani.meteotrentinoapp.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.WeatherSlotAdapter;
import it.chiarani.meteotrentinoapp.api.API_weatherReport;
import it.chiarani.meteotrentinoapp.api.API_weatherReport_response;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForWeekEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.databinding.ActivityMainBinding;
import it.chiarani.meteotrentinoapp.databinding.FragmentMainBinding;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;
import it.chiarani.meteotrentinoapp.helper.WeatherTypes;
import it.chiarani.meteotrentinoapp.repositories.OpenWeatherDataRepository;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;
import it.chiarani.meteotrentinoapp.views.WeatherReportActivity;

public class MainFragment extends Fragment implements API_weatherReport_response {

  FragmentMainBinding binding;
  ActivityMainBinding binging_act;
  String user_location;

  public MainFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
    binging_act  = DataBindingUtil.inflate(inflater, R.layout.activity_main, container, false);

    user_location = getArguments().getString("user_location");

    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);


    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    binding.fragmentMainRvWeatherSlot.setHasFixedSize(true);

    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
    binding.fragmentMainRvWeatherSlot.setLayoutManager(horizontalLayoutManagaer);

    // call api
    new API_weatherReport(getActivity().getApplication(),getContext(), this::processFinish, user_location).execute();

    binding.fragmentMainBtnBollettino.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent myIntent = new Intent(getActivity(), WeatherReportActivity.class);
        myIntent.putExtra("DAY", 0);
        startActivity(myIntent);
      }
    });


    ImageButton btn = view.findViewById(R.id.main_act_btn_menu);

    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.main_activity_drawer_layout);
        mDrawerLayout.openDrawer(Gravity.LEFT);
      }
    });
  }



  /**
   * Called after API termination
   */
  @Override
  public void processFinish() {
    WeatherReportRepository repository = new WeatherReportRepository(getActivity().getApplication());

    repository.getAll().observe(this, entries -> {

      if(entries.size() == 0)
        return;

      WeatherReportEntity wfr  = entries.get(entries.size()-1);
      WeatherForWeekEntity wfw = wfr.getPrevisione();
      WeatherForDayEntity wfd  = wfw.getGiorni().get(0);

      binding.fragmentMainTxtPosition.setText(wfw.getLocalita());            // Locality
      binding.fragmentMainTxtPrev.setText(wfd.getDescIcona());               // Previsione

      if( wfd.getDescIconaAllerte().isEmpty() || wfd.getDescIconaAllerte() == null ) {
        binding.fragmentMainTxtAllerta.setText(" ");                    // Allerta
      }
      else
      {
        binding.fragmentMainTxtAllerta.setText(wfd.getDescIconaAllerte());
        //binding.activityMainTxtAllerta.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wind, 0, 0, 0);
      }

      if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.COPERTO)) {
        binding.fragmentMainRl.setBackgroundResource(R.drawable.bg_day);
        binding.fragmentMainImgWeather.setImageResource(R.drawable.ic_w_cloud);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.COPERTO_CON_PIOGGIA)) {
        binding.fragmentMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.fragmentMainImgWeather.setImageResource(R.drawable.ic_w_light_rain);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.COPERTO_CON_PIOGGIA_ABBONDANTE)) {
        binding.fragmentMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.fragmentMainImgWeather.setImageResource(R.drawable.ic_w_rain);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.COPERTO_CON_PIOGGIA_E_NEVE)) {
        binding.fragmentMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.fragmentMainImgWeather.setImageResource(R.drawable.ic_w_snow_rain);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.NEVICATA)) {
        binding.fragmentMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.fragmentMainImgWeather.setImageResource(R.drawable.ic_w_sun);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.SOLE)) {
        binding.fragmentMainRl.setBackgroundResource(R.drawable.bg_day);
        binding.fragmentMainImgWeather.setImageResource(R.drawable.ic_w_sun);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.SOLEGGIATO)) {
        binding.fragmentMainRl.setBackgroundResource(R.drawable.bg_day);
        binding.fragmentMainImgWeather.setImageResource(R.drawable.ic_w_sun_cloud);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.SOLEGGIATO_CON_PIOGGIA)) {
        binding.fragmentMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.fragmentMainImgWeather.setImageResource(R.drawable.ic_w_sun_cloud_rain);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.SOLEGGIATO_CON_PIOGGIA_E_NEVE)) {
        binding.fragmentMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.fragmentMainImgWeather.setImageResource(R.drawable.ic_w_sun_cloud_rain_snow);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.TEMPORALE)) {
        binding.fragmentMainRl.setBackgroundResource(R.drawable.bg_day_cloud);
        binding.fragmentMainImgWeather.setImageResource(R.drawable.ic_w_thunderstorm);
      }
      else if(WeatherIconDescriptor.getWeatherType(wfd.getIcona()).equals(WeatherTypes.UNDEFINED)) {
        binding.fragmentMainRl.setBackgroundResource(R.drawable.bg_day);
        binding.fragmentMainImgWeather.setImageResource(R.drawable.ic_w_sun_cloud);
      }

      OpenWeatherDataRepository repository_op = new OpenWeatherDataRepository(getActivity().getApplication());
      repository_op.getAll().observe(this, od_entries -> {
        if(od_entries.size() == 0)
          return;

        binding.fragmentMainTxtTemperature.setText(od_entries.get(od_entries.size()-1).getActualTemperature() +"°");  // Actual temperature

        WeatherSlotAdapter adapter = new WeatherSlotAdapter(wfr, od_entries.get(od_entries.size()-1));
        binding.fragmentMainRvWeatherSlot.setAdapter(adapter);            // Fasce


      });
    });

  }


}

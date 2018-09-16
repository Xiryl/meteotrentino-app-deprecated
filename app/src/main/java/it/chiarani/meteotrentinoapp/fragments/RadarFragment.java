package it.chiarani.meteotrentinoapp.fragments;

  import android.databinding.DataBindingUtil;
  import android.os.Bundle;
  import android.support.annotation.NonNull;
  import android.support.annotation.Nullable;
  import android.support.v4.app.Fragment;
  import android.support.v4.widget.DrawerLayout;
  import android.support.v7.widget.LinearLayoutManager;
  import android.view.Gravity;
  import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  import android.widget.ImageButton;

  import com.bumptech.glide.Glide;
  import com.bumptech.glide.load.engine.DiskCacheStrategy;
  import com.bumptech.glide.request.RequestOptions;

  import it.chiarani.meteotrentinoapp.R;
  import it.chiarani.meteotrentinoapp.adapters.WeatherSevenDayAdapter;
  import it.chiarani.meteotrentinoapp.api.API_weatherReport;
  import it.chiarani.meteotrentinoapp.api.API_weatherReport_response;
  import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
  import it.chiarani.meteotrentinoapp.databinding.ActivityMainBinding;
  import it.chiarani.meteotrentinoapp.databinding.FragmentRadarBinding;
  import it.chiarani.meteotrentinoapp.databinding.FragmentSevenDayBinding;
  import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class RadarFragment extends Fragment {

  FragmentRadarBinding binding;
  ActivityMainBinding binging_act;

  public RadarFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_radar, container, false);
    binging_act  = DataBindingUtil.inflate(inflater, R.layout.activity_main, container, false);


    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Glide.with(this)
        .load("https://content.meteotrentino.it/dati-meteo/radar/home/lastRadar4mobile.aspx")
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.fragmentRadarImgRadarNordItaly);

    Glide.with(this)
        .load("http://api.sat24.com/animated/ALPS/infraPolair/1/Central%20European%20Standard%20Time/493234")
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.fragmentRadarImgInfrarossoAlpi);


  }




}

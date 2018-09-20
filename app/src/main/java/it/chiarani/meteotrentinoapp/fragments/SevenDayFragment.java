package it.chiarani.meteotrentinoapp.fragments;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.WeatherSevenDayAdapter;
import it.chiarani.meteotrentinoapp.api.API_weatherReport;
import it.chiarani.meteotrentinoapp.api.API_weatherReport_response;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.databinding.FragmentSevenDayBinding;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class SevenDayFragment extends Fragment implements API_weatherReport_response {

  // #region private fields
  private FragmentSevenDayBinding binding;
  private String user_location;
  private WeatherReportRepository repository;
  private final static String INTENT_USER_LOCATION_TAG = "user_location";
  // #region private fields

  /**
   * Empty constructor
   */
  public SevenDayFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    repository = new WeatherReportRepository(getActivity().getApplication());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    Window window = getActivity().getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setStatusBarColor(Color.parseColor("#5D96CC"));

    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_seven_day, container, false);

    user_location = getArguments().getString(INTENT_USER_LOCATION_TAG);

    repository.getAll().observe(this, entries -> {
      if(entries.size() == 0 || entries == null) {
        // call api
        new API_weatherReport(getActivity().getApplication(),getContext(), this::processFinish, user_location).execute();
      }
      else
      {
        DisplayToUi();
      }
    });

    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    binding.fragmentSevenDayRv.setHasFixedSize(true);

    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    binding.fragmentSevenDayRv.setLayoutManager(horizontalLayoutManagaer);

    ImageButton btn = view.findViewById(R.id.fragment_seven_day_btn_menu);

    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
//        DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.main_activity_drawer_layout);
  //      mDrawerLayout.openDrawer(Gravity.LEFT);
      }
    });
  }


  /**
   * Called after API termination
   */
  @Override
  public void processFinish() {
    DisplayToUi();
  }

  /**
   * Display data to UI
   */
  private void DisplayToUi() {
    repository.getAll().observe(this, entries -> {
      WeatherReportEntity report = entries.get(entries.size() -1);
      WeatherSevenDayAdapter adapter = new WeatherSevenDayAdapter(report);
      binding.fragmentSevenDayRv.setAdapter(adapter);
    });
  }

}

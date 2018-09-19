package it.chiarani.meteotrentinoapp.fragments;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.api.API_endpoint;
import it.chiarani.meteotrentinoapp.databinding.ActivityMainBinding;
import it.chiarani.meteotrentinoapp.databinding.FragmentRadarBinding;

public class RadarFragment extends Fragment {

  // #region private fields
  private FragmentRadarBinding binding;
  // #endregion

  /**
   * Empty constructor
   */
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
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Window window = getActivity().getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setStatusBarColor(Color.parseColor("#5D96CC"));

    // radar
    Glide.with(this)
        .load(API_endpoint.ENDPOINT_RADAR)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.fragmentRadarImgRadarNordItaly);

    // infrarossi
    Glide.with(this)
        .load(API_endpoint.ENDPOINT_INFRAROSSI)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.fragmentRadarImgInfrarossoAlpi);
  }




}

package it.chiarani.meteotrentinoapp.views;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.api.API_endpoint;
import it.chiarani.meteotrentinoapp.databinding.ActivityRadarBinding;
import it.chiarani.meteotrentinoapp.helper.CustomDialog;

public class RadarActivity extends SampleActivity{

  // #region private fields
  private ActivityRadarBinding binding;
  // #endregion

  @Override
  protected int getLayoutID() {
    return R.layout.activity_radar;
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

    // radar
    Glide.with(this)
        .load(API_endpoint.ENDPOINT_RADAR)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.fragmentRadarImgRadarNordItaly);

    // infrarossi
    Glide.with(this)
        .load(API_endpoint.ENDPOINT_INFRARED)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.fragmentRadarImgInfrarossoAlpi);


    Glide.with(this)
        .load("https://api.sat24.com/mostrecent/ALPS/visual5hdcomplete")
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.fragmentRadarImgZonaAlpina);

    Glide.with(this)
        .load("https://api.sat24.com/animated/EU/snow/1/Central%20European%20Standard%20Time")
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.fragmentRadarImgRadarNeve);

    Glide.with(this)
        .load("https://api.sat24.com/animated/EU/infraPolair/1/Central%20European%20Standard%20Time")
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.fragmentRadarImgInfrarossoEuropa);




    binding.fragmentRadarDayBtnMenu.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    CustomDialog cdd = new CustomDialog(this, "Il caricamento delle immagini satellitari dipende dalla velocità della tua connessione dati\n\nScorri per visualizzare tutti i radar!");
    cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    cdd.show();
  }
}

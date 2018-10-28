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

    // zona alpina
    Glide.with(this)
        .load(API_endpoint.ENDPOINT_IMG_ZONA_ALPINA)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.fragmentRadarImgZonaAlpina);

    // radar neve
    Glide.with(this)
        .load(API_endpoint.ENDPOINT_IMG_RADAR_NEVE)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.fragmentRadarImgRadarNeve);

    // immagine europea
    Glide.with(this)
        .load(API_endpoint.ENDPOINT_IMG_EUROPA)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.fragmentRadarImgInfrarossoEuropa);



    binding.fragmentRadarDayBtnMenu.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    CustomDialog cdd = new CustomDialog(this, getResources().getString(R.string.radar_message));
    cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    cdd.show();
  }
}

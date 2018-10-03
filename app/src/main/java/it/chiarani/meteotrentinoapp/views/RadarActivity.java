package it.chiarani.meteotrentinoapp.views;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.api.API_endpoint;
import it.chiarani.meteotrentinoapp.databinding.ActivityRadarBinding;
import it.chiarani.meteotrentinoapp.helper.DialogShower;

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
        .load(API_endpoint.ENDPOINT_INFRAROSSI)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.fragmentRadarImgInfrarossoAlpi);

    binding.fragmentRadarDayBtnMenu.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    DialogShower.ShowDialog(this, null,  "Radar", "Il caricamento del radar può dipendere dalla connessione del tuo telefono", "ok", "");

  }
}

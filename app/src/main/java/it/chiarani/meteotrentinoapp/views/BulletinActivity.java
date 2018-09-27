package it.chiarani.meteotrentinoapp.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.api.API_endpoint;
import it.chiarani.meteotrentinoapp.databinding.ActivityBulletinBinding;
import it.chiarani.meteotrentinoapp.databinding.ActivityRadarBinding;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class BulletinActivity extends SampleActivity{

  // #region private fields
  private ActivityBulletinBinding binding;
  private final static String URL_BOLLETTINO_MONTAGNA       = "https://www.meteotrentino.it/#!/menu?menuItem=41";
  private final static String URL_BOLLETTINO_VALANGHE       = "https://www.meteotrentino.it/#!/menu?menuItem=4";
  private final static String URL_BOLLETTINO_PROBABILISTICO = "https://www.meteotrentino.it/protcivtn-meteo/api/front/bollettinoProb?idPrevisione=";
  // #endregion

  @Override
  protected int getLayoutID() {
    return R.layout.activity_bulletin;
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

    WeatherReportRepository repo = new WeatherReportRepository(getApplication());
    repo.getAll().observe(this, entries -> {

      binding.activityBulletinCrdwMontagna.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_BOLLETTINO_MONTAGNA));
          startActivity(browserIntent);
        }
      });

      binding.activityBulletinCrdwProb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_BOLLETTINO_PROBABILISTICO + entries.get(entries.size()-1).getIdPrevisione() + "&history=0"));
          startActivity(browserIntent);
        }
      });

      binding.activityBulletinCrdwValanghe.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_BOLLETTINO_VALANGHE));
          startActivity(browserIntent);
        }
      });
    });

    binding.fragmentRadarDayBtnMenu.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });
  }
}

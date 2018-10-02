package it.chiarani.meteotrentinoapp.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.AllerteListAdapter;
import it.chiarani.meteotrentinoapp.adapters.WeatherStationAdapter;
import it.chiarani.meteotrentinoapp.api.API_endpoint;
import it.chiarani.meteotrentinoapp.api.API_protezioneCivileAvvisiAllerte;
import it.chiarani.meteotrentinoapp.api.API_protezioneCivileAvvisiAllerte_response;
import it.chiarani.meteotrentinoapp.databinding.ActivityAllerteBinding;
import it.chiarani.meteotrentinoapp.databinding.ActivityBulletinBinding;
import it.chiarani.meteotrentinoapp.databinding.ActivityRadarBinding;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class AllerteActivity extends SampleActivity implements API_protezioneCivileAvvisiAllerte_response{

  // #region private fields
  private ActivityAllerteBinding binding;
  private final static String URL_PROVINCIA = "http://www.protezionecivile.tn.it/news_comunicati_stampa/";
  private final static String URL_METEO     = "https://www.meteotrentino.it/#!/content?menuItemDesktop=44";
  // #endregion

  @Override
  protected int getLayoutID() {
    return R.layout.activity_allerte;
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

      binding.activityAllerteCrdwMeteot.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_METEO));
          startActivity(browserIntent);
        }
      });

      binding.activityAllerteCrdwProvincia.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_PROVINCIA));
          startActivity(browserIntent);
        }
      });

      binding.fragmentRadarDayBtnMenu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          onBackPressed();
        }
      });
    });

    new API_protezioneCivileAvvisiAllerte(getApplication(), this, this::processFinish).execute();
  }

  @Override
  public void processFinish(ArrayList<String> data) {
    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    binding.activityAllerteRv.setHasFixedSize(true);

    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    binding.activityAllerteRv.setLayoutManager(horizontalLayoutManagaer);
    AllerteListAdapter adapter1 = new AllerteListAdapter(data);
    binding.activityAllerteRv.setAdapter(adapter1);
  }
}

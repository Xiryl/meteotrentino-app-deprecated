package it.chiarani.meteotrentinoapp.views;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Locale;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.api.API_weatherReport;
import it.chiarani.meteotrentinoapp.api.API_weatherReport_response;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.databinding.ActivityLoaderBinding;
import it.chiarani.meteotrentinoapp.helper.GpsTracker;
import it.chiarani.meteotrentinoapp.repositories.OpenWeatherDataRepository;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class LoaderActivity extends SampleActivity implements API_weatherReport_response{

  // #region private fields
  private ActivityLoaderBinding binding;
  private String user_location = "TRENTO";
  private boolean wait_for_permissions = false;
  private final static String ACTIVITY_TAG = "LOADER_ACTIVITY";
  private final int PERMISSION_REQ_CODE = 101;
  // #endregion

  @Override
  protected int getLayoutID() {
    return R.layout.activity_loader;
  }

  @Override
  protected void setActivityBinding() {
    binding = DataBindingUtil.setContentView(this, getLayoutID());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent intent = getIntent();
    if(intent.hasExtra("POSITION")) {
      // call API
      new API_weatherReport(getApplication(),this, this::processFinish, intent.getStringExtra("POSITION")).execute();
    }
    else {
      // clean repository
      WeatherReportRepository repository = new WeatherReportRepository(this.getApplication());
      // TODO CHECK DELETE ALL
      repository.deleteAll();

      // set toolbar color
      Window window = this.getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(Color.parseColor("#33495F"));

      // Set loader gif
      Glide.with(this)
          .load(R.drawable.git_weather_loading)
          .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
          .into(binding.activityLoaderGifLoading);

      // get user location from GPS
      try {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
          ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQ_CODE);
        }
        GpsTracker gpsTracker = new GpsTracker(this);
        if (gpsTracker.canGetLocation()) {
          double latitude = gpsTracker.getLatitude();
          double longitude = gpsTracker.getLongitude();
          Geocoder geocoder;
          List<Address> addresses;
          geocoder = new Geocoder(this, Locale.getDefault());

          addresses = geocoder.getFromLocation(latitude, longitude, 1);

          user_location = addresses.get(0).getLocality();

          // call API
          new API_weatherReport(getApplication(), this, this::processFinish, user_location).execute();

        } else {
          Toast.makeText(this, "GPS non attivo", Toast.LENGTH_LONG).show();
          Intent i = new Intent(LoaderActivity.this, MainActivity.class);
          this.startActivity(i);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


  @Override
  public void onRequestPermissionsResult( int requestCode,String[] permissions,int[] grantResults) {

    switch (requestCode) {
      case PERMISSION_REQ_CODE:
        boolean isPerpermissionForAllGranted = false;
        if (grantResults.length > 0 && permissions.length==grantResults.length) {
          for (int i = 0; i < permissions.length; i++){
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
              isPerpermissionForAllGranted=true;
            }else{
              isPerpermissionForAllGranted=false;
            }
          }
        } else {
          isPerpermissionForAllGranted=false;
          Log.e(ACTIVITY_TAG, "Permission GPS not given.");
          Intent i = new Intent(LoaderActivity.this, MainActivity.class);
          this.startActivity(i);
        }

        if(isPerpermissionForAllGranted){
          Log.e(ACTIVITY_TAG, "Permission GPS ok.");
          // call API
          new API_weatherReport(getApplication(),this, this::processFinish, user_location).execute();
        }
        else
        {
          Toast.makeText(this,"GPS non dato", Toast.LENGTH_SHORT).show();
        }
        break;
    }
  }

  @Override
  public void processFinish() {
    Intent i = new Intent(LoaderActivity.this, MainActivity.class);
    this.startActivity(i);
  }
}

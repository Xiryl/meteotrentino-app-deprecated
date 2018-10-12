package it.chiarani.meteotrentinoapp.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.takusemba.spotlight.OnSpotlightStateChangedListener;
import com.takusemba.spotlight.OnTargetStateChangedListener;
import com.takusemba.spotlight.Spotlight;
import com.takusemba.spotlight.shape.Circle;
import com.takusemba.spotlight.target.SimpleTarget;

import java.util.List;
import java.util.Locale;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.api.API_weatherReport;
import it.chiarani.meteotrentinoapp.api.API_weatherReport_response;
import it.chiarani.meteotrentinoapp.database.entity.LocalityEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.databinding.ActivityLoaderBinding;
import it.chiarani.meteotrentinoapp.helper.GpsTracker;
import it.chiarani.meteotrentinoapp.repositories.LocalityRepository;
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

    // preferences
    SharedPreferences getPrefs = PreferenceManager
        .getDefaultSharedPreferences(getBaseContext());

    //  Create a new boolean and preference and set it to true
    boolean isFirstStart = getPrefs.getBoolean("firstStart_loader", true);

    //  If the activity has never started before...
    if (isFirstStart) {


      Intent x = new Intent(this, IntroActivity.class);
      x.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(x);
      this.finish();


      //  Make a new preferences editor
      SharedPreferences.Editor e = getPrefs.edit();

      //  Edit preference to make it false because we don't want this to run again
      e.putBoolean("firstStart_loader", false);

      //  Apply changes
      e.apply();


    }
    else
    {
      Intent intent = getIntent();
      if(intent.hasExtra("POSITION")) {
        // call API
        String pos = intent.getStringExtra("POSITION");
        callAPI(pos);
      }
      else {
        // clean repository
        //WeatherReportRepository repository = new WeatherReportRepository(this.getApplication());
        // TODO CHECK DELETE ALL
        //repository.deleteAll();

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
          if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQ_CODE);
          }

          // PERMESSO AL GPS DATO
        WeatherReportRepository repository = new WeatherReportRepository(this.getApplication());
        repository.getAll().observe(this, entities -> {
          if (entities.size() <= 0) {
            Intent i = new Intent(LoaderActivity.this, ChooseLocationActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(i);
          }
          else
          {
            try {
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
                callAPI(user_location);

              } else {
                Toast.makeText(getApplicationContext(), "GPS non attivo.", Toast.LENGTH_SHORT).show();
                  if (entities.size() <= 0) {
                    Intent i = new Intent(LoaderActivity.this, ChooseLocationActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.startActivity(i);
                  }
                  else
                  {
                    // chiamo le API con l'ultima località assumendo che sia la predefinita
                    String loc = entities.get(entities.size()-1).getPrevisione().getLocalita();
                    Toast.makeText(getApplicationContext(), "GPS non attivo, ottengo l'ultima località ricercata...", Toast.LENGTH_SHORT).show();
                    callAPI(loc);
                  }
              }
            }
            catch (Exception e) {
              Log.d("", e.getMessage());
            }
          }
        });
      }
    }
  }


  @Override
  public void onRequestPermissionsResult( int requestCode,String[] permissions,int[] grantResults) {
    WeatherReportRepository repository = new WeatherReportRepository(this.getApplication());
    switch (requestCode){
      case PERMISSION_REQ_CODE:

        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
          Log.e("Permission","Granted");

          try {
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
              callAPI(user_location);

            } else {
              Toast.makeText(getApplicationContext(), "GPS non attivo.", Toast.LENGTH_SHORT).show();
              repository.getAll().observe(this, entities -> {
                if (entities.size() <= 0) {
                  Intent i = new Intent(LoaderActivity.this, ChooseLocationActivity.class);
                  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  this.startActivity(i);
                }
                else
                {
                  // chiamo le API con l'ultima località assumendo che sia la predefinita
                  String loc = entities.get(entities.size()-1).getPrevisione().getLocalita();
                  Toast.makeText(getApplicationContext(), "GPS non attivo, ottengo l'ultima località ricercata...", Toast.LENGTH_SHORT).show();
                  callAPI(loc);
                }
              });
            }
          }
          catch (Exception e) {
            Log.d("", e.getMessage());
          }
        }
        else
        {
          Log.e("Permission","Not Granted");
          repository.getAll().observe(this, entities -> {
            if (entities.size() <= 0) {
              Toast.makeText(getApplicationContext(), "Permesso al GPS negato", Toast.LENGTH_LONG).show();
              Intent i = new Intent(LoaderActivity.this, ChooseLocationActivity.class);
              i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              this.startActivity(i);
            }
            else
            {
              // chiamo le API con l'ultima località assumendo che sia la predefinita
              String loc = entities.get(entities.size()-1).getPrevisione().getLocalita();
              Toast.makeText(getApplicationContext(), "GPS non attivo, ottengo l'ultima località ricercata...", Toast.LENGTH_SHORT).show();
              callAPI(loc);
            }
          });
        }
        break;
    }
  }

  @Override
  public void processFinish(int response) {
    if(response == 1) {
      Intent i = new Intent(LoaderActivity.this, MainActivity.class);
      i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      this.startActivity(i);
    }
    else if(response == -2)
    {
      Toast.makeText(this,"Errore nella localizzazione GPS, raccolgo l'ultima località salvata ... ", Toast.LENGTH_LONG).show();
      Intent i = new Intent(LoaderActivity.this, MainActivity.class);
      i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      this.startActivity(i);
    }
    else if(response == -1) {
      Toast.makeText(this,"Errore nella connessione al server meteotrentino.it ... ", Toast.LENGTH_LONG).show();
      Intent i = new Intent(LoaderActivity.this, MainActivity.class);
      i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      this.startActivity(i);
    }
  }


  private void callAPI(String location) {
    LocalityRepository repo = new LocalityRepository(getApplication());
    repo.getAll().observe(this, localityEntities -> {
      if(localityEntities == null || localityEntities.isEmpty()) {
        new API_weatherReport(getApplication(), this, this::processFinish, location, "", "").execute();
      }
      else {
        LocalityEntity tmp = new LocalityEntity();
        for(LocalityEntity e : localityEntities)
          if(e.getLoc().equals(location))
          {
            tmp = e;
            break;
          }
        new API_weatherReport(getApplication(), this, this::processFinish, location, tmp.getLatitude(), tmp.getLongitude()).execute();
      }
    });

  }
}

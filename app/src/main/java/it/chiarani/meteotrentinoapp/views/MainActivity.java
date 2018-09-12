package it.chiarani.meteotrentinoapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;
import java.util.Locale;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.databinding.ActivityMainBinding;
import it.chiarani.meteotrentinoapp.fragments.MainFragment;
import it.chiarani.meteotrentinoapp.fragments.RadarFragment;
import it.chiarani.meteotrentinoapp.fragments.SevenDayFragment;
import it.chiarani.meteotrentinoapp.helper.GpsTracker;
import it.chiarani.meteotrentinoapp.models.WeatherReport;

public class MainActivity extends SampleActivity{

  // #REGION PRIVATE FIELDS
  private final static String MAINACTIVITY_TAG = "MAINACTIVITY";
  ActivityMainBinding binding;
  private WeatherReport _report;
  GpsTracker gpsTracker;
  BottomNavigationView bottomNavigationView;
  // #ENDREGION

  @Override
  protected int getLayoutID() {
    return R.layout.activity_main;
  }

  @Override
  protected void setActivityBinding() {
    binding = DataBindingUtil.setContentView(this, getLayoutID());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // log start activity
    Log.d( MAINACTIVITY_TAG, "Start mainactivity");
    launchIsFirstThread();

    binding.mainActivityNavView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(MenuItem menuItem) {
            // set item as selected to persist highlight
            menuItem.setChecked(true);

            switch (menuItem.getItemId()) {
              case R.id.drawer_view_search :
                Intent myIntent = new Intent(MainActivity.this, ChooseLocationActivity.class);
                startActivity(myIntent);
                return true;
            }
            return true;
          }
        });

    Intent intent = getIntent();
    String user_location = "TRENTO";

    if(intent.hasExtra("POSITION")) {
      user_location = intent.getExtras().getString("POSITION");
    }
    else
    {
      try {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
          ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
        }

        gpsTracker = new GpsTracker(this);
        if(gpsTracker.canGetLocation()){
          double latitude = gpsTracker.getLatitude();
          double longitude = gpsTracker.getLongitude();
          Geocoder geocoder;
          List<Address> addresses;
          geocoder = new Geocoder(this, Locale.getDefault());

          addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

          user_location = addresses.get(0).getLocality();

        }else{
          gpsTracker.showSettingsAlert();
        }

      } catch (Exception e){
        e.printStackTrace();
      }
    }

    final String tmp = user_location;

    // set bottom navbar
    bottomNavigationView = binding.mainActivityBottomNav;

    // set scanqr as checked item

    bottomNavigationView.setOnNavigationItemSelectedListener(
        item -> {
          switch (item.getItemId()) {
            case R.id.bottombaritem_today:
              Bundle bundle = new Bundle();
              bundle.putString("user_location", tmp);
              MainFragment frag = new MainFragment();
              frag.setArguments(bundle);
              getSupportFragmentManager()
                  .beginTransaction()
                  .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                  .replace(R.id.activity_main_framelayout, frag, "MainFragment")
                  .commit();
              return true;
            case R.id.bottombaritem_sevenday:
              Bundle bundle1 = new Bundle();
              bundle1.putString("user_location", tmp);
              SevenDayFragment frag1 = new SevenDayFragment();
              frag1.setArguments(bundle1);
              getSupportFragmentManager()
                  .beginTransaction()
                  .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                  .replace(R.id.activity_main_framelayout, frag1, "MainFragment")
                  .commit();
              return true;
            case R.id.bottombaritem_radar:
              RadarFragment frag2 = new RadarFragment();
              getSupportFragmentManager()
                  .beginTransaction()
                  .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                  .replace(R.id.activity_main_framelayout, frag2, "MainFragment")
                  .commit();
              return true;
          }
          return false;
        });
    bottomNavigationView.setSelectedItemId(R.id.bottombaritem_today);

  }

  private void launchIsFirstThread() {
    Thread t = new Thread(new Runnable() {
      @Override
      public void run() {
        // Initialize SharedPreferences
        SharedPreferences getPrefs = PreferenceManager
            .getDefaultSharedPreferences(getBaseContext());

        // Create a new boolean and preference and set it to true
        boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

        // If the activity has never started before...
        if (isFirstStart) {
          Intent myIntent = new Intent(MainActivity.this, ChooseLocationActivity.class);

          runOnUiThread(new Runnable() {
            @Override public void run() {
              startActivity(myIntent);
            }
          });

          // Make a new preferences editor
          SharedPreferences.Editor e = getPrefs.edit();

          // Edit preference to make it false because we don't want this to run again
          e.putBoolean("firstStart", false);

          // Apply changes
          e.apply();
        }
      }
    });

    t.start();
  }


  @Override
  public void onBackPressed() {
    // do noting
  }


    private void loadFragment(Fragment fragment) {
      // load fragment
      FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
      transaction.replace(R.id.activity_main_framelayout, fragment);
      transaction.addToBackStack(null);
      transaction.commit();
    }
}

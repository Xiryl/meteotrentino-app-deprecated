package it.chiarani.meteotrentinoapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;

import java.util.List;
import java.util.Locale;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.databinding.ActivityMainBinding;
import it.chiarani.meteotrentinoapp.fragments.MainFragment;
import it.chiarani.meteotrentinoapp.fragments.RadarFragment;
import it.chiarani.meteotrentinoapp.fragments.SevenDayFragment;
import it.chiarani.meteotrentinoapp.helper.GpsTracker;
import it.chiarani.meteotrentinoapp.models.WeatherReport;
import it.chiarani.meteotrentinoapp.servicies.AlarmManagerBroadcastReceiver;

public class MainActivity extends SampleActivity{

  // #region private fields
  private final static String MAINACTIVITY_TAG = "MAINACTIVITY";
  private ActivityMainBinding binding;
  private WeatherReport _report;
  private final static String INTENT_USER_LOCATION_TAG = "user_location";
  private AlarmManagerBroadcastReceiver alarm;
  // #endregion

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
/*
    // log start activity
    Log.d( MAINACTIVITY_TAG, "Start mainactivity");

    // alarm = new AlarmManagerBroadcastReceiver();
    // alarm.SetAlarm(this);

    /**
     * Call Chooselocationactivity if is first running
     */

/*
    // TODO: IF GPS IS ALREADY ENABLE SKIP THIS
    launchIsFirstThread();

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
        GpsTracker gpsTracker = new GpsTracker(this);
        if(gpsTracker.canGetLocation()){
          double latitude = gpsTracker.getLatitude();
          double longitude = gpsTracker.getLongitude();
          Geocoder geocoder;
          List<Address> addresses;
          geocoder = new Geocoder(this, Locale.getDefault());

          addresses = geocoder.getFromLocation(latitude, longitude, 1);

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
    BottomNavigationView bottomNavigationView = binding.mainActivityBottomNav;
    Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_framelayout);

    // https://github.com/roughike/BottomBar/issues/385
    bottomNavigationView.setOnNavigationItemSelectedListener(
        item -> {
          switch (item.getItemId()) {
            case R.id.bottombaritem_today:
              Bundle bundle = new Bundle();
              bundle.putString(INTENT_USER_LOCATION_TAG, tmp);
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
              bundle1.putString(INTENT_USER_LOCATION_TAG, tmp);
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
    bottomNavigationView.setSelectedItemId(R.id.bottombaritem_today);*/
/*
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

              case R.id.drawer_view_today:
                Bundle bundle = new Bundle();
                bundle.putString(INTENT_USER_LOCATION_TAG, tmp);
                MainFragment frag = new MainFragment();
                frag.setArguments(bundle);
                getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.activity_main_framelayout, frag, "MainFragment")
                    .commit();
                binding.mainActivityDrawerLayout.closeDrawer(Gravity.LEFT);
                return true;

              case R.id.drawer_view_seven_day:
                Bundle bundle1 = new Bundle();
                bundle1.putString(INTENT_USER_LOCATION_TAG, tmp);
                SevenDayFragment frag1 = new SevenDayFragment();
                frag1.setArguments(bundle1);
                getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.activity_main_framelayout, frag1, "MainFragment")
                    .commit();
                binding.mainActivityDrawerLayout.closeDrawer(Gravity.LEFT);
                return true;

              case R.id.drawer_view_radar:
                RadarFragment frag2 = new RadarFragment();
                getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.activity_main_framelayout, frag2, "MainFragment")
                    .commit();
                binding.mainActivityDrawerLayout.closeDrawer(Gravity.LEFT);
                return true;

              case R.id.drawer_view_staz_meteorologiche:
                Intent myIntent1 = new Intent(MainActivity.this, WeatherStationActivity.class);
                startActivity(myIntent1);
                return true;
            }
            return true;
          }
        });
*/
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
}

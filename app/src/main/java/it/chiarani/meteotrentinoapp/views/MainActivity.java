package it.chiarani.meteotrentinoapp.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.WeatherSevenDayAdapter;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForWeekEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.databinding.ActivityMainBinding;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;
import it.chiarani.meteotrentinoapp.models.WeatherReport;
import it.chiarani.meteotrentinoapp.repositories.OpenWeatherDataRepository;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;
import it.chiarani.meteotrentinoapp.servicies.AlarmManagerBroadcastReceiver;

public class MainActivity extends SampleActivity {

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

    Log.d(MAINACTIVITY_TAG, "Start mainactivity");

    // get repository
    WeatherReportRepository repository = new WeatherReportRepository(this.getApplication());

    /*
    // alarm = new AlarmManagerBroadcastReceiver();
    // alarm.SetAlarm(this);
*/

    binding.mainActBtnMenu.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        binding.mainActivityDrawerLayout.openDrawer(Gravity.LEFT);
      }
    });

    // set toolbar color
    Window window = this.getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setStatusBarColor(Color.parseColor("#7FB3D1"));

    binding.mainActivityNavView.setNavigationItemSelectedListener(
        menuItem -> {
          // set item as selected to persist highlight
          menuItem.setChecked(true);

          switch (menuItem.getItemId()){

            case R.id.drawer_view_search:
              Intent chooseloc_intent = new Intent(MainActivity.this, ChooseLocationActivity.class);
              startActivity(chooseloc_intent);
              break;

            case R.id.drawer_view_today:
              Toast.makeText(this, "Sei già nella pagina richiesta", Toast.LENGTH_LONG).show();
              break;

            case R.id.drawer_view_radar:
              Intent radar_intent = new Intent(MainActivity.this, RadarActivity.class);
              startActivity(radar_intent);
              break;

            case R.id.drawer_view_staz_meteorologiche:
              Intent staz_intent = new Intent(MainActivity.this, WeatherStationActivity.class);
              startActivity(staz_intent);
              break;

            case R.id.drawer_view_staz_neve:
              Toast.makeText(this, "Servizio attivo solo nell'inverno", Toast.LENGTH_LONG).show();
              break;

            case R.id.drawer_view_bollettini:
              Intent bulletin_intent = new Intent(MainActivity.this, BulletinActivity.class);
              startActivity(bulletin_intent);
              break;

            case R.id.drawer_view_allerte:
              Intent allerte_intent = new Intent(MainActivity.this, AllerteActivity.class);
              startActivity(allerte_intent);
              break;

            case R.id.drawer_view_telegram:
              Intent telegram_intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.t.me/MeteoTrentinoBot"));
              startActivity(telegram_intent);
              break;
          }
          return true;
        });

    repository.getAll().observe(this, entries -> {
      if (entries == null || entries.isEmpty() || entries.size() == 0) {
        Toast.makeText(this, "Dati meteo non disponibili, riprova più tardi", Toast.LENGTH_LONG).show();
        return;
      }

      WeatherReportEntity wfr = entries.get(entries.size() - 1);
      WeatherForWeekEntity wfw = wfr.getPrevisione();
      WeatherForDayEntity wfd = wfw.getGiorni().get(0);

      binding.activityMainTxtMaxTemperature.setText(wfd.gettMaxGiorno() + ""); // Temp. MAX
      binding.activityMainTxtMinTemperature.setText(wfd.gettMinGiorno() + ""); // Temp. MIN

      // Lista Meteo 7 giorni
      binding.fragmentMainRvWeatherSlot.setHasFixedSize(true);
      LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
      binding.fragmentMainRvWeatherSlot.setLayoutManager(horizontalLayoutManagaer);
      WeatherSevenDayAdapter adapter = new WeatherSevenDayAdapter(wfr);
      binding.fragmentMainRvWeatherSlot.setAdapter(adapter);

      binding.activityMainTxtRain.setText(wfd.getFasce().get(wfd.getFasce().size() - 1).getDescPrecProb() + "%");

      binding.activityMainTxtPosition.setText(wfw.getLocalita());
      binding.activityMainTxtWeatherDescription.setText(wfd.getDescIcona());

      OpenWeatherDataRepository repository_op = new OpenWeatherDataRepository(getApplication());
      repository_op.getAll().observe(this, od_entries -> {
        if (od_entries.isEmpty() || od_entries.size() == 0) {
          Toast.makeText(this, "Dati meteo non disponibili, riprova più tardi", Toast.LENGTH_LONG);
          return;
        }
        OpenWeatherDataEntity opw = od_entries.get(od_entries.size() - 1);
        binding.activityMainTxtActTemperature.setText(opw.getActualTemperature() + "");
        binding.activityMainTxtHumidity.setText(opw.getHumidity() + "%");
        Double w = Double.parseDouble(opw.getWindSpeed()) * 3.6;
        binding.activityMainTxtWind.setText(w + " km/h");
      });

      switch (WeatherIconDescriptor.getWeatherType(wfd.getIcona())) {
        case COPERTO:
          binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_cloud);
          break;

        case COPERTO_CON_PIOGGIA:
          binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_light_rain);
          break;

        case COPERTO_CON_PIOGGIA_ABBONDANTE:
          binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_rain);
          break;

        case COPERTO_CON_PIOGGIA_E_NEVE:
          binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_snow_rain);
          break;

        case NEVICATA:
          binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_sun);
          break;

        case SOLE:
          binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_sun);
          break;

        case SOLEGGIATO:
          binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_sun_cloud);
          break;

        case SOLEGGIATO_CON_PIOGGIA:
          binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_sun_cloud_rain);
          break;

        case SOLEGGIATO_CON_PIOGGIA_E_NEVE:
          binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_sun_cloud_rain_snow);
          break;

        case TEMPORALE:
          binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_thunderstorm);
          break;

        case UNDEFINED:
          binding.activityMainIcWeatherIcon.setImageResource(R.drawable.ic_w_sun_cloud);
          break;
      }
    });
  }

  @Override
  public void onBackPressed() {
    // do noting
  }

  @Override
  protected void onResume() {
    super.onResume();

    WeatherReportRepository repository = new WeatherReportRepository(this.getApplication());
    repository.getAll().observe(this, entries -> {
      if (entries == null || entries.isEmpty() || entries.size() == 0) {
        Toast.makeText(this, "Download dei dati fallito.. riprova più tardi!", Toast.LENGTH_LONG).show();
        return;
      }

      // check time for reload weather meteo
      if(System.currentTimeMillis() >= entries.get(entries.size() - 1).getDataInserimentoDb() - (60 * 60 * 2 * 1000)) {
        // min 2 ore
      } else {
        // passate 2 ore
        Intent i = new Intent(MainActivity.this, LoaderActivity.class);
        this.startActivity(i);
      }
    });
  }
}
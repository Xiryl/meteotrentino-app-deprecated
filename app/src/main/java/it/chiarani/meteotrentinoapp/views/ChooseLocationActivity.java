package it.chiarani.meteotrentinoapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.api.API_location;
import it.chiarani.meteotrentinoapp.api.API_location_response;
import it.chiarani.meteotrentinoapp.database.entity.LocationEntity;
import it.chiarani.meteotrentinoapp.databinding.ActivityChooseLocationBinding;
import it.chiarani.meteotrentinoapp.models.Location;
import it.chiarani.meteotrentinoapp.repositories.LocationRepository;

public class ChooseLocationActivity extends SampleActivity implements API_location_response {

  // #region private fields
  private ActivityChooseLocationBinding binding;
  private final static String PREF_NUBER_KEY = "PREF_NUMBER";
  private String[] all_locs;
  private int pref_number = -1;
  // #endregion

  @Override
  protected int getLayoutID() {
    return R.layout.activity_choose_location;
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
    window.setStatusBarColor(getResources().getColor(R.color.toolbar_color));

    // GET param from intent
    Intent intent = getIntent();
    if(intent.hasExtra(PREF_NUBER_KEY)) {
      pref_number = intent.getIntExtra(PREF_NUBER_KEY, -1);
    }

    SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

    LocationRepository repository = new LocationRepository(this.getApplication());

    repository.getAll().observe(this, entries -> {
      if(entries.size() == 0) {
        // Launch async task for get locality
        new API_location(getApplication(), this, this::processFinish).execute();
      }
      else
      {
        all_locs = listTostring(entries);

        if(all_locs == null) {
          // TODO: place in @String
          Toast.makeText(this, getResources().getString(R.string.error_download_location), Toast.LENGTH_LONG).show();
          return;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, all_locs);
        binding.chooseLocationAutoCompleteTxt.setAdapter(adapter);
      }
    });

    /**
     * Catch ENTER
     */
    binding.chooseLocationAutoCompleteTxt.setOnKeyListener((v, keyCode, event) -> {
      if (event.getAction() == KeyEvent.ACTION_DOWN)
      {
        switch (keyCode)
        {
          case KeyEvent.KEYCODE_DPAD_CENTER:
          case KeyEvent.KEYCODE_ENTER:

            String user_location = binding.chooseLocationAutoCompleteTxt.getText().toString();

            if( user_location.isEmpty()  || all_locs == null || all_locs.length == 0) {
              Toast.makeText(v.getContext(), getResources().getString(R.string.error_insert_location), Toast.LENGTH_LONG).show();
              return  true;
            }

            for(String l : all_locs){
              if(l.toLowerCase().equals(user_location.toLowerCase()))
              {
                callLoaderactivity(getPrefs);
              }
            }

            Toast.makeText(v.getContext(), getResources().getString(R.string.error_invalid_location), Toast.LENGTH_LONG).show();
            return true;
          default:
            break;
        }
      }
      return false;
    });

    /**
     * Catch NEXT button
     */
    binding.chooseLocationBtnNext.setOnClickListener(v -> {

      String user_location = binding.chooseLocationAutoCompleteTxt.getText().toString();

      if(user_location.isEmpty() || user_location == null || all_locs == null || all_locs.length == 0) {
        Toast.makeText(v.getContext(), getResources().getString(R.string.error_insert_location), Toast.LENGTH_LONG).show();
        return;
      }

      for(String l : all_locs){
        if(l.toLowerCase().equals(user_location.toLowerCase()))
        {
          callLoaderactivity(getPrefs);
        }
      }
      Toast.makeText(v.getContext(), getResources().getString(R.string.error_invalid_location), Toast.LENGTH_LONG).show();
    });

    /**
     * Skip activity after choose the item
     */
    binding.chooseLocationAutoCompleteTxt.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
      callLoaderactivity(getPrefs);
    });

    // back button
    binding.fragmentRadarDayBtnMenu.setOnClickListener(v -> onBackPressed());
  }

  private void callLoaderactivity(SharedPreferences getPrefs) {
    SharedPreferences.Editor e = getPrefs.edit();

    if(pref_number == 1) {
      e.putString("first_pos", binding.chooseLocationAutoCompleteTxt.getText().toString());

      e.apply();
      launchIntent();
    }
    else if(pref_number == 2) {
      e.putString("second_pos", binding.chooseLocationAutoCompleteTxt.getText().toString());

      e.apply();
      launchIntent();
    }
    else
    {
      launchIntent();
    }
  }

  private void launchIntent()
  {
    Intent myIntent = new Intent(ChooseLocationActivity.this, LoaderActivity.class);
    myIntent.putExtra("POSITION", binding.chooseLocationAutoCompleteTxt.getText().toString());
    startActivity(myIntent);
  }

  /**
   * Called after API termination
   */
  @Override
  public void processFinish() {
    LocationRepository repository = new LocationRepository(this.getApplication());

    repository.getAll().observe(this, entries -> {
      all_locs = listTostring(entries);

      if(all_locs == null) {
        Toast.makeText(this, getResources().getString(R.string.error_download_location), Toast.LENGTH_LONG).show();
        return;
      }

      ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, all_locs);
      binding.chooseLocationAutoCompleteTxt.setAdapter(adapter);
    });
  }

  /**
   * Convert ArrayList to String[] containing all localities
   * @param data ArrayList to convert
   * @return String[] with locality
   */
  private String[] listTostring(List<LocationEntity> data) {
    if(data.isEmpty())
      return null;

    String[] tmp = new String[data.size()];

    int i = 0;
    for(Location loc : data) {
      tmp[i++] = loc.getLoc();
    }

    return tmp;
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
  }
}

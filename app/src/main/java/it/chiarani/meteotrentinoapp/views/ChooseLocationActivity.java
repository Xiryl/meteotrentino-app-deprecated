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
  private static final String CHOOSELOCATIONACTIVITY_TAG = "CHOOSELOCATIONACTIVITY";
  private ActivityChooseLocationBinding binding;
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

    Log.d( CHOOSELOCATIONACTIVITY_TAG, "Start choose location actiity");

    Intent intent = getIntent();
    if(intent.hasExtra("PREF_NUMBER")) {
      pref_number = intent.getIntExtra("PREF_NUMBER", -1);
    }

    // preferences
    SharedPreferences getPrefs = PreferenceManager
        .getDefaultSharedPreferences(getBaseContext());

    // set toolbar color
    Window window = this.getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setStatusBarColor(Color.parseColor("#65A8D9"));

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
          Toast.makeText(this, "Impossibile scaricare le località. riprova.", Toast.LENGTH_LONG).show();
          return;
        }

        // create adapter with all localities
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, all_locs);

        // set adapter to autocomplete text
        binding.chooseLocationAutoCompleteTxt.setAdapter(adapter);
      }
    });

    binding.chooseLocationAutoCompleteTxt.setOnKeyListener(new View.OnKeyListener()
    {
      public boolean onKey(View v, int keyCode, KeyEvent event)
      {
        if (event.getAction() == KeyEvent.ACTION_DOWN)
        {
          switch (keyCode)
          {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:

              String user_location = binding.chooseLocationAutoCompleteTxt.getText().toString();

              if(user_location.isEmpty() || user_location == null || all_locs == null || all_locs.length == 0) {
                Toast.makeText(v.getContext(), "Inserire una località per continuare!", Toast.LENGTH_LONG).show();
                return  true;
              }

              for(String l : all_locs){
                if(l.toLowerCase().equals(user_location.toLowerCase()))
                {
                  if(pref_number == 1) {

                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putString("first_pos", binding.chooseLocationAutoCompleteTxt.getText().toString());

                    //  Apply changes
                    e.apply();

                    // launch main activity
                    Intent myIntent = new Intent(ChooseLocationActivity.this, LoaderActivity.class);
                    myIntent.putExtra("POSITION", binding.chooseLocationAutoCompleteTxt.getText().toString());
                    startActivity(myIntent);
                  }
                  else if(pref_number == 2) {

                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putString("second_pos", binding.chooseLocationAutoCompleteTxt.getText().toString());

                    //  Apply changes
                    e.apply();

                    // launch main activity
                    Intent myIntent = new Intent(ChooseLocationActivity.this, LoaderActivity.class);
                    myIntent.putExtra("POSITION", binding.chooseLocationAutoCompleteTxt.getText().toString());
                    startActivity(myIntent);
                  }
                  else
                  {
                    // launch main activity
                    Intent myIntent = new Intent(ChooseLocationActivity.this, LoaderActivity.class);
                    myIntent.putExtra("POSITION", binding.chooseLocationAutoCompleteTxt.getText().toString());
                    startActivity(myIntent);
                  }

                }
              }
              Toast.makeText(v.getContext(), "Località non valida!", Toast.LENGTH_LONG).show();
              return true;
            default:
              break;
          }
        }
        return false;
      }
    });

    // set next button handler
    binding.chooseLocationBtnNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        String user_location = binding.chooseLocationAutoCompleteTxt.getText().toString();

        if(user_location.isEmpty() || user_location == null || all_locs == null || all_locs.length == 0) {
          Toast.makeText(v.getContext(), "Inserire una località per continuare!", Toast.LENGTH_LONG).show();
          return;
        }

        for(String l : all_locs){
          if(l.toLowerCase().equals(user_location.toLowerCase()))
          {
            if(pref_number == 1) {

              SharedPreferences.Editor e = getPrefs.edit();

              //  Edit preference to make it false because we don't want this to run again
              e.putString("first_pos", binding.chooseLocationAutoCompleteTxt.getText().toString());

              //  Apply changes
              e.apply();

              // launch main activity
              Intent myIntent = new Intent(ChooseLocationActivity.this, LoaderActivity.class);
              myIntent.putExtra("POSITION", binding.chooseLocationAutoCompleteTxt.getText().toString());
              startActivity(myIntent);
            }
            else if(pref_number == 2) {

              SharedPreferences.Editor e = getPrefs.edit();

              //  Edit preference to make it false because we don't want this to run again
              e.putString("second_pos", binding.chooseLocationAutoCompleteTxt.getText().toString());

              //  Apply changes
              e.apply();

              // launch main activity
              Intent myIntent = new Intent(ChooseLocationActivity.this, LoaderActivity.class);
              myIntent.putExtra("POSITION", binding.chooseLocationAutoCompleteTxt.getText().toString());
              startActivity(myIntent);
            }
            else
            {
              // launch main activity
              Intent myIntent = new Intent(ChooseLocationActivity.this, LoaderActivity.class);
              myIntent.putExtra("POSITION", binding.chooseLocationAutoCompleteTxt.getText().toString());
              startActivity(myIntent);
            }
          }
        }
        Toast.makeText(v.getContext(), "Località non valida!", Toast.LENGTH_LONG).show();
      }
    });

    /**
     * Skip activity after choose the item
     */
    binding.chooseLocationAutoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        if(pref_number == 1) {

          SharedPreferences.Editor e = getPrefs.edit();

          //  Edit preference to make it false because we don't want this to run again
          e.putString("first_pos", binding.chooseLocationAutoCompleteTxt.getText().toString());

          //  Apply changes
          e.apply();

          // launch main activity
          Intent myIntent = new Intent(ChooseLocationActivity.this, LoaderActivity.class);
          myIntent.putExtra("POSITION", binding.chooseLocationAutoCompleteTxt.getText().toString());
          startActivity(myIntent);
        }
        else if(pref_number == 2) {

          SharedPreferences.Editor e = getPrefs.edit();

          //  Edit preference to make it false because we don't want this to run again
          e.putString("second_pos", binding.chooseLocationAutoCompleteTxt.getText().toString());

          //  Apply changes
          e.apply();

          // launch main activity
          Intent myIntent = new Intent(ChooseLocationActivity.this, LoaderActivity.class);
          myIntent.putExtra("POSITION", binding.chooseLocationAutoCompleteTxt.getText().toString());
          startActivity(myIntent);
        }
        else
        {
          // launch main activity
          Intent myIntent = new Intent(ChooseLocationActivity.this, LoaderActivity.class);
          myIntent.putExtra("POSITION", binding.chooseLocationAutoCompleteTxt.getText().toString());
          startActivity(myIntent);
        }
      }
    });

    // back button
    binding.fragmentRadarDayBtnMenu.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });
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
        // TODO: place in @String
        Toast.makeText(this, "Impossibile scaricare le località. riprova.", Toast.LENGTH_LONG).show();
        return;
      }

      // create adapter with all localities
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, all_locs);

      // set adapter to autocomplete text
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

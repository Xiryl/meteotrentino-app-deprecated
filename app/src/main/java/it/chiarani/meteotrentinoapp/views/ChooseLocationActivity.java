package it.chiarani.meteotrentinoapp.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.api.API_locality;
import it.chiarani.meteotrentinoapp.api.API_locality_response;
import it.chiarani.meteotrentinoapp.databinding.ActivityChooseLocationBinding;
import it.chiarani.meteotrentinoapp.models.Locality;

public class ChooseLocationActivity extends SampleActivity implements API_locality_response {

  private static final String CHOOSELOCATIONACTIVITY_TAG = "CHOOSELOCATIONACTIVITY";
  ActivityChooseLocationBinding binding;
  public ArrayList<Locality> mylocs;

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

    // Launch async task for get locality
    new API_locality(this, this::processFinish).execute();
  }

  @Override
  public void processFinish(ArrayList<Locality> output) {
    mylocs = output;

    String[] all_locs = listTostring(output);

    if(all_locs == null) {
      // TODO: place in @String
      Toast.makeText(this, "Impossibile scaricare le località. riprova.", Toast.LENGTH_LONG).show();
      return;
    }

    // create adapter with all localities
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, all_locs);

    // set adapter to autocomplete text
    binding.chooseLocationAutoCompleteTxt.setAdapter(adapter);

    // set next button handler
    binding.chooseLocationBtnNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        // launch main activity

        Intent myIntent = new Intent(ChooseLocationActivity.this, MainActivity.class);
        startActivity(myIntent);
      }
    });
  }

  /**
   * Convert ArrayList to String[] containing all localities
   * @param data ArrayList to convert
   * @return String[] with locality
   */
  private String[] listTostring(ArrayList<Locality> data) {
    if(data.isEmpty())
      return null;

    String[] tmp = new String[data.size()];

    int i = 0;
    for(Locality loc : data) {
      tmp[i++] = loc.getLoc();
    }

    return tmp;
  }

  @Override
  public void onBackPressed() {
    // do nothing
  }
}

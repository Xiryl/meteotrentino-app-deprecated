package it.chiarani.meteotrentinoapp.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

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

    // Launch asynctask for get locality
    new API_locality(this, this::processFinish).execute();
  }

  @Override
  public void processFinish(ArrayList<Locality> output) {
    mylocs = output;

    String[] COUNTRIES = new String[mylocs.size()];

    int i = 0;
    for(Locality l : mylocs) {
      COUNTRIES[i++] = l.getLoc();
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    android.R.layout.simple_dropdown_item_1line, COUNTRIES);

    AutoCompleteTextView textView = (AutoCompleteTextView)
        findViewById(R.id.chooseLocationAutoCompleteTxt);

    textView.setAdapter(adapter);
  }


}

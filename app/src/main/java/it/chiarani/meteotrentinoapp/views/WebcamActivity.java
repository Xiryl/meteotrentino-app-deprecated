package it.chiarani.meteotrentinoapp.views;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.api.API_endpoint;
import it.chiarani.meteotrentinoapp.databinding.ActivityInfoBinding;
import it.chiarani.meteotrentinoapp.databinding.ActivityWebcamBinding;
import it.chiarani.meteotrentinoapp.helper.WeatherStation;
import it.chiarani.meteotrentinoapp.helper.WebcamListCSV;

public class WebcamActivity extends SampleActivity implements  AdapterView.OnItemSelectedListener{

  // #region private fields
  private ActivityWebcamBinding binding;
  // #endregion

  @Override
  protected int getLayoutID() {
    return R.layout.activity_webcam;
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

    Spinner spinner = (Spinner) findViewById(R.id.activity_webcam_spinner);

    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<String> adapter = new ArrayAdapter<String>
        (this, android.R.layout.simple_spinner_item, WebcamListCSV.getWebcamNames());
    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(this);

  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    String url = WebcamListCSV.getWebcamUrl(position+1);
    Glide.with(this)
        .load(url)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(binding.activityWebcamImg);
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}
